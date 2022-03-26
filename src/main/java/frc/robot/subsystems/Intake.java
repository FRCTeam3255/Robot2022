// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.frcteam3255.components.SN_DoubleSolenoid;
import com.frcteam3255.preferences.SN_DoublePreference;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.RobotMap.*;

public class Intake extends SubsystemBase {

  // Creates the Intake motors
  private TalonFX intakeMotor;
  private SN_DoubleSolenoid intakePiston;

  // Shuffleboard
  private ShuffleboardTab tab;
  private ShuffleboardLayout intakeComponentsLayout;

  private int shuffleboardWidgetWidth;
  private int shuffleboardWidgetHeight;

  // Initializes Intake Variables
  public Intake() {
    intakeMotor = new TalonFX(IntakeMap.INTAKE_MOTOR_CAN);
    intakePiston = new SN_DoubleSolenoid(RobotMap.PRIMARY_PCM, PneumaticsModuleType.CTREPCM,
        IntakeMap.INTAKE_SOLENOID_PCM_A,
        IntakeMap.INTAKE_SOLENOID_PCM_B);

    tab = Shuffleboard.getTab("Intake");
    intakeComponentsLayout = tab.getLayout("Intake Components", BuiltInLayouts.kList).withSize(2, 2);

    shuffleboardWidgetWidth = 2;
    shuffleboardWidgetHeight = 1;
    configure();
  }

  // Sets factory default (configure it)
  public void configure() {
    intakeMotor.configFactoryDefault();

    intakeMotor.setInverted(true);
    intakePiston.setInverted(true);

  }

  // Resets Intake Motor Encoder Count
  public void resetIntakeEncoderCount() {
    intakeMotor.setSelectedSensorPosition(0);
  }

  // Sets Intake Motor Speed
  public void setIntakeMotorSpeed(SN_DoublePreference a_speed) {
    double speed = a_speed.getValue();

    intakeMotor.set(ControlMode.PercentOutput, speed);
  }

  // Returns positions of Intake Motors
  public double getIntakeMotorCount() {
    return intakeMotor.getSelectedSensorPosition();
  }

  public boolean isIntakeDeployed() {
    return intakePiston.isDeployed();
  }

  // Deploys Intake Solenoid
  public void deployIntake() {
    intakePiston.setDeployed();
  }

  // Retracts Intake Solenoid
  public void retractIntake() {
    intakePiston.setRetracted();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Intake Components
    intakeComponentsLayout.add("Intake Motor", getIntakeMotorCount()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    intakeComponentsLayout.add("IntakeMotorOutputPercent", intakeMotor.getMotorOutputPercent()).withSize(
        shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    intakeComponentsLayout.add("Intake Solenoid", isIntakeDeployed()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);

  }
}