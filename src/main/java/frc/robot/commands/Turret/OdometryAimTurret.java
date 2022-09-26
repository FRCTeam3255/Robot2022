// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Turret;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Turret;

public class OdometryAimTurret extends CommandBase {
  Turret turret;
  Drivetrain drivetrain;

  double kHubX = Units.feetToMeters(26);
  double kHubY = Units.feetToMeters(13.5);

  double robotX;
  double robotY;
  double robotTheta;

  double adjustedRobotX;
  double adjustedRobotY;

  double drivetrainAngleToHub;
  double turretAngleToHub;

  double distanceToHub;

  /** Creates a new OdometryAimTurret. */
  public OdometryAimTurret(Turret sub_turret, Drivetrain sub_drivetrain) {
    turret = sub_turret;
    drivetrain = sub_drivetrain;
    addRequirements(sub_turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    robotX = drivetrain.getPose().getX();
    robotY = drivetrain.getPose().getY();
    robotTheta = drivetrain.getPose().getRotation().getDegrees();

    adjustedRobotX = robotX - kHubX;
    adjustedRobotY = robotY - kHubY;

    drivetrainAngleToHub = Math.atan2(adjustedRobotY, adjustedRobotX) - robotTheta;
    turretAngleToHub = drivetrainAngleToHub - turret.getTurretAngle();

    distanceToHub = Math.sqrt(Math.pow(kHubX - robotX, 2) + Math.pow(kHubY - robotY, 2));

    // turret.setTurretAngle(turretAngleToHub);

    SmartDashboard.putNumber("Odometry Aim RobotX", robotX);
    SmartDashboard.putNumber("Odometry Aim RobotY", robotY);
    SmartDashboard.putNumber("Odometry Aim Robot Theta", robotTheta);
    SmartDashboard.putNumber("Odometry Aim Adjusted Robot X", adjustedRobotX);
    SmartDashboard.putNumber("Odometry Aim Adjusted Robot Y", adjustedRobotY);
    SmartDashboard.putNumber("Odometry Aim Drivetrain Angle To Hub", drivetrainAngleToHub);
    SmartDashboard.putNumber("Odometry Aim Turret Angle To Hub", turretAngleToHub);
    SmartDashboard.putNumber("Odometry Aim Distance To Hub", distanceToHub);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
