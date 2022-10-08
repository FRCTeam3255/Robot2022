// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotPreferences.VisionPrefs;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class SetGoalRPM extends CommandBase {
  Shooter shooter;
  Vision vision;
  Hood hood;

  /** Creates a new SetGoalRPM. */
  public SetGoalRPM(Shooter sub_shooter, Vision sub_vision, Hood sub_hood) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = sub_shooter;
    vision = sub_vision;
    hood = sub_hood;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (vision.limelightDistanceFromGoal() < VisionPrefs.lowHoodMaxDistance.getValue()) {
      hood.hoodLowTilt();
      shooter.setGoalRPM(vision.limelightLowDistanceRPM());
    } else if (vision.limelightDistanceFromGoal() < VisionPrefs.midHoodMaxDistance.getValue()) {
      hood.hoodMediumTilt();
      shooter.setGoalRPM(vision.limelightMidDistanceRPM());
    } else if (vision.limelightDistanceFromGoal() < VisionPrefs.highHoodMaxDistance.getValue()) {
      hood.hoodHighTilt();
      shooter.setGoalRPM(vision.limelightHighDistanceRPM());
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
