// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot; 

import frc.subsystems; 
import edu.wpilibj.shuffleboard.ShuffleBoard;
import edu.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpilibj.shuffleboard.ShuffleBoardLayout;
import edu.wpilibj.shuffleboard.ShuffleBoardTab;
import 
import RobotContainer;


//ShuffleBoard should act similar to RobotContainer but for ShuffleBoard
//So basically the values of ShuffleBoard Widgets will be declared here
public class ShuffleBoardContainer {
  
  //The new Shuffleboard Tabs
  private ShuffleBoardTab climberShuffleBoardTab;
  private ShuffleBoardTab shooterShuffleBoardTab;
  private ShuffleBoardTab hoodShuffleBoardTab;

  //The new ShuffleBoard Widgets
  private int climberShuffleBoardWidgetWidth;
  
  //Climber Subsystem
  climberShuffleBoardTab.add("Climber Encoder Counts", getClimberEncoderCount()).withSize()withPosition;

}