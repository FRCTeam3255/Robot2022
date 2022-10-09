// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motion.BufferedTrajectoryPointStream;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.frcteam3255.preferences.SN_DoublePreference;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.RobotPreferences;
import frc.robot.RobotMap.*;
import frc.robot.RobotPreferences.*;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  // Creates Variables for Drivetrain
  public TalonFX leftLeadMotor;
  public TalonFX rightLeadMotor;
  public TalonFX leftFollowMotor;
  public TalonFX rightFollowMotor;

  private TalonFXConfiguration config;

  public SlewRateLimiter posSlewRateLimiter;
  public SlewRateLimiter negSlewRateLimiter;

  // Initializes Variables for Drivetrain
  public Drivetrain() {
    leftLeadMotor = new TalonFX(DrivetrainMap.LEFT_LEAD_MOTOR_CAN);
    rightLeadMotor = new TalonFX(DrivetrainMap.RIGHT_LEAD_MOTOR_CAN);
    leftFollowMotor = new TalonFX(DrivetrainMap.LEFT_FOLLOW_MOTOR_CAN);
    rightFollowMotor = new TalonFX(DrivetrainMap.RIGHT_FOLLOW_MOTOR_CAN);

    config = new TalonFXConfiguration();

    posSlewRateLimiter = new SlewRateLimiter(DrivetrainPrefs.drivePosSlewRateLimit.getValue());
    negSlewRateLimiter = new SlewRateLimiter(DrivetrainPrefs.driveNegSlewRateLimit.getValue());

    configure();
  }

  // Sets Drivetrain Variable's Default Settings
  public void configure() {

    config.slot0.closedLoopPeakOutput = DrivetrainPrefs.driveClosedLoopPeakOutput.getValue();
    config.slot0.kF = DrivetrainPrefs.driveF.getValue();
    config.slot0.kP = DrivetrainPrefs.driveP.getValue();
    config.slot0.kI = DrivetrainPrefs.driveI.getValue();
    config.slot0.kD = DrivetrainPrefs.driveD.getValue();

    // Left
    leftLeadMotor.configFactoryDefault();
    leftFollowMotor.configFactoryDefault();
    leftLeadMotor.configAllSettings(config);
    leftFollowMotor.configAllSettings(config);
    leftLeadMotor.setInverted(true);
    leftFollowMotor.setInverted(TalonFXInvertType.FollowMaster);
    leftLeadMotor.setSensorPhase(true);

    if (DrivetrainPrefs.driveBreakMode.getValue()) {
      leftFollowMotor.setNeutralMode(NeutralMode.Brake);
      leftLeadMotor.setNeutralMode(NeutralMode.Brake);
    } else {
      leftFollowMotor.setNeutralMode(NeutralMode.Coast);
      leftLeadMotor.setNeutralMode(NeutralMode.Coast);
    }

    leftFollowMotor.follow(leftLeadMotor);

    // Right
    rightLeadMotor.configFactoryDefault();
    rightFollowMotor.configFactoryDefault();
    rightLeadMotor.configAllSettings(config);
    rightFollowMotor.configAllSettings(config);
    rightLeadMotor.setInverted(false);
    rightFollowMotor.setInverted(TalonFXInvertType.FollowMaster);
    rightLeadMotor.setSensorPhase(false);
    if (DrivetrainPrefs.driveBreakMode.getValue()) {
      rightFollowMotor.setNeutralMode(NeutralMode.Brake);
      rightLeadMotor.setNeutralMode(NeutralMode.Brake);
    } else {
      rightFollowMotor.setNeutralMode(NeutralMode.Coast);
      rightLeadMotor.setNeutralMode(NeutralMode.Coast);
    }
    rightFollowMotor.follow(rightLeadMotor);

    posSlewRateLimiter = new SlewRateLimiter(DrivetrainPrefs.drivePosSlewRateLimit.getValue());
    negSlewRateLimiter = new SlewRateLimiter(DrivetrainPrefs.driveNegSlewRateLimit.getValue());

  }

  public void resetDrivetrainEncodersCount() {
    leftLeadMotor.setSelectedSensorPosition(0);
    rightLeadMotor.setSelectedSensorPosition(0);
  }

  public double getLeftEncoderCount() {
    return leftLeadMotor.getSelectedSensorPosition();
  }

  public double getRightEncoderCount() {
    return rightLeadMotor.getSelectedSensorPosition();
  }

  public double getAverageEncoderCount() {
    return (getLeftEncoderCount() + getRightEncoderCount()) / 2;
  }

  public double getLeftFeetDriven() {
    return getLeftEncoderCount() / DrivetrainPrefs.driveEncoderCountsPerFoot.getValue();
  }

  public double getRightFeetDriven() {
    return getRightEncoderCount() / DrivetrainPrefs.driveEncoderCountsPerFoot.getValue();
  }

  public double getAverageFeetDriven() {
    return getAverageEncoderCount() / DrivetrainPrefs.driveEncoderCountsPerFoot.getValue();
  }

  public double getLeftVelocity() {
    return leftLeadMotor.getSelectedSensorVelocity();
  }

  public double getRightVelocity() {
    return rightLeadMotor.getSelectedSensorVelocity();
  }

  public double getAverageVelocity() {
    return (getLeftVelocity() + getRightVelocity()) / 2;
  }

  public double getLeftFeetPerSecond() {
    double fps = getLeftVelocity(); // encoder counts per 100ms
    fps *= 10; // counts per second
    fps /= DrivetrainPrefs.driveEncoderCountsPerFoot.getValue(); // feet per second
    return fps;
  }

  public double getRightFeetPerSecond() {
    double fps = getRightVelocity(); // encoder counts per 100ms
    fps *= 10; // counts per second
    fps /= DrivetrainPrefs.driveEncoderCountsPerFoot.getValue(); // feet per second
    return fps;
  }

  public double getAverageFeetPerSecond() {
    double fps = getAverageVelocity(); // encoder counts per 100ms
    fps *= 10; // counts per second
    fps /= DrivetrainPrefs.driveEncoderCountsPerFoot.getValue(); // feet per second
    return fps;
  }

  // Method controls Drivetrain Motor speeds
  public void arcadeDrive(double a_speed, double a_turn) {
    double speed = a_speed * DrivetrainPrefs.arcadeSpeed.getValue();
    double turn = a_turn * DrivetrainPrefs.arcadeTurn.getValue();
    double multiplier = 1;

    if (RobotContainer.DriverStick.btn_LBump.get()) {
      multiplier = RobotPreferences.DrivetrainPrefs.arcadeLowSpeed.getValue();
    }

    if (RobotContainer.DriverStick.btn_RBump.get()) {
      multiplier = RobotPreferences.DrivetrainPrefs.arcadeHighSpeed.getValue();
    }

    leftLeadMotor.set(ControlMode.PercentOutput, speed * multiplier, DemandType.ArbitraryFeedForward, turn);
    rightLeadMotor.set(ControlMode.PercentOutput, speed * multiplier, DemandType.ArbitraryFeedForward, -turn);
  }

  // starts motion profile using seperate left and right trajectories, and ctre
  // ControlMode.MotionProfile
  public void startMotionProfile(BufferedTrajectoryPointStream pointsLeft, BufferedTrajectoryPointStream pointsRight) {
    configure();
    resetDrivetrainEncodersCount();

    leftLeadMotor.startMotionProfile(
        pointsLeft, DrivetrainPrefs.motionProfileMinBufferedPoints.getValue(), ControlMode.MotionProfile);
    rightLeadMotor.startMotionProfile(
        pointsRight, DrivetrainPrefs.motionProfileMinBufferedPoints.getValue(), ControlMode.MotionProfile);
  }

  public boolean isMotionProfileFinished() {
    return leftLeadMotor.isMotionProfileFinished() && rightLeadMotor.isMotionProfileFinished();
  }

  public void resetMotionProfile() {
    rightLeadMotor.set(ControlMode.PercentOutput, 0.0);
    leftLeadMotor.set(ControlMode.PercentOutput, 0.0);
    rightLeadMotor.clearMotionProfileTrajectories();
    leftLeadMotor.clearMotionProfileTrajectories();
    resetDrivetrainEncodersCount();
  }

  public void driveDistance(SN_DoublePreference a_inchesToDrive, SN_DoublePreference a_peakPercentOutput) {
    leftLeadMotor.configClosedLoopPeakOutput(0, a_peakPercentOutput.getValue());
    rightLeadMotor.configClosedLoopPeakOutput(0, a_peakPercentOutput.getValue());
    double position = a_inchesToDrive.getValue() * (DrivetrainPrefs.driveEncoderCountsPerFoot.getValue() / 12);
    leftLeadMotor.set(ControlMode.Position, position);
    rightLeadMotor.set(ControlMode.Position, position);
  }

  public double getLeftClosedLoopErrorInches() {
    return leftLeadMotor.getClosedLoopError() * (DrivetrainPrefs.driveEncoderCountsPerFoot.getValue() / 12);
  }

  public double getRightClosedLoopErrorInches() {
    return rightLeadMotor.getClosedLoopError() * (DrivetrainPrefs.driveEncoderCountsPerFoot.getValue() / 12);
  }

  public double getAverageClosedLoopErrorInches() {
    return (getLeftClosedLoopErrorInches() + getRightClosedLoopErrorInches()) / 2;
  }

  public void setBrakeMode() {
    leftFollowMotor.setNeutralMode(NeutralMode.Brake);
    leftLeadMotor.setNeutralMode(NeutralMode.Brake);

    rightFollowMotor.setNeutralMode(NeutralMode.Brake);
    rightLeadMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoastMode() {
    leftFollowMotor.setNeutralMode(NeutralMode.Coast);
    leftLeadMotor.setNeutralMode(NeutralMode.Coast);

    rightFollowMotor.setNeutralMode(NeutralMode.Coast);
    rightLeadMotor.setNeutralMode(NeutralMode.Coast);
  }

}
