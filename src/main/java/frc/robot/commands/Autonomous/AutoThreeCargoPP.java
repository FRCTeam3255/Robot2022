// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotPreferences.DrivetrainPrefs;
import frc.robot.RobotPreferences.AutoPrefs.ThreeCargo;
import frc.robot.commands.Intake.CollectCargo;
import frc.robot.commands.Transfer.PushCargoSimple;
import frc.robot.commands.Turret.SetTurretPosition;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Turret;

public class AutoThreeCargoPP extends SequentialCommandGroup {

  Drivetrain drivetrain;
  Shooter shooter;
  Turret turret;
  Hood hood;
  Transfer transfer;
  Intake intake;
  Climber climber;

  String trajectoryJSON = "paths/j.wpilib.json";
  Trajectory trajectory = new Trajectory();

  RamseteCommand driveTo1Then2;

  /** Creates a new AutoThreeCargoPP. */
  public AutoThreeCargoPP(
      Drivetrain sub_drivetrain,
      Shooter sub_shooter,
      Turret sub_turret,
      Hood sub_hood,
      Transfer sub_transfer,
      Intake sub_intake,
      Climber sub_climber) {

    drivetrain = sub_drivetrain;
    shooter = sub_shooter;
    turret = sub_turret;
    hood = sub_hood;
    transfer = sub_transfer;
    intake = sub_intake;
    climber = sub_climber;

    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }

    driveTo1Then2 = new RamseteCommand(
        trajectory,
        drivetrain::getPose,
        new RamseteController(),
        DrivetrainPrefs.driveKinematics,
        drivetrain::driveSpeed,
        drivetrain);

    addCommands(

        parallel(
            new InstantCommand(drivetrain::setBrakeMode), // set drivetrain
            new SetShooterRPM(shooter, ThreeCargo.shooterRPM1_6), // set shooter
            new SetTurretPosition(turret, ThreeCargo.turretAngle1_6).withTimeout(.5), // set turret
            new InstantCommand(() -> hood.setHood(ThreeCargo.hoodLevel1_6.getValue())), // set hood
            new CollectCargo(intake, transfer).until(transfer::isTopBallCollected), // collect
            new PushCargoSimple(shooter, transfer).withTimeout(3)), // shoot

        new InstantCommand(() -> drivetrain.resetOdometry(trajectory.getInitialPose())),
        driveTo1Then2,
        new InstantCommand(() -> drivetrain.driveSpeed(0, 0)));

  }
}

/*
 * Plan for auto:
 * 
 * Start at fender by cargo 1
 * shoot preloaded cargo
 * drive and collect cargo 1 and 2
 * drive back to fender
 * shoot both cargo
 *
 */