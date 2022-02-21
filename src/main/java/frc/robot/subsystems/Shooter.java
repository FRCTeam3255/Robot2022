// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;

public class Shooter extends SubsystemBase {

  // CREATES NEW MOTOR
  private TalonFX leadMotor;
  private TalonFX followMotor;

  private TalonFXConfiguration config = new TalonFXConfiguration();

  // LINKS TO ROBOT MAP
  public Shooter() {
    leadMotor = new TalonFX(RobotMap.ShooterMap.LEFT_MOTOR_CAN);
    followMotor = new TalonFX(RobotMap.ShooterMap.RIGHT_MOTOR_CAN);

    config = new TalonFXConfiguration();

    configure();
  }

  // Configures Shooter Motor's factory Defaults
  public void configure() {
    leadMotor.configFactoryDefault();
    followMotor.configFactoryDefault();

    config.slot0.kF = RobotPreferences.ShooterPrefs.shooterF.getValue();
    config.slot0.kP = RobotPreferences.ShooterPrefs.shooterP.getValue();
    config.slot0.kI = RobotPreferences.ShooterPrefs.shooterI.getValue();
    config.slot0.kD = RobotPreferences.ShooterPrefs.shooterD.getValue();

    leadMotor.configAllSettings(config);
    followMotor.configAllSettings(config);

    followMotor.follow(leadMotor);

    leadMotor.setInverted(false);
    followMotor.setInverted(InvertType.OpposeMaster);

    leadMotor.setSensorPhase(false);
    followMotor.setSensorPhase(true);
  }

  // RESETS COUNT FOR ENCODERS
  public void resetShooterEncoderCounts() {
    leadMotor.setSelectedSensorPosition(0);
    followMotor.setSelectedSensorPosition(0);
  }

  // Gets and returns shooter's encoder counts
  public double getShooterEncoderCount() {
    return leadMotor.getSelectedSensorPosition();
  }

  // Sets/Controls Shooter Motor speeds
  public void setShooterSpeed(double a_speed) {
    double speed = a_speed;
    leadMotor.set(ControlMode.PercentOutput, speed);
  }

  private void setShooterVelocity(double a_velocity) {
    leadMotor.set(ControlMode.Velocity, a_velocity);
  }

  private double velocityToRPM(double a_velocity) {
    double rpm = a_velocity; // counts per 100ms
    rpm *= 10; // counts per 1s
    rpm *= 60; // counts per 1m
    rpm /= RobotPreferences.ShooterPrefs.shooterEncoderCountsPerWheelRotation.getValue(); // rotations per minute
    return rpm;
  }

  private double RPMToVelocity(double a_rpm) {
    double velocity = a_rpm; // rotations per 1m
    velocity *= RobotPreferences.ShooterPrefs.shooterEncoderCountsPerWheelRotation.getValue(); // counts 1m
    velocity /= 60; // counts per 1s
    velocity /= 10; // counts per 100ms
    return velocity;
  }

  public void setShooterRPM(double a_rpm) {
    double rpm = RPMToVelocity(a_rpm);
    setShooterVelocity(rpm);
  }

  public double getShooterRPM() {
    return velocityToRPM(getShooterVelocity());
  }

  private double getShooterVelocity() {
    return leadMotor.getSelectedSensorVelocity();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Shooter Left Motor", getShooterEncoderCount());
    SmartDashboard.putNumber("Shooter Velocity", getShooterVelocity());
  }
}