// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import com.frcteam3255.preferences.SN_DoublePreference;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotPreferences.TurretPrefs;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LaunchPadTurnPreset extends InstantCommand {

  Turret turret;
  double currentTurretPosition;
  SN_DoublePreference launchPadZeroDegreesTurned;
  SN_DoublePreference launchPadOneEightyDegreesTurned;

  public LaunchPadTurnPreset(Turret sub_turret) {
    turret = sub_turret;
    launchPadZeroDegreesTurned = TurretPrefs.turretLaunchPadZeroDegreesTurned;
    launchPadOneEightyDegreesTurned = TurretPrefs.turretLaunchPadOneEightyDegreesTurned;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double newTurretAngle;
    currentTurretPosition = turret.getTurretAngle();
    newTurretAngle = 0;

    if (currentTurretPosition <= 45) {
      // if we are at 0, turn to 341
      newTurretAngle = launchPadZeroDegreesTurned.getValue();

    } else {
      // if we are at 180, turn to 168
      newTurretAngle = launchPadOneEightyDegreesTurned.getValue();
    }

    turret.setTurretAngle(newTurretAngle);
  }
}
