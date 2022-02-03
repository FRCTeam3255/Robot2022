// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
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
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setIntakeMotorSpeed(RobotPreferences.IntakePrefs.collectSpeed.getValue());
    transfer.setTransferMotorSpeed(RobotPreferences.TransferPrefs.transferSpeed.getValue());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakeMotorSpeed(0);
    transfer.setTransferMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
