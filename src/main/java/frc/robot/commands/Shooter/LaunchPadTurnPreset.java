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
  SN_DoublePreference launchpadDegrees;

  public LaunchPadTurnPreset(Turret sub_turret) {
    turret = sub_turret;
    launchpadDegrees = TurretPrefs.turretLaunchPadDegreesTurned;
    currentTurretPosition = turret.getTurretAngle();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double newTurretAngle;
    newTurretAngle = launchpadDegrees.getValue() + currentTurretPosition;
    turret.setTurretAngle(newTurretAngle);
  }
}
