// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.frcteam3255.utils.SN_Math;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.*;
import static frc.robot.RobotPreferences.*;

public class Shooter extends SubsystemBase {

  // CREATES NEW MOTOR
  private TalonFX leadMotor;
  private TalonFX followMotor;

  private TalonFXConfiguration config = new TalonFXConfiguration();

  double goalRPM;
  boolean isHighHub;

  // Shuffleboard
  private ShuffleboardTab tab;
  private ShuffleboardLayout shooterRPMShuffleboardLayout;
  private ShuffleboardLayout shooterShuffleboardBooleansLayout;
  private ShuffleboardLayout shooterShuffleboardMotorsLayout;
  private ShuffleboardLayout shooterShuffleboardMotorsEncodersLayout;

  private int shuffleboardWidgetWidth;
  private int shuffleboardWidgetHeight;

  /**
   * Creates new shooter
   */
  public Shooter() {
    leadMotor = new TalonFX(ShooterMap.LEFT_MOTOR_CAN);
    followMotor = new TalonFX(ShooterMap.RIGHT_MOTOR_CAN);

    config = new TalonFXConfiguration();

    configure();

    goalRPM = 0;

    tab = Shuffleboard.getTab("Shooter");
    shooterShuffleboardBooleansLayout = tab.getLayout("Shooter Booleans").withSize(8, 2).withPosition(0, 4);
    shooterShuffleboardMotorsLayout = tab.getLayout("Shooter Motors").withSize(3, 4).withPosition(0, 0);
    shooterShuffleboardMotorsEncodersLayout = tab.getLayout("Shooter Encoders").withSize(3, 3).withPosition(4, 0);
    shooterRPMShuffleboardLayout = tab.getLayout("Shooter RPM").withSize(4, 4).withPosition(7, 0);
  }

  /**
   * Configures the shooter's motors
   */
  public void configure() {
    leadMotor.configFactoryDefault();
    followMotor.configFactoryDefault();

    config.slot0.kF = ShooterPrefs.shooterF.getValue();
    config.slot0.kP = ShooterPrefs.shooterP.getValue();
    config.slot0.kI = ShooterPrefs.shooterI.getValue();
    config.slot0.kD = ShooterPrefs.shooterD.getValue();

    leadMotor.configAllSettings(config);
    followMotor.configAllSettings(config);

    followMotor.follow(leadMotor);

    leadMotor.setInverted(false);
    followMotor.setInverted(InvertType.OpposeMaster);

    leadMotor.setSensorPhase(false);
    followMotor.setSensorPhase(true);
  }

  /**
   * Resets the shooter's motors' encoder counts to zero
   */
  public void resetShooterEncoderCounts() {
    leadMotor.setSelectedSensorPosition(0);
    followMotor.setSelectedSensorPosition(0);
  }

  /**
   * 
   * @return Shooter Motor Encoder Counts
   */
  public double getShooterEncoderCount() {
    return leadMotor.getSelectedSensorPosition();
  }

  // Sets/Controls Shooter Motor speeds
  public void setShooterPercentOutput(double a_speed) {
    leadMotor.set(ControlMode.PercentOutput, a_speed);
  }

  /**
   * Sets the shooter RPM
   * 
   * @param a_rpm RPM to set motor to
   */
  public void setShooterRPM(double a_rpm) {
    double velocity = SN_Math.RPMToVelocity(a_rpm, SN_Math.TALONFX_ENCODER_PULSES_PER_COUNT);
    leadMotor.set(ControlMode.Velocity, velocity);
  }

  /**
   * 
   * @return Shooter RPM
   */
  public double getShooterRPM() {
    return SN_Math.velocityToRPM(getShooterVelocity(), SN_Math.TALONFX_ENCODER_PULSES_PER_COUNT);
  }

  /**
   * 
   * @return Shooter velocity (encoder counts per 100ms)
   */
  private double getShooterVelocity() {
    return leadMotor.getSelectedSensorVelocity();
  }

  private double getErrorRPM() {
    return SN_Math.velocityToRPM(leadMotor.getClosedLoopError(), SN_Math.TALONFX_ENCODER_PULSES_PER_COUNT);
  }

  public boolean isShooterUpToSpeed() {
    return ShooterPrefs.shooterAcceptableErrorRPM.getValue() > Math.abs(getErrorRPM());

    // return true if the acceptable error (in rpm) is greater than the actual error
    // (converted to rpm)
  }

  public void setGoalRPM(double a_goalRPM) {
    goalRPM = a_goalRPM;
  }

  public double getGoalRPM() {
    return goalRPM;
  }

  public void setGoalUpperHub() {
    isHighHub = true;
  }

  public void setGoalLowerHub() {
    isHighHub = false;
  }

  public boolean isGoalHighHub() {
    return isHighHub;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Shuffleboard

    // Shooter Booleans
    shooterShuffleboardBooleansLayout.add("Is Shooter Goal High Hub", isGoalHighHub()).withSize(3, 2).withPosition(0,
        4);
    shooterShuffleboardBooleansLayout.add("Is Shooter Up to Speed", isShooterUpToSpeed()).withSize(3, 2).withPosition(4,
        4);

    // Shooter Motors
    shooterShuffleboardMotorsLayout.add("ShooterFollowMotorSpeed", followMotor.getMotorOutputPercent())
        .withSize(shuffleboardWidgetWidth, shuffleboardWidgetHeight);
    shooterShuffleboardMotorsLayout.add("ShooterLeadMotorSpeed", leadMotor.getMotorOutputPercent())
        .withSize(shuffleboardWidgetWidth, shuffleboardWidgetHeight);

    // Shooter Motor Encoder Counts
    shooterShuffleboardMotorsEncodersLayout.add("Shooter Motor Encoder Count", getShooterEncoderCount())
        .withSize(shuffleboardWidgetWidth, shuffleboardWidgetHeight);

    // Shooter RPM
    shooterRPMShuffleboardLayout.add("Shooter Error RPM", getErrorRPM()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    shooterRPMShuffleboardLayout.add("Shooter Goal RPM", getGoalRPM()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    shooterRPMShuffleboardLayout.add("Shooter RPM", getShooterRPM()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);

  }
}
