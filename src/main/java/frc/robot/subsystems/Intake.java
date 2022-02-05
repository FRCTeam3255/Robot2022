// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;

public class Intake extends SubsystemBase {

  // Creates the motors
  private TalonFX intakeMotor;
  private DoubleSolenoid intakeSolenoid;
  private ColorSensorV3 intakeColorSensorV3;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  // Link to Robot Map
  public Intake() {
    intakeMotor = new TalonFX(RobotMap.IntakeMap.INTAKE_MOTOR_CAN);
    intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.IntakeMap.INTAKE_SOLENOID_PCM_A,
        RobotMap.IntakeMap.INTAKE_SOLENOID_PCM_B);
    intakeColorSensorV3 = new ColorSensorV3(i2cPort);

    configure();
  }

  // Set to factory default (configure it)
  public void configure() {
    intakeMotor.configFactoryDefault();
  }

  // Reset Encoder Count
  public void resetIntakeEncoderCount() {
    intakeMotor.setSelectedSensorPosition(0);
  }

  public void setIntakeMotorSpeed(double a_speed) {
    double speed = a_speed;

    intakeMotor.set(ControlMode.PercentOutput, speed);
  }

  // Get positons
  public double getIntakeMotorCount() {
    return intakeMotor.getSelectedSensorPosition();
  }

  public boolean isIntakeDeployed() {
    Value intakeSolenoidStatus = intakeSolenoid.get();
    boolean isIntakeDeployed = false;

    if (intakeSolenoidStatus == DoubleSolenoid.Value.kForward) {
      isIntakeDeployed = true;
    } else {
      isIntakeDeployed = false;
    }

    return isIntakeDeployed;

  }

  // solenoid do solenoid thing!!
  public void deployIntake() {
    intakeSolenoid.set(Value.kForward);
  }

  public void retractIntake() {
    intakeSolenoid.set(Value.kReverse);
  }

  // color sesnror do colro senror thingss
  public int getRed() {
    return intakeColorSensorV3.getRed();
  }

  public int getBlue() {
    return intakeColorSensorV3.getBlue();
  }

  public int getProximity() {
    return intakeColorSensorV3.getProximity();
  }

  public static enum ballColor {
    red, blue, none;
  }

  public ballColor getBallColor() {
    if (isProximity()) {
      if (getBlue() > getRed()) {
        return ballColor.blue;
      } else {
        return ballColor.red;
      }
    } else {
      return ballColor.none;
    }
  }

  public String ballColorString() {
    if (getBallColor() == ballColor.blue) {
      return "blue";
    } else if (getBallColor() == ballColor.red) {
      return "red";
    } else if (getBallColor() == ballColor.none) {
      return "none";
    } else {
      return "huh";
    }
  }

  public boolean isProximity() {
    return getProximity() > RobotPreferences.IntakePrefs.colorSensorMinProximity.getValue();
  }

  public boolean isBallBlue() {
    return getBallColor() == ballColor.blue;
  }

  public boolean isBallRed() {
    return getBallColor() == ballColor.red;
  }

  public boolean isAllianceBlue() {
    return DriverStation.getAlliance() == Alliance.Blue;
  }

  public boolean isAllianceRed() {
    return DriverStation.getAlliance() == Alliance.Red;
  }

  public boolean ballColorMatchesAlliance() {
    return (isBallBlue() && isAllianceBlue()) || (isBallRed() && isAllianceRed());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Motor", getIntakeMotorCount());
    SmartDashboard.putBoolean("Intake Solenoid", isIntakeDeployed());

    SmartDashboard.putNumber("Color Sensor Blue", getBlue());
    SmartDashboard.putNumber("Color Sensor Red", getRed());
    SmartDashboard.putNumber("Color Sensor Prox", getProximity());
    SmartDashboard.putBoolean("Is Ball Blue", isBallBlue());
    SmartDashboard.putBoolean("Is Alliance Blue", isAllianceBlue());
    SmartDashboard.putBoolean("Ball Color Matches Alliance", ballColorMatchesAlliance());
  }
}