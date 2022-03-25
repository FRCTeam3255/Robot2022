// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.*;

public class Hood extends SubsystemBase {
  /** Creates a new Hood. */

  // Creates Hood Variables
  private DoubleSolenoid angleHoodSolenoid;
  private DoubleSolenoid.Value shallowAngleHoodValue = Value.kReverse;
  private DoubleSolenoid.Value steepAngleHoodValue = Value.kForward;

  private ShuffleboardTab tab;
  private int shuffleboardWidgetWidth;
  private int shuffleboardWidgetHeight;

  // Initializes Hood Variables
  public Hood() {
    angleHoodSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,
        HoodMap.HOOD_SOLENOID_STEEP_ANGLE_PCM_A,
        HoodMap.HOOD_SOLENOID_SHALLOW_ANGLE_PCM_B);

    tab = Shuffleboard.getTab("Hood");
    shuffleboardWidgetHeight = 1;
    shuffleboardWidgetWidth = 2;
    // configure is not needed since this is a solenoid

  }

  // Method checks if solenoid is extended
  public boolean isHoodSteep() {
    Value hoodSolenoidStatus = angleHoodSolenoid.get();
    boolean isHoodSteep = false;

    if (hoodSolenoidStatus == DoubleSolenoid.Value.kForward) {
      isHoodSteep = true;
    } else {
      isHoodSteep = false;
    }
    return isHoodSteep;
  }

  // solenoid methods
  // Sets hood angle to the given value

  public void steepenHood() {
    angleHoodSolenoid.set(steepAngleHoodValue);

  }

  public void shallowHood() {
    angleHoodSolenoid.set(shallowAngleHoodValue);
  }

  // Method constantly runs
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    tab.add("Hood Solenoid", isHoodSteep()).withSize(shuffleboardWidgetHeight, shuffleboardWidgetWidth);
  }
}