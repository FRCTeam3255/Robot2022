// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Climber;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MagicClimb extends SequentialCommandGroup {

  SetClimberPosition extendClimber;
  SetClimberPosition retractClimber;
  InstantCommand pivotPerpendicular;
  InstantCommand pivotAngled;

  /** Creates a new ClimbNextRung. */
  public MagicClimb(Climber sub_climber) {

    // addCommands(new InstantCommand(sub_climber::extendClimber, sub_climber));
    // addCommands(new InstantCommand(sub_climber::pivotBackward, sub_climber));
    // addCommands(new InstantCommand(sub_climber::pivotForward, sub_climber));
    // addCommands(new InstantCommand(sub_climber::retractClimber, sub_climber));

    addCommands();
  }
}
