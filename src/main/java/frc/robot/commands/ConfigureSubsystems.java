// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.frcteam3255.preferences.SN_BooleanPreference;
import com.frcteam3255.utils.SN_Debug;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ConfigureSubsystems extends InstantCommand {
  /** Creates a new ConfigureSubsystems. */

  SN_Debug logDebug = new SN_Debug("Reset Configs");

  public ConfigureSubsystems(Climber sub_climber, Drivetrain sub_drivetrain, Intake sub_intake, Shooter sub_shooter,
      Transfer sub_transfer, Turret sub_turret) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    logDebug.disableMessages(new SN_BooleanPreference("Disable Messages:", false));

    // The hood is not configured since its pretty hard to configure a solenoid
    // The NanX and the Vision subsystems are also not featured here since I have no
    // clue how they work B)
    sub_climber.configure();
    sub_drivetrain.configure();
    sub_intake.configure();
    sub_shooter.configure();
    sub_transfer.configure();
    sub_turret.configure();
    for (int i = 0; i < 100; i++) {
      logDebug.printDebug("TASK COMPLETE!", "YOUR SUFFERING IS OVER!");
    }
  }
}

// TODO: Test logging BEFORE configuring - Ask Ian if need help