// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotPreferences;
import frc.robot.RobotMap;

public class Turret extends SubsystemBase {
  /** Creates a new Turret. */

  private TalonFX turretMotor;
  private TalonFXConfiguration config;

  // LINKS TO ROBOT MAP
  public Turret() {
    // Creates Turret Variables
    turretMotor = new TalonFX(RobotMap.TurretMap.TURRET_MOTOR_CAN);
    config = new TalonFXConfiguration();

    configure();
  }

  // Sets Turret Variable Factory Defaults
  public void configure() {
    // first we reset all the settings
    turretMotor.configFactoryDefault();

    // then we set the config settings
    config.slot0.allowableClosedloopError = RobotPreferences.TurretPrefs.turretMaxAllowableError.getValue();
    config.slot0.closedLoopPeakOutput = RobotPreferences.TurretPrefs.turretClosedLoopPeakOutput.getValue();
    config.slot0.kF = RobotPreferences.TurretPrefs.turretF.getValue();
    config.slot0.kP = RobotPreferences.TurretPrefs.turretP.getValue();
    config.slot0.kI = RobotPreferences.TurretPrefs.turretI.getValue();
    config.slot0.kD = RobotPreferences.TurretPrefs.turretD.getValue();

    // then we apply the config settings (which also sets every other setting, not
    // just the ones we set)
    turretMotor.configAllSettings(config);

    // then we set more stuff that wasn't in the config

    // soft limit
    turretMotor.configForwardSoftLimitThreshold(RobotPreferences.TurretPrefs.turretMaxAngleDegrees.getValue()
        * RobotPreferences.TurretPrefs.turretEncoderCountsPerDegree.getValue());
    turretMotor.configReverseSoftLimitThreshold(RobotPreferences.TurretPrefs.turretMinAngleDegrees.getValue()
        * RobotPreferences.TurretPrefs.turretEncoderCountsPerDegree.getValue());
    turretMotor.configForwardSoftLimitEnable(true);
    turretMotor.configReverseSoftLimitEnable(true);

    turretMotor.setInverted(false);
    turretMotor.setSensorPhase(false);

    turretMotor.setNeutralMode(NeutralMode.Brake);
  }

  // Resets Encoder Counts
  public void resetTurretEncoderCounts() {
    turretMotor.setSelectedSensorPosition(0);
  }

  // GETS AND RETURNS COUNT FOR ENCONDERS
  public double getTurretMotorEncoderCounts() {
    return turretMotor.getSelectedSensorPosition();
  }

  public void setTurretSpeed(double a_rotate) {
    double rotate = a_rotate;

    turretMotor.set(ControlMode.PercentOutput, rotate);
  }

  // gets the turret angle in degrees
  public double getTurretAngle() {
    return getTurretMotorEncoderCounts() / RobotPreferences.TurretPrefs.turretEncoderCountsPerDegree.getValue();
  }

  // sets the turret angle in degrees
  public void setTurretAngle(double a_degrees) {
    double position = a_degrees * RobotPreferences.TurretPrefs.turretEncoderCountsPerDegree.getValue();
    turretMotor.set(ControlMode.Position, position);
  }

  // gets the difference between where the motor wants to be, and where it is, in
  // encoder counts
  public double getTurretClosedLoopError() {
    return turretMotor.getClosedLoopError();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Turret Encoder", getTurretMotorEncoderCounts());
    SmartDashboard.putNumber("Turret Angle", getTurretAngle());
    SmartDashboard.putNumber("Turret Closed Loop Error", getTurretClosedLoopError());
  }
}
