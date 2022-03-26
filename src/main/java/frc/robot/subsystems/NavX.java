// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

//Shuffleboard imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class NavX extends SubsystemBase {

  public AHRS navx;

  private ShuffleboardLayout navxLayout;
  private ShuffleboardTab tab;

  private int shuffleboardWidgetHeight;
  private int shuffleboardWidgetWidth;

  /** Creates a new NavX. */
  public NavX() {
    navx = new AHRS();
    navxLayout = tab.getLayout("isNavxConnected", BuiltInLayouts.kList).withSize(3, 3).withPosition(0, 0);
    tab = Shuffleboard.getTab("NavX");

    // Shuffleboard Layout Width
    shuffleboardWidgetWidth = 2;

    // Shuffleboard Layout Height
    shuffleboardWidgetHeight = 1;

  }

  public void resetHeading() {
    navx.reset();
  }

  public void calibrate() {
    navx.calibrate();
  }

  // isNavxConnected
  public boolean isNavxConnected() {
    return isNavxConnected();
  }

  // getNavYaw
  public int getNavYaw() {
    return getNavYaw();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    navxLayout.add("Is Navx Connected", isNavxConnected()).withSize(shuffleboardWidgetWidth, shuffleboardWidgetHeight);
    navxLayout.add("Get Nav Yaw", getNavYaw()).withSize(shuffleboardWidgetWidth, shuffleboardWidgetHeight);
  }
}
