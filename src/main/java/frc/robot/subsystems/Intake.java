// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.frcteam3255.components.SN_DoubleSolenoid;
import com.frcteam3255.preferences.SN_DoublePreference;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.RobotMap.*;
import static frc.robot.RobotPreferences.*;

public class Intake extends SubsystemBase {

  // Creates the Intake motors
  private TalonFX intakeMotor;
  private SN_DoubleSolenoid intakePiston;
  private ColorSensorV3 intakeColorSensorV3;
  private final I2C.Port i2cPort = I2C.Port.kMXP;

  // For Shuffleboard
  private ShuffleboardTab tab;
  private ShuffleboardLayout intakeComponentsLayout;
  private ShuffleboardLayout colorSensorLayout;

  private int shuffleboardWidgetWidth;
  private int shuffleboardWidgetHeight;

  // Initializes Intake Variables
  public Intake() {
    tab = Shuffleboard.getTab("Intake");
    intakeComponentsLayout = tab.getLayout("Intake Components", BuiltInLayouts.kList).withSize(2, 2);
    colorSensorLayout = tab.getLayout("Color Sensor", BuiltInLayouts.kList).withSize(2, 2);

    shuffleboardWidgetWidth = 2;
    shuffleboardWidgetHeight = 1;

    intakeMotor = new TalonFX(IntakeMap.INTAKE_MOTOR_CAN);
    intakePiston = new SN_DoubleSolenoid(RobotMap.PRIMARY_PCM, PneumaticsModuleType.CTREPCM,
        IntakeMap.INTAKE_SOLENOID_PCM_A,
        IntakeMap.INTAKE_SOLENOID_PCM_B);
    intakeColorSensorV3 = new ColorSensorV3(i2cPort);

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

  // Returns Ball color in Intake
  public int getRed() {
    return intakeColorSensorV3.getRed();
  }

  // Returns Ball color in Intake
  public int getBlue() {
    return intakeColorSensorV3.getBlue();
  }

  // Returns Proximity of Ball in Intake
  public int getProximity() {
    return intakeColorSensorV3.getProximity();
  }

  public static enum ballColor {
    red, blue, none;
  }

  public ballColor getBallColor() {
    if (isBallNearIntake()) {
      if (getBlue() > getRed()) {
        return ballColor.blue;
      } else {
        return ballColor.red;
      }
    } else {
      return ballColor.none;
    }
  }

  // Gets Ball Color/Status
  public String ballColorString() {
    if (getBallColor() == ballColor.blue) {
      return "blue";
    } else if (getBallColor() == ballColor.red) {
      return "red";
    } else if (getBallColor() == ballColor.none) {
      return "none";
    } else {
      return "Unknown Color";
    }
  }

  public boolean isBallNearIntake() {
    return getProximity() > IntakePrefs.colorSensorMinProximity.getValue();
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

    // Intake Components
    intakeComponentsLayout.add("Intake Motor", getIntakeMotorCount()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    intakeComponentsLayout.add("IntakeMotorOutputPercent", intakeMotor.getMotorOutputPercent()).withSize(
        shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    intakeComponentsLayout.add("Intake Solenoid", isIntakeDeployed()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);

    // Color Sensor
    colorSensorLayout.add("Color Sensor Blue", getBlue()).withSize(shuffleboardWidgetWidth, shuffleboardWidgetHeight);
    colorSensorLayout.add("Color Sensor Red", getRed()).withSize(shuffleboardWidgetWidth, shuffleboardWidgetHeight);
    colorSensorLayout.add("Color Sensor Prox", getProximity()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    colorSensorLayout.add("Is Ball Blue", isBallBlue()).withSize(shuffleboardWidgetWidth, shuffleboardWidgetHeight);
    colorSensorLayout.add("Is Alliance Blue", isAllianceBlue()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    colorSensorLayout.add("Ball Color Matches Alliance", ballColorMatchesAlliance()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    colorSensorLayout.add("Is Proximity", isBallNearIntake()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
  }
}