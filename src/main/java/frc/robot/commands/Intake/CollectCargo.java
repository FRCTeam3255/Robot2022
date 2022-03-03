// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Transfer.TransferState;

import static frc.robot.RobotPreferences.*;

import com.frcteam3255.preferences.SN_DoublePreference;

public class CollectCargo extends CommandBase {
  Intake intake;
  Transfer transfer;

  int intakeRejectLatch;
  int entranceRejectLatch;

  SN_DoublePreference outputIntakeSpeed;
  SN_DoublePreference outputEntranceSpeed;
  SN_DoublePreference outputBottomBeltSpeed;
  SN_DoublePreference outputTopBeltSpeed;

  boolean stateOverride;

  /** Creates a new CollectBall. */
  public CollectCargo(Intake sub_intake, Transfer sub_transfer) {
    intake = sub_intake;
    transfer = sub_transfer;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.deployIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    outputEntranceSpeed = TransferPrefs.transferEntranceSpeed;
    outputBottomBeltSpeed = TransferPrefs.transferBeltSpeed;
    outputTopBeltSpeed = TransferPrefs.transferBeltSpeed;

    if (transfer.isTopBallCollected()) {
      outputTopBeltSpeed = RobotPreferences.zeroDoublePref;
    }

    if (transfer.isTopBallCollected() && transfer.isBottomBallCollected()) {
      outputEntranceSpeed = RobotPreferences.zeroDoublePref;
      outputBottomBeltSpeed = RobotPreferences.zeroDoublePref;
      outputTopBeltSpeed = RobotPreferences.zeroDoublePref;
    }

    if (intake.isProximity()) {
      if (intake.ballColorMatchesAlliance()) {
        intakeRejectLatch -= TransferPrefs.transferRejectLatchTimeLoops.getValue();
        entranceRejectLatch = 0;
      } else {
        intakeRejectLatch = IntakePrefs.intakeRejectLatchTimeLoops.getValue();
        entranceRejectLatch = TransferPrefs.transferRejectLatchTimeLoops.getValue();
      }
    }

    if (intakeRejectLatch > 0) {
      outputIntakeSpeed = IntakePrefs.intakeRejectSpeed;
      intakeRejectLatch--;
    }

    if (entranceRejectLatch > 0) {
      outputEntranceSpeed = TransferPrefs.transferEntranceRejectSpeed;
      entranceRejectLatch--;
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

    intake.setIntakeMotorSpeed(outputIntakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakeMotorSpeed(RobotPreferences.zeroDoublePref);
    transfer.setEntranceBeltMotorSpeed(RobotPreferences.zeroDoublePref);
    transfer.setBottomBeltMotorSpeed(RobotPreferences.zeroDoublePref);
    transfer.setTopBeltMotorSpeed(RobotPreferences.zeroDoublePref);

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
