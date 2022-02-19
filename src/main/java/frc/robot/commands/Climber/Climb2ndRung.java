// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import javax.swing.text.Position;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.frcteam3255.joystick.SN_DualActionStick;

public class Climb2ndRung extends CommandBase {
    Climber climber;

    /** Creates a new Climb. */
    public Climb2ndRung(Climber sub_climber) {
        // Use addRequirements() here to declare subsystem dependencies.
        climber = sub_climber;
        addRequirements(climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Climber brake disengages when we press the button assigned to the climber
        climber.unlockClimberPivotPiston();

        // 2nd Climber Piston Extended Position is set and executed
        // Check if we are going BACKWARD or FORWARD because I have NO CLUE - Alice
        climber.pivotClimberPistonForward();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Returns 2nd climber Piston to first position
        climber.setClimberSpeed(0);
        climber.lockClimberPivotPiston();

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}