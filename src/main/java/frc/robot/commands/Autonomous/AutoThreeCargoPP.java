// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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

    RamseteCommand driveTo1Then2 = drivetrain.getRamseteCommand(drivetrain.driveTo1Then2Traj);

    addCommands(
        new InstantCommand(drivetrain::setBrakeMode), // config drivetrain

        // shoot first ball
        parallel(
            new SetShooterRPM(shooter, ThreeCargo.shooterRPM1_6), // set shooter
            new SetTurretPosition(turret, ThreeCargo.turretAngle1_6).withTimeout(.5), // set turret
            new InstantCommand(() -> hood.setHood(ThreeCargo.hoodLevel1_6.getValue())), // set hood
            new PushCargoSimple(shooter, transfer).withTimeout(3)), // shoot

        // drive and collect
        new InstantCommand(() -> drivetrain.resetOdometry(drivetrain.driveTo1Then2Traj.getInitialPose())), //
        parallel(
            driveTo1Then2.andThen(new InstantCommand(() -> drivetrain.driveSpeed(0, 0))), // drive then stop
            new CollectCargo(intake, transfer).until(transfer::areTopAndBottomBallCollected)), // collect

        // shoot
        parallel(
            new SetShooterRPM(shooter, ThreeCargo.shooterRPM2_6), // set shooter
            new SetTurretPosition(turret, ThreeCargo.turretAngle2_6).withTimeout(.5), // set turret
            new InstantCommand(() -> hood.setHood(ThreeCargo.hoodLevel2_6.getValue())), // set hood
            new PushCargoSimple(shooter, transfer).withTimeout(3) // shoot

        ));
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