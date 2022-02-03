/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//if  rev robotics import is red, use ctrl shift p and clean java workspace
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Transfer extends SubsystemBase {
  /**
   * Creates a new Transfer.
   */

  private TalonSRX topBeltMotor;
  private TalonSRX bottomBeltMotor;
  private TalonSRX entranceBeltMotor;
  private ColorSensorV3 colorSensor;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private DigitalInput transferTopLimitSwitch;
  private DigitalInput transferBottomLimitSwitch;

  public Transfer() {
    topBeltMotor = new TalonSRX(RobotMap.TransferMap.TOP_BELT_MOTOR_CAN);
    bottomBeltMotor = new TalonSRX(RobotMap.TransferMap.BOTTOM_BELT_MOTOR_CAN);
    colorSensor = new ColorSensorV3(i2cPort);
    transferTopLimitSwitch = new DigitalInput(RobotMap.TransferMap.TRANSFER_TOP_LIMIT_SWITCH_DIO);
    transferBottomLimitSwitch = new DigitalInput(RobotMap.TransferMap.TRANSFER_BOTTOM_LIMIT_SWITCH_DIO);
  }

  public void configure() {
    topBeltMotor.configFactoryDefault();
    bottomBeltMotor.configFactoryDefault();
    entranceBeltMotor.configFactoryDefault();
  }

  public double getTopBeltMotorEncoderCount() {
    return topBeltMotor.getSelectedSensorPosition();
  }

  public double getBottomBeltMotorEncoderCount() {
    return bottomBeltMotor.getSelectedSensorPosition();
  }

  public void setTransferMotorSpeed(double transfermotor_speed) {
    double speed = transfermotor_speed;

    if (isTopBallCollected() == true) {
      topBeltMotor.set(ControlMode.PercentOutput, 0);

    } else if (isTopBallCollected() == false) {
      topBeltMotor.set(ControlMode.PercentOutput, speed);
    }

    if (isBottomBallCollected() == true) {
      bottomBeltMotor.set(ControlMode.PercentOutput, 0);

    } else if (isBottomBallCollected() == false) {
      bottomBeltMotor.set(ControlMode.PercentOutput, speed);
    }

    entranceBeltMotor.set(ControlMode.PercentOutput, speed);
  }

  public boolean isTopBallCollected() {
    return transferTopLimitSwitch.get();
  }

  public boolean isBottomBallCollected() {
    return transferBottomLimitSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Top Ball Collected", isTopBallCollected());
    SmartDashboard.putBoolean("Bottom Ball Collected", isBottomBallCollected());
  }
}
