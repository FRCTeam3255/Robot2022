// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous.AutoTwoCargoA;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.SetHoodPosition;
import frc.robot.commands.Autonomous.SetShooterRPM;
import frc.robot.commands.Drivetrain.DriveMotionProfile;
import frc.robot.commands.Intake.CollectCargo;
import frc.robot.commands.Transfer.PushCargoToShooter;
import frc.robot.commands.Turret.SetTurretPosition;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Turret;
import static frc.robot.RobotPreferences.AutoPrefs.TwoCargoA.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoTwoCargo extends SequentialCommandGroup {

  Drivetrain drivetrain;
  Shooter shooter;
  Turret turret;
  Hood hood;
  Transfer transfer;
  Intake intake;

  DriveMotionProfile driveToOneOrThree;
  SetShooterRPM setShooterRPM;
  SetTurretPosition setTurretPosition;
  SetHoodPosition setHoodPosition;
  CollectCargo collectCargo;
  PushCargoToShooter shootBall;

  /** Creates a new AutoTwoCargo. */
  public AutoTwoCargo(Drivetrain sub_drivetrain, Shooter sub_shooter, Turret sub_turret, Hood sub_hood,
      Transfer sub_transfer, Intake sub_intake, String a_leftPath, String a_rightPath) {

    drivetrain = sub_drivetrain;
    shooter = sub_shooter;
    turret = sub_turret;
    hood = sub_hood;
    transfer = sub_transfer;
    intake = sub_intake;

    driveToOneOrThree = new DriveMotionProfile(drivetrain, a_leftPath, a_rightPath);
    setShooterRPM = new SetShooterRPM(shooter, auto1shooterRPM);
    setTurretPosition = new SetTurretPosition(turret, auto1turretAngle);
    setHoodPosition = new SetHoodPosition(hood, auto1hoodSteep);
    collectCargo = new CollectCargo(intake, transfer);
    shootBall = new PushCargoToShooter(shooter, transfer);

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        parallel(driveToOneOrThree, setShooterRPM, setTurretPosition, setHoodPosition, collectCargo),
        shootBall

    );
  }
}
