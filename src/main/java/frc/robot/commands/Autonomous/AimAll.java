// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import com.frcteam3255.preferences.SN_BooleanPreference;
import com.frcteam3255.preferences.SN_DoublePreference;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class AimAll extends CommandBase {

  Shooter shooter;
  Turret turret;
  Hood hood;

  SN_DoublePreference goalRPM;
  SN_DoublePreference turretAngle;
  SN_BooleanPreference hoodSteep;

  /** Creates a new AimAll. */
  public AimAll(
      Shooter sub_shooter, SN_DoublePreference a_goalRPM,
      Turret sub_turret, SN_DoublePreference a_turretAngleDegrees,
      Hood sub_hood, SN_BooleanPreference a_hoodSteepened) {

    shooter = sub_shooter;
    turret = sub_turret;
    hood = sub_hood;

    addRequirements(shooter, turret, hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

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
