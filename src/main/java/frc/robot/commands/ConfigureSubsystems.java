// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ConfigureSubsystems extends SequentialCommandGroup {
  /** Creates a new ConfigureSubsystems. */
  public ConfigureSubsystems(Climber sub_climber, Drivetrain sub_drivetrain, Intake sub_intake, Shooter sub_shooter,
      Transfer sub_transfer, Turret sub_turret) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    // The hood is not configured since its pretty hard to configure a solenoid
    // The NanX and the Vision subsystems are also not featured here since I have no
    // clue how they work B)
    addCommands(new InstantCommand(sub_climber::configure, sub_climber));
    addCommands(new InstantCommand(sub_drivetrain::configure, sub_drivetrain));
    addCommands(new InstantCommand(sub_intake::configure, sub_intake));
    addCommands(new InstantCommand(sub_shooter::configure, sub_shooter));
    addCommands(new InstantCommand(sub_transfer::configure, sub_shooter));
    addCommands(new InstantCommand(sub_turret::configure, sub_turret));
  }

}
