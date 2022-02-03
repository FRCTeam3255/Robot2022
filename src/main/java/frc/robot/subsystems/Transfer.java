/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Transfer extends SubsystemBase {
  /**
   * Creates a new Transfer.
   */

  private TalonSRX topBeltMotor;
  private TalonSRX bottomBeltMotor;
  private DigitalInput isBallTopTransferLimitSwitch;
  private DigitalInput isBallBottomTransferLimitSwitch;
  private TalonSRX entranceBeltMotor;

  public Transfer() {
    topBeltMotor = new TalonSRX(RobotMap.TransferMap.TOP_BELT_MOTOR_CAN);
    bottomBeltMotor = new TalonSRX(RobotMap.TransferMap.BOTTOM_BELT_ENTRANCE_MOTOR_CAN);
    entranceBeltMotor = new TalonSRX(RobotMap.TransferMap.MIDDLE_BELT_MOTOR_CAN);
    isBallTopTransferLimitSwitch = new DigitalInput(RobotMap.TransferMap.IS_BALL_TOP_TRANSFER_LIMIT_SWITCH_DIO);
    isBallBottomTransferLimitSwitch = new DigitalInput(RobotMap.TransferMap.IS_BALL_BOTTOM_TRANSFER_LIMIT_SWITCH_DIO);

    configure();
  }

  public void configure() {
    topBeltMotor.configFactoryDefault();
    bottomBeltMotor.configFactoryDefault();
    entranceBeltMotor.configFactoryDefault();
  }

  public void setTopTransferMotorSpeed(double topTransferBeltMotor_speed) {
    double transferTopMotSpeed = topTransferBeltMotor_speed;

    topBeltMotor.set(ControlMode.PercentOutput, transferTopMotSpeed);
  }

  public void setTransferBottomBeltMotorSpeed(double transferBottomBeltMotor_speed) {
    double transferBottomBeltMotSpeed = transferBottomBeltMotor_speed;

    bottomBeltMotor.set(ControlMode.PercentOutput, transferBottomBeltMotSpeed);
  }

  public void setTransferEntranceMotorSpeed(double transferEntranceBeltMotor_speed) {
    double transferEntranceBeltMotSpeed = transferEntranceBeltMotor_speed;

    entranceBeltMotor.set(ControlMode.PercentOutput, transferEntranceBeltMotSpeed);
  }

  public boolean isBallTopCollected() {
    return isBallTopTransferLimitSwitch.get();

  }

  public boolean isBallBottomCollected() {
    return isBallBottomTransferLimitSwitch.get();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
