// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot; 

import frc.subsystems; 
import edu.wpilibj.shuffleboard.ShuffleBoard;
import edu.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpilibj.shuffleboard.ShuffleBoardLayout;
import edu.wpilibj.shuffleboard.ShuffleBoardTab;
import RobotContainer;


//ShuffleBoard should act similar to RobotContainer but for ShuffleBoard
//So basically the values of ShuffleBoard Widgets will be declared here
public class ShuffleBoardContainer {
  
  //The new Shuffleboard Tabs
  private ShuffleBoardTab climberShuffleBoardTab;
  private ShuffleBoardTab drivetrainShuffleBoardTab;
  private ShuffleBoardTab intakeShuffleBoardTab;
  private ShuffleBoardTab navXShuffleBoardTab;
  private ShuffleBoardTab shooterShuffleBoardTab;
  private ShuffleBoardTab transferShuffleboardTab;
  private ShuffleBoardTab turretShuffleboardTab;
  private ShuffleBoardTab visionShuffleboardTab;

  //The new ShuffleBoard Widgets
  //Commented Out for now
  //private int climberShuffleBoardWidgetWidth;
  
  //Climber Subsystem
    climberShuffleBoardTab.add("Climber Encoder Counts", getClimberEncoderCount()).withSize()withPosition();
    climberShuffleBoardTab.add("Climber Closed Loop Error", getClimberClosedLoopError()).withSize().withPosition();
    climberShuffleBoardTab.add("Is Climber Error Acceptable", isClimberClosedLoopErrorAcceptable()).withSize().withPosition();
    climberShuffleBoardTab.add("Climber Motor Speed", climbMotor.getMotorOutputPercent()).withSize().withPosition();
    climberShuffleBoardTab.add("Is Climber At Bottom", isClimberAtBottom()).withSize().withPosition();
    climberShuffleBoardTab.add("Is Climber Angled", isClimberAngled()).withSize().withPosition();
    climberShuffleBoardTab.add("Is Climber Hooked", isClimberHooked()).withSize().withPosition();

  //Drivetrain Subsystem
  
    //Encoder Counts
`   drivetrainShuffleBoardTab.add("Drivetrain Left Encoder", getLeftEncoderCount()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Right Encoder", getRightEncoderCount()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Average Encoder", getAverageEncoderCount()).withSize().withPosition();

    //Motion Profile
    drivetrainShuffleBoardTab.add("Is Drivetrain Motion Profile Finished", isMotionProfileFinished()).withSize().withPosition();

    //Motor Percent Output (MPO)
    drivetrainShuffleBoardTab.add("Drivetrain Left Lead Motor Speed", leftLeadMotor.getMotorOutputPercent()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Right Lead Motor Speed", rightLeadMotor.getMotorOutputPercent()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Left Follow Motor Speed", leftFollowMotor.getMotorOutputPercent()).withSize().withPosition():
    drivetrainShuffleBoardTab.add("Drivetrain Right Follow Motor Speed", rightFollowMotor.getMotorOutputPercent()).withSize().withPosition();

    //Feet Driven
    drivetrainShuffleBoardTab.add("Drivetrain Left Feet Driven", getLeftFeetDriven()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Right Feet Driven", getRightFeetDriven()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Average Feet Driven", getAverageFeetDriven()).withSize().withPosition();

    //Feet Per Second
    drivetrainShuffleBoardTab.add("Drivetrain Left Feet Per Second", getLeftFeetDriven()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Right Feet Per Second", getRightFeetPerSecond()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Average Feet Per Second", getAverageFeetPerSecond()).withSize().withPosition();

    //Encoder Counts Per 100ms
    drivetrainShuffleBoardTab.add("Drivetrain Left Velocity", getLeftVelocity()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Right Velocity", getRightVelocity()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Average Velocity", getAverageVelocity()).withSize().withPosition();

    //Closed Loop Error 
    drivetrainShuffleBoardTab.add("Drivtrain Left Closed Loop Error Inches", getLeftClosedLoopErrorInches()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Right Closed Loop Error Inches", getRightClosedLoopErrorInches()).withSize().withPosition();
    drivetrainShuffleBoardTab.add("Drivetrain Average Closed Loop Error Inches", getAverageClosedLoopErrorInches()).withSize().withPosition();

  //Intake Subsystem 
    intakeShuffleBoardTab.add("Intake Motor", getIntakeMotorCount()).withSize().withPosition();
    intakeShuffleBoardTab.add("Intake Motor Percent Output", intakeMotor.getMotorOutputPercent()).withSize().withPosition();
    intakeShuffleBoardTab.add("Intake Solenoid", isIntakeDeployed()).withSize().withPosition();
    intakeShuffleBoardTab.add("Is Alliance Blue", isAllianceBlue()).withSize().withPosition();

  //NavX Subsystem 
    navXShuffleBoardTab.add("is navX connected", navX.isConnected()).withSize().withPosition();
    navXShuffleBoardTab.add("navx yaw", navx.getYaw());

  //Shooter Subsystem
    //shooterShuffleBoardTab.add("Shooter")

  //Transfer Subsystem
    transferShuffleboardTab.add("Is Top Ball Collected", isTopBallCollected()).withSize().withPosition();
    transferShuffleboardTab.add("Is Bottom Ball Collected", isBottomBallCollected()).withSize().withPosition();
    transferShuffleboardTab.add("Top Belt Motor Speed", topBeltMotor.getMotorOutputPercent()).withSize().withPosition();
    transferShuffleboardTab.add("Bottom Belt Motor Speed", bottomBeltMotor.getMotorOutputPercent()).withSize().withPosition();
    transferShuffleboardTab.add("Entrance Belt Motor Speed", entranceBeltMotor>getMotorOutputPercent()).withSize().withPosition();

  //Turret Subsytem 
    turretShuffleboardTab.add("Turret Encoder", getTurretMotorEncoderCounts()).withSize().withPosition();
    turretShuffleboardTab.add("Turret Angle", getTurretAngle()).withSize().withPosition();
    turretShuffleboardTab.add("Turret Closed Loop Error", getTurretClosedLoopErrorDegrees()).withSize().withPosition();
    turretShuffleboardTab.add("Turret Motor Speed", turretMotor.getMotorOutputPercent()).withSize().withPosition();

  //Vison Subsystem 
     visionShuffleboardTab.add("Limelight Has Target", limelight.hasTarget()).withSize().withPosition();
     visionShuffleboardTab.add("Limelight X Error", limelight.getOffsetX()).withSize().withPosition();
     visionShuffleboardTab.add("Limelight Y Error", limelight.getOffsetY()).withSize().withPosition();
     visionShuffleboardTab.add("Limelight Target Area", limelight.getTargetedArea()).withSize().withPosition();
     visionShuffleboardTab.add("Limelight Ideal Upper Hub RPM", getIdealUpperHubRPM()).withSize().withPosition();
     visionShuffleboardTab.add("Limelight Ideal Lower Hub RPM", getIdealLowerHubRPM()).withSize().withPosition();

  }
}