// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotPreferences.DrivetrainPrefs;
import frc.robot.subsystems.Drivetrain;

public class DriveSpeed extends CommandBase {

  Drivetrain drivetrain;

  /** Creates a new DriveSpeed. */
  public DriveSpeed(Drivetrain sub_drivetrain) {
    drivetrain = sub_drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.driveSpeed(DrivetrainPrefs.testMPS.getValue(), DrivetrainPrefs.testMPS.getValue());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.driveSpeed(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
