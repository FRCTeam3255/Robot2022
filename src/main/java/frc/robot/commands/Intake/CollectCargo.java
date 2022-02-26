// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transfer;

public class CollectCargo extends CommandBase {
  Intake intake;
  Transfer transfer;

  /** Creates a new Collect. */
  public CollectCargo(Intake sub_intake, Transfer sub_transfer) {
    // Use addRequirements() here to declare subsystem dependencies.
    intake = sub_intake;
    transfer = sub_transfer;

    addRequirements(intake, transfer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Deploy the intake
    intake.deployIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Reject ball command
    // if (intake.ballColorMatchesAlliance() == false) {
    // // Reverse Motors
    // intake.setIntakeMotorSpeed(RobotPreferences.IntakePrefs.rejectSpeed.getValue());
    // } else {

    // Motor Controlling
    // Top Belt Motors
    if (transfer.isTopBallCollected() == true) {
      transfer.setTopBeltMotorSpeed(0);

    } else if (transfer.isTopBallCollected() == false) {
      // Make the Top Belt Move
      transfer.setTopBeltMotorSpeed(RobotPreferences.TransferPrefs.transferSpeed.getValue());
    }

    // Bottom Belt Motors
    if (transfer.isBottomBallCollected() == true && transfer.isTopBallCollected() == true) {
      // Retract the intake
      intake.retractIntake();

      transfer.setBottomBeltMotorSpeed(0);
      transfer.setEntranceBeltMotorSpeed(0);
      intake.setIntakeMotorSpeed(0);

    } else {
      // Deploy the intake if it isn't already deployed
      intake.deployIntake();

      // Set all bottom motors to Move
      transfer.setBottomBeltMotorSpeed(RobotPreferences.TransferPrefs.transferSpeed.getValue());
      transfer.setEntranceBeltMotorSpeed(RobotPreferences.TransferPrefs.transferSpeed.getValue());
      intake.setIntakeMotorSpeed(RobotPreferences.IntakePrefs.collectSpeed.getValue());
    }
  }
  // }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakeMotorSpeed(0);
    transfer.setTopBeltMotorSpeed(0);
    transfer.setBottomBeltMotorSpeed(0);
    transfer.setEntranceBeltMotorSpeed(0);
    // Retract Intake
    intake.retractIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
