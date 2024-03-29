// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Transfer;

import com.frcteam3255.preferences.SN_DoublePreference;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotPreferences.TransferPrefs;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Transfer.TransferState;
import static frc.robot.RobotPreferences.*;

public class PushCargoSimple extends CommandBase {
  Shooter shooter;
  Transfer transfer;

  SN_DoublePreference outputEntranceSpeed;
  SN_DoublePreference outputBottomBeltSpeed;
  SN_DoublePreference outputTopBeltSpeed;

  boolean shooterReady;
  int buffer;

  /** Creates a new PushCargoSimple. */
  public PushCargoSimple(Shooter sub_shooter, Transfer sub_transfer) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = sub_shooter;
    transfer = sub_transfer;

    addRequirements(transfer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooterReady = false;
    buffer = 0;
    transfer.setTransferState(TransferState.SHOOTING);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    buffer++;

    if (buffer > ShooterPrefs.shooterBufferLoops.getValue() && shooter.isShooterUpToSpeed()) {
      shooterReady = true;
    }

    if (buffer > ShooterPrefs.shooterBufferLoops.getValue() && !shooter.isShooterUpToSpeed()) {
      shooterReady = false;
    }

    outputEntranceSpeed = TransferPrefs.transferEntranceSpeed;
    outputBottomBeltSpeed = TransferPrefs.transferBeltSpeed;
    outputTopBeltSpeed = TransferPrefs.transferBeltSpeed;

    if (!shooterReady) {

      if (transfer.isTopBallCollected()) {
        outputTopBeltSpeed = zeroDoublePref;

        if (transfer.isBottomBallCollected()) {
          outputEntranceSpeed = zeroDoublePref;
          outputBottomBeltSpeed = zeroDoublePref;
          outputTopBeltSpeed = zeroDoublePref;
        }
      }
    }

    transfer.setEntranceBeltMotorSpeed(outputEntranceSpeed);
    transfer.setBottomBeltMotorSpeed(outputBottomBeltSpeed);
    transfer.setTopBeltMotorSpeed(outputTopBeltSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transfer.setEntranceBeltMotorSpeed(zeroDoublePref);
    transfer.setBottomBeltMotorSpeed(zeroDoublePref);
    transfer.setTopBeltMotorSpeed(zeroDoublePref);
    transfer.setTransferState(TransferState.OFF);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
