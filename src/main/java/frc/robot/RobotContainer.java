// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.frcteam3255.joystick.SN_DualActionStick;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Shooter.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Joysticks
  public static final SN_DualActionStick DriverStick = new SN_DualActionStick(RobotMap.ControllerMap.DRIVER_STICK);

  // Subsystems
  private final Drivetrain sub_drivetrain = new Drivetrain();
  private final Intake sub_intake = new Intake();
  private final Shooter sub_shooter = new Shooter();

  // Drivetrain Commands
  private final Drive com_drive = new Drive(sub_drivetrain);

  // Hood Commands

  // Turret Commands

  // Shooter Commands
  private final ShootCargo com_shoot_cargo = new ShootCargo(sub_shooter);
  // Transfer Commands

  // Intake Commands

  private final Collect com_collect = new Collect(sub_intake);

  // Vision Commands

  // Climber Commands

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    sub_drivetrain.setDefaultCommand(com_drive);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Collect Button

    DriverStick.btn_LTrig.whileHeld(com_collect);

    // Button 2
    // Button 3
    // Button 4
    // Button 5
    // Button 6
    // Button 7
    // Button 8
    // Button 9
    // Button 10
    // Button 11
    // Button 12
    DriverStick.btn_RTrig.whileHeld(com_shoot_cargo);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
