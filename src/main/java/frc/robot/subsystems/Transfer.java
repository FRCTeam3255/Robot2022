/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//if a import is red, use ctrl shift p and clean java workspace
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Transfer extends SubsystemBase {
  /**
   * Creates a new Transfer.
   */

  private TalonSRX topBeltMotor;
  private TalonSRX bottomBeltMotor;;
  private DigitalInput transferLimitSwitch;
  private DigitalInput transferLimitSwitchTwo;
  private TalonSRX midBeltMotor;

  public Transfer() {
    topBeltMotor = new TalonSRX(RobotMap.TransferMap.TOP_BELT_MOTOR_CAN);
    bottomBeltMotor = new TalonSRX(RobotMap.TransferMap.BOTTOM_BELT_ENTRANCE_MOTOR_CAN);
    midBeltMotor = new TalonSRX(RobotMap.TransferMap.MIDDLE_BELT_MOTOR_CAN);
    transferLimitSwitch = new DigitalInput(RobotMap.TransferMap.TRANSFER_TOP_LIMIT_SWITCH_DIO);
    transferLimitSwitchTwo = new DigitalInput(RobotMap.TransferMap.TRANSFER_BOTTOM_LIMIT_SWITCH_DIO);
  }

  public void configure() {
    topBeltMotor.configFactoryDefault();
    bottomBeltMotor.configFactoryDefault();
  }

  public double getTopBeltMotorEncoderCount() {
    return topBeltMotor.getSelectedSensorPosition();
  }

  public double getBottomBeltMotorEncoderCount() {
    return bottomBeltMotor.getSelectedSensorPosition();
  }

  public void setTransferMotorSpeed(double transfermotor_speed) {
    double speed = transfermotor_speed;

    topBeltMotor.set(ControlMode.PercentOutput, speed);
    bottomBeltMotor.set(ControlMode.PercentOutput, speed);
  }

  public boolean isBallCollected() {
    return transferLimitSwitch.get();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
