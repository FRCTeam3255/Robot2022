/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.frcteam3255.preferences.SN_DoublePreference;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.*;

import static frc.robot.RobotPreferences.*;

public class Transfer extends SubsystemBase {
  /**
   * Creates a new Transfer.
   */
  // Creates Transfer Variables
  private TalonFX topBeltMotor;
  private TalonFX bottomBeltMotor;
  private TalonFX entranceBeltMotor;

  private DigitalInput transferTopLeftLimitSwitch;
  private DigitalInput transferBottomLeftLimitSwitch;
  private DigitalInput transferTopRightLimitSwitch;
  private DigitalInput transferBottomRightLimitSwitch;

  // For Shuffleboard
  private ShuffleboardTab tab;
  private ShuffleboardLayout ballCollectedLayout;
  private ShuffleboardLayout speedLayout;

  private int shuffleboardWidgetWidth;
  private int shuffleboardWidgetHeight;

  TransferState state;

  // Initializes Transfer Variables
  public Transfer() {
    tab = Shuffleboard.getTab("Transfer");
    ballCollectedLayout = tab.getLayout("Collected Balls", BuiltInLayouts.kList).withSize(2, 2);
    speedLayout = tab.getLayout("Speed", BuiltInLayouts.kList).withSize(2, 2);

    shuffleboardWidgetWidth = 2;
    shuffleboardWidgetHeight = 1;

    topBeltMotor = new TalonFX(TransferMap.TOP_BELT_MOTOR_CAN);
    bottomBeltMotor = new TalonFX(TransferMap.BOTTOM_BELT_MOTOR_CAN);
    entranceBeltMotor = new TalonFX(TransferMap.ENTRANCE_BELT_MOTOR_CAN);

    transferTopLeftLimitSwitch = new DigitalInput(TransferMap.TRANSFER_TOP_LEFT_LIMIT_SWITCH_DIO);
    transferBottomLeftLimitSwitch = new DigitalInput(TransferMap.TRANSFER_BOTTOM_LEFT_LIMIT_SWITCH_DIO);
    transferTopRightLimitSwitch = new DigitalInput(TransferMap.TRANSFER_TOP_RIGHT_LIMIT_SWITCH_DIO);
    transferBottomRightLimitSwitch = new DigitalInput(TransferMap.TRANSFER_BOTTOM_RIGHT_LIMIT_SWITCH_DIO);
    configure();

    state = TransferState.OFF;
  }

  // Sets Transfer variable defaults
  public void configure() {
    topBeltMotor.configFactoryDefault();
    bottomBeltMotor.configFactoryDefault();
    entranceBeltMotor.configFactoryDefault();

    topBeltMotor.setNeutralMode(NeutralMode.Brake);
    bottomBeltMotor.setNeutralMode(NeutralMode.Brake);
    entranceBeltMotor.setNeutralMode(NeutralMode.Brake);

    entranceBeltMotor.setInverted(true);

    // Ramping
    topBeltMotor.configOpenloopRamp(TransferPrefs.transferRampTime.getValue());
    bottomBeltMotor.configOpenloopRamp(TransferPrefs.transferRampTime.getValue());
  }

  public enum TransferState {
    SHOOTING, PROCESSING, OFF
  }

  public void setTransferState(TransferState a_state) {
    state = a_state;
  }

  public TransferState getTransferState() {
    return state;
  }

  public double getTopBeltMotorEncoderCount() {
    return topBeltMotor.getSelectedSensorPosition();
  }

  public double getBottomBeltMotorEncoderCount() {
    return bottomBeltMotor.getSelectedSensorPosition();
  }

  public void setTopBeltMotorSpeed(SN_DoublePreference a_speed) {
    double speed = a_speed.getValue();
    topBeltMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setBottomBeltMotorSpeed(SN_DoublePreference a_speed) {
    double speed = a_speed.getValue();
    bottomBeltMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setEntranceBeltMotorSpeed(SN_DoublePreference a_speed) {
    double speed = a_speed.getValue();
    entranceBeltMotor.set(ControlMode.PercentOutput, speed);
  }

  public boolean isTopBallCollected() {
    return !transferTopRightLimitSwitch.get() || !transferTopLeftLimitSwitch.get();
  }

  public boolean isBottomBallCollected() {
    return !transferBottomRightLimitSwitch.get() || !transferBottomLeftLimitSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Ball Collected
    ballCollectedLayout.add("Top Ball Collected", isTopBallCollected()).withSize(
        shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    ballCollectedLayout.add("Bottom Ball Collected", isBottomBallCollected()).withSize(
        shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);

    // Speed
    speedLayout.add("Top Belt Motor Speed", topBeltMotor.getMotorOutputPercent()).withSize(
        shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    speedLayout.add("Bottom Belt Motor Speed", bottomBeltMotor.getMotorOutputPercent()).withSize(
        shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    speedLayout.add("Entrance Belt Motor Speed", entranceBeltMotor.getMotorOutputPercent()).withSize(
        shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);

  }
}
