// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Transfer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Transfer;

public class TransferCargo extends CommandBase {
  Transfer transfer;

  /** Creates a new Transfer. */
  public TransferCargo(Transfer sub_transfer) {
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(sub_transfer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    transfer.setTransferMotorSpeed(RobotPreferences.TransferPrefs.transferMotorSpeed.getValue());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transfer.setTransferMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
