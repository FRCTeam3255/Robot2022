// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
  Climber climber;

  /** Creates a new Climb. */
  public Climb(Climber sub_climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    climber = sub_climber;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Climber Break disengages when we press the button assigned to the climber
    climber.unlockClimber();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Climber Speed is set depending joystick input
    double speed = RobotContainer.coDriverStick.getLeftStickY();

    climber.setClimberSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops Climber when joystick is released and applies brake
    climber.setClimberSpeed(0);
    climber.lockClimber();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}