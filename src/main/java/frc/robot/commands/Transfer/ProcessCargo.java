// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Transfer;

import com.frcteam3255.preferences.SN_DoublePreference;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Transfer.TransferState;

import static frc.robot.RobotPreferences.*;
import static frc.robot.RobotPreferences.TransferPrefs.*;

public class ProcessCargo extends CommandBase {
  Transfer transfer;

  SN_DoublePreference outputEntranceSpeed;
  SN_DoublePreference outputBottomBeltSpeed;
  SN_DoublePreference outputTopBeltSpeed;

  boolean stateOverride;

  /** Creates a new ProcessCargo. */
  public ProcessCargo(Transfer sub_transfer) {
    transfer = sub_transfer;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!transfer.isTopBallCollected() && !transfer.isBottomBallCollected()) {
      outputEntranceSpeed = transferEntranceSpeed;
      outputBottomBeltSpeed = transferBeltSpeed;
      outputTopBeltSpeed = transferBeltSpeed;
    }

    if (!transfer.isTopBallCollected() && transfer.isBottomBallCollected()) {
      outputEntranceSpeed = transferEntranceSpeed;
      outputBottomBeltSpeed = transferBeltSpeed;
      outputTopBeltSpeed = transferBeltSpeed;
    }

    if (transfer.isTopBallCollected() && !transfer.isBottomBallCollected()) {
      outputEntranceSpeed = transferEntranceSpeed;
      outputTopBeltSpeed = zeroDoublePref;
      outputBottomBeltSpeed = transferBeltSpeed;
    }

    if (transfer.isTopBallCollected() && transfer.isBottomBallCollected()) {
      outputEntranceSpeed = zeroDoublePref;
      outputBottomBeltSpeed = zeroDoublePref;
      outputTopBeltSpeed = zeroDoublePref;
    }

    switch (transfer.getTransferState()) {
      case SHOOTING:
        stateOverride = true;
        break;
      case PROCESSING:
        stateOverride = false;
        break;
      case OFF:
        transfer.setTransferState(TransferState.PROCESSING);
        stateOverride = false;
        break;
    }

    if (!stateOverride) {
      transfer.setEntranceBeltMotorSpeed(outputEntranceSpeed);
      transfer.setBottomBeltMotorSpeed(outputBottomBeltSpeed);
      transfer.setTopBeltMotorSpeed(outputTopBeltSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transfer.setEntranceBeltMotorSpeed(zeroDoublePref);
    transfer.setBottomBeltMotorSpeed(zeroDoublePref);
    transfer.setTopBeltMotorSpeed(zeroDoublePref);

    if (!stateOverride) {
      transfer.setTransferState(TransferState.OFF);
    }

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
