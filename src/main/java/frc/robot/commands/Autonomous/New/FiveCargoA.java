// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous.New;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotPreferences.AutoPrefs.FiveCargo;
import frc.robot.commands.Autonomous.SetShooterRPM;
import frc.robot.commands.Intake.DumbCollect;
import frc.robot.commands.Transfer.PushCargoSimple;
import frc.robot.commands.Turret.SetTurretAngle;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Turret;

public class FiveCargoA extends SequentialCommandGroup {

  Drivetrain drivetrain;
  Shooter shooter;
  Turret turret;
  Hood hood;
  Transfer transfer;
  Intake intake;
  Climber climber;

  /** Creates a new AutoThreeCargoPP. */
  public FiveCargoA(
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

    RamseteCommand fenderTo1Then2 = drivetrain.getRamseteCommand(drivetrain.fenderTo1Then2Traj);
    RamseteCommand ball2ToTerminal = drivetrain.getRamseteCommand(drivetrain.ball2ToTerminalTraj);

    addCommands(
        // config drivetrain
        new InstantCommand(() -> sub_drivetrain.setBrakeMode()),
        new InstantCommand(() -> drivetrain.resetOdometry(drivetrain.fenderTo1Then2Traj.getInitialPose())),

        // drive and configure shooter on the way
        parallel(
            new DumbCollect(intake, transfer).until(() -> fenderTo1Then2.isFinished()),
            new SetShooterRPM(shooter, FiveCargo.shooterRPM2_7), // set shooter
            new SetTurretAngle(turret, FiveCargo.turretAngle2_7).withTimeout(.5), // set turret
            new InstantCommand(() -> hood.setHood(FiveCargo.hoodLevel2_7.getValue())), // set hood
            fenderTo1Then2.andThen(new InstantCommand(() -> drivetrain.driveSpeed(0, 0)))),

        new PushCargoSimple(shooter, transfer).until(() -> transfer.areTopAndBottomBallNotCollected()), // shoot

        parallel(
            new DumbCollect(intake, transfer).until(() -> transfer.areTopAndBottomBallCollected()),
            new SetShooterRPM(shooter, FiveCargo.shooterRPM3_7),
            new SetTurretAngle(turret, FiveCargo.turretAngle3_7).withTimeout(.5),
            new InstantCommand(() -> hood.setHood(FiveCargo.hoodLevel3_7.getValue())),
            new InstantCommand(() -> drivetrain.resetOdometry(drivetrain.ball2ToTerminalTraj.getInitialPose()))
                .andThen(ball2ToTerminal
                    .andThen(new InstantCommand(() -> drivetrain.driveSpeed(0, 0))))

        ),

        new PushCargoSimple(shooter, transfer));

  }
}

/*
 * Plan for auto:
 * 
 * Start at fender by cargo 1
 * shoot preloaded cargo
 * drive and collect cargo 1 and 2
 * shoot both cargo
 *
 */