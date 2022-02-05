// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.frcteam3255.joystick.SN_DualActionStick;
import com.frcteam3255.joystick.SN_Extreme3DStick;

import frc.robot.RobotPreferences;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Hood.*;
import frc.robot.commands.Turret.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Climber.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Joysticks
  public static final SN_DualActionStick DriverStick = new SN_DualActionStick(RobotMap.ControllerMap.DRIVER_STICK);
  public static final SN_DualActionStick coDriverStick = new SN_DualActionStick(RobotMap.ControllerMap.CODRIVER_STICK);

  // Subsystems
  private final Drivetrain sub_drivetrain = new Drivetrain();
  private final Hood sub_hood = new Hood();
  private final Turret sub_turret = new Turret();
  private final Intake sub_intake = new Intake();
  private final Shooter sub_shooter = new Shooter();
  private final Climber sub_climber = new Climber();
  private final Transfer sub_transfer = new Transfer();

  // Drivetrain Commands
  private final Drive com_drive = new Drive(sub_drivetrain);

  // Hood Commands
  private final ShallowHood com_shallow_hood = new ShallowHood(sub_hood);
  private final SteepenHood com_steepen_hood = new SteepenHood(sub_hood);

  // Turret Commands
  private final ManualRotate com_manualRotate = new ManualRotate(sub_turret);

  // Shooter Commands
  private final ShootCargo com_shootCargo = new ShootCargo(sub_shooter, sub_transfer);
  // Transfer Commands

  // Intake Commands
  private final CollectCargo com_collect = new CollectCargo(sub_intake, sub_transfer);
  private final RetractIntake com_retractIntake = new RetractIntake(sub_intake);
  private final DeployIntake com_deployIntake = new DeployIntake(sub_intake);

  // Vision Commands

  // Climber Commands

  private final Climb com_climb = new Climb(sub_climber);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureDashboardButtons();
    sub_drivetrain.setDefaultCommand(com_drive);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    DriverStick.btn_RTrig.whileHeld(com_shootCargo);
    DriverStick.btn_LTrig.whileHeld(com_collect);

    coDriverStick.btn_Y.whenPressed(com_retractIntake);
    coDriverStick.btn_X.whenPressed(com_deployIntake);
    coDriverStick.POV_West.whileHeld(com_manualRotate);

    coDriverStick.btn_A.whenPressed(com_steepen_hood);
    coDriverStick.btn_B.whenPressed(com_shallow_hood);

    // btn_LStick can become btn_RStick for dominant hand
    coDriverStick.btn_LStick.whileHeld(com_climb);
  }

  /**
   * Use this method to define your dashboard buttons
   */
  private void configureDashboardButtons() {
    SmartDashboard.putData("Reset Climber Encoders",
        new InstantCommand(sub_climber::resetClimberEncoderCount, sub_climber));
    SmartDashboard.putData("Reset Drivetrain Encoders",
        new InstantCommand(sub_drivetrain::resetDrivetrainEncodersCount, sub_drivetrain));
    SmartDashboard.putData("Reset Intake Encoders",
        new InstantCommand(sub_intake::resetIntakeEncoderCount, sub_intake));
    SmartDashboard.putData("Reset Turret Encoders",
        new InstantCommand(sub_turret::resetTurretEncoderCounts, sub_turret));
    SmartDashboard.putData("Reset Shooter Encoders",
        new InstantCommand(sub_shooter::resetShooterEncoderCounts, sub_shooter));

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