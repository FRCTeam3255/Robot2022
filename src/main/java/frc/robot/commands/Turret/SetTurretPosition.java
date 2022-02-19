// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Turret;

public class SetTurretPosition extends CommandBase {
  Turret turret;
  double degrees;

  int loopsInTol;
  int loopsToFinish;

  /** Creates a new SetTurretPosition. */
  public SetTurretPosition(Turret a_turret, double a_degrees) {
    turret = a_turret;
    degrees = a_degrees;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    loopsInTol = 0;
    loopsToFinish = RobotPreferences.TurretPrefs.turretLoopsToFinish.getValue();

    turret.setTurretAngle(degrees);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(turret.getTurretClosedLoopErrorDegrees()) < RobotPreferences.TurretPrefs.turretMaxAllowableErrorDegrees
        .getValue()) {
      loopsInTol++;
    } else {
      loopsInTol = 0;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.setTurretSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return loopsInTol > loopsToFinish;
  }
}
