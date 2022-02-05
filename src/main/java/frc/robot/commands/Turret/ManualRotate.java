// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Turret;

public class ManualRotate extends CommandBase {
  Turret turret;

  /** Creates a new ManualRotate. */
  public ManualRotate(Turret sub_turret) {
    // Use addRequirements() here to declare subsystem dependencies.
    turret = sub_turret;
    addRequirements(sub_turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if ((turret.getTurretMotorEncoderCount()
        * RobotPreferences.TurretPrefs.turretMotorEncoderAngleMultiplier.getValue() < 359
        && RobotContainer.coDriverStick.getRightStickX() > 0)
        || (turret.getTurretMotorEncoderCount()
            * RobotPreferences.TurretPrefs.turretMotorEncoderAngleMultiplier.getValue() > 1
            && RobotContainer.coDriverStick.getRightStickX() < 0)) {
      double rotate = RobotContainer.coDriverStick.getRightStickX();

      turret.setTurretSpeed(rotate);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
