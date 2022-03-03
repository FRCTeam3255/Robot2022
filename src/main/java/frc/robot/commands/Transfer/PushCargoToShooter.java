// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Transfer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.RobotPreferences.*;

import com.frcteam3255.preferences.SN_DoublePreference;

import frc.robot.RobotPreferences;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Transfer.TransferState;

public class PushCargoToShooter extends CommandBase {
  // Creates Shooter and Transfer Variables in PushCargotoShooter
  Shooter shooter;
  Transfer transfer;

  SN_DoublePreference outputEntranceSpeed;
  SN_DoublePreference outputBottomBeltSpeed;
  SN_DoublePreference outputTopBeltSpeed;

  /** Creates a new ShootCargo. */
  public PushCargoToShooter(Shooter sub_shooter, Transfer sub_transfer) {
    // Use addRequirements() here to declare subsystem dependencies.
    // Initializes PushCargoToShooter Variables
    shooter = sub_shooter;
    transfer = sub_transfer;

    addRequirements(transfer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    transfer.setTransferState(TransferState.SHOOTING);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (!shooter.isErrorAcceptable()) {
      if (!transfer.isTopBallCollected() && !transfer.isBottomBallCollected()) {
        outputEntranceSpeed = TransferPrefs.transferEntranceSpeed;
        outputBottomBeltSpeed = TransferPrefs.transferBeltSpeed;
        outputTopBeltSpeed = TransferPrefs.transferBeltSpeed;
      }

      if (!transfer.isTopBallCollected() && transfer.isBottomBallCollected()) {
        outputEntranceSpeed = TransferPrefs.transferEntranceSpeed;
        outputBottomBeltSpeed = TransferPrefs.transferBeltSpeed;
        outputTopBeltSpeed = TransferPrefs.transferBeltSpeed;
      }

      if (transfer.isTopBallCollected() && !transfer.isBottomBallCollected()) {
        outputEntranceSpeed = TransferPrefs.transferEntranceSpeed;
        outputTopBeltSpeed = RobotPreferences.zeroDoublePref;
        outputBottomBeltSpeed = TransferPrefs.transferBeltSpeed;
      }

      if (transfer.isTopBallCollected() && transfer.isBottomBallCollected()) {
        outputEntranceSpeed = RobotPreferences.zeroDoublePref;
        outputBottomBeltSpeed = RobotPreferences.zeroDoublePref;
        outputTopBeltSpeed = RobotPreferences.zeroDoublePref;
      }
    } else {
      outputEntranceSpeed = TransferPrefs.transferEntranceSpeed;
      outputBottomBeltSpeed = TransferPrefs.transferBeltSpeed;
      outputTopBeltSpeed = TransferPrefs.transferBeltSpeed;
    }

    transfer.setEntranceBeltMotorSpeed(outputEntranceSpeed);
    transfer.setBottomBeltMotorSpeed(outputBottomBeltSpeed);
    transfer.setTopBeltMotorSpeed(outputTopBeltSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transfer.setTopBeltMotorSpeed(RobotPreferences.zeroDoublePref);
    transfer.setBottomBeltMotorSpeed(RobotPreferences.zeroDoublePref);
    transfer.setEntranceBeltMotorSpeed(RobotPreferences.zeroDoublePref);
    transfer.setTransferState(TransferState.OFF);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
