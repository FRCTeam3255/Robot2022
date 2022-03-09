// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotPreferences.ClimberPrefs;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MagicClimb extends SequentialCommandGroup {

  Climber climber;
  Turret turret;
  Hood hood;

  // ReadyClimber readyClimber;

  SetClimberPosition extendClimber;
  SetClimberPosition retractClimber;
  InstantCommand pivotPerpendicular;
  InstantCommand pivotAngled;
  InstantCommand hookUp;
  InstantCommand hookDown;

  /** Creates a new ClimbNextRung. */
  public MagicClimb(Climber sub_climber, Turret sub_turret, Hood sub_hood) {

    climber = sub_climber;
    turret = sub_turret;
    hood = sub_hood;

    // readyClimber = new ReadyClimber(climber, turret, hood);

    extendClimber = new SetClimberPosition(climber, ClimberPrefs.climberUpPosition);
    retractClimber = new SetClimberPosition(climber, ClimberPrefs.climberDownPosition);
    pivotPerpendicular = new InstantCommand(climber::pivotPerpendicular, climber);
    pivotAngled = new InstantCommand(climber::pivotAngled, climber);
    hookUp = new InstantCommand(climber::hookUp);
    hookDown = new InstantCommand(climber::hookDown);

    addCommands(
        // readyClimber,
        extendClimber,
        retractClimber,
        pivotAngled,
        extendClimber,
        pivotPerpendicular,
        retractClimber,
        pivotAngled,
        extendClimber,
        pivotPerpendicular,
        retractClimber);
  }
}
