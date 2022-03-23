// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.frcteam3255.components.SN_DoubleSolenoid;
import com.frcteam3255.preferences.SN_DoublePreference;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.RobotMap.*;
import static frc.robot.RobotPreferences.*;

public class Climber extends SubsystemBase {

  /** Creates a new Climber. */
  private TalonFX climbMotor;
  private DigitalInput climberBottomSafetySwitch;
  private SN_DoubleSolenoid climberLockPiston;
  private SN_DoubleSolenoid climberPivotPiston;
  // Somebody can rename this solenoid if they can think of a better name
  // ok
  private SN_DoubleSolenoid climberHookPiston;

  private ShuffleboardTab tab;
  private int shuffleboardWidgetWidth;
  private int shuffleboardWidgetHeight;

  public Climber() {
    tab = Shuffleboard.getTab("Climber");
    shuffleboardWidgetWidth = 2;
    shuffleboardWidgetHeight = 1;

    climberBottomSafetySwitch = new DigitalInput(ClimberMap.BOTTOM_SAFETY_MAG_SWITCH_DIO);
    climbMotor = new TalonFX(ClimberMap.CLIMBER_MOTOR_CAN);

    climberLockPiston = new SN_DoubleSolenoid(RobotMap.CLIMBER_PCM, PneumaticsModuleType.CTREPCM,
        ClimberMap.LOCK_PISTON_PCM_A,
        ClimberMap.LOCK_PISTON_PCM_B);

    climberHookPiston = new SN_DoubleSolenoid(RobotMap.CLIMBER_PCM, PneumaticsModuleType.CTREPCM,
        ClimberMap.STATIONARY_CLIMB_HOOKS_PISTON_A,
        ClimberMap.STATIONARY_CLIMB_HOOKS_PISTON_B);

    climberPivotPiston = new SN_DoubleSolenoid(RobotMap.CLIMBER_PCM, PneumaticsModuleType.CTREPCM,
        ClimberMap.PIVOT_PISTON_PCM_A,
        ClimberMap.PIVOT_PISTON_PCM_B);

    configure();

  }

  public void configure() {
    climbMotor.configFactoryDefault();

    // Set the Soft Limit for Forward Throttle
    climbMotor.configForwardSoftLimitThreshold(ClimberPrefs.climberMaxEncoderCountPerpendicular.getValue());
    climbMotor.configReverseSoftLimitThreshold(ClimberPrefs.climberMinEncoderCount.getValue());
    climbMotor.configForwardSoftLimitEnable(true);
    climbMotor.configReverseSoftLimitEnable(true);

    climbMotor.setNeutralMode(NeutralMode.Brake);

    climberLockPiston.setInverted(ClimberPrefs.climberLockPistonInvert.getValue());
    climberPivotPiston.setInverted(ClimberPrefs.climberPivotPistonInvert.getValue());
    climberHookPiston.setInverted(ClimberPrefs.climberHookPistonInvert.getValue());
  }

  // Method controls CLimb Motor Speed
  public void setClimberSpeed(double a_speed) {
    double speed = a_speed;

    if (isHookDeployed()) {
      climbMotor.set(ControlMode.PercentOutput, speed);
    }
  }

  public void setClimberPosition(SN_DoublePreference a_position) {
    climbMotor.set(ControlMode.Position, a_position.getValue());
  }

  public void resetClimberEncoderCount() {
    climbMotor.setSelectedSensorPosition(0);
  }

  public double getClimberEncoderCount() {
    return climbMotor.getSelectedSensorPosition();
  }

  public boolean isClimberLocked() {
    return climberLockPiston.isDeployed();
  }

  // solenoid commands
  public void lockClimber() {
    climberLockPiston.setDeployed();
  }

  public void unlockClimber() {
    climberLockPiston.setRetracted();
  }

  // Piston Deploy/Retract
  public void pivotPerpendicular() {
    climberPivotPiston.setDeployed();
    climbMotor.configForwardSoftLimitThreshold(ClimberPrefs.climberMaxEncoderCountPerpendicular.getValue());
  }

  public void pivotAngled() {
    climberPivotPiston.setRetracted();
    climbMotor.configForwardSoftLimitThreshold(ClimberPrefs.climberMaxEncoderCountAngled.getValue());
  }

  public boolean isClimberAngled() {
    return climberPivotPiston.isDeployed();
  }

  public void hookUp() {
    climberHookPiston.setDeployed();
  }

  public void hookDown() {
    climberHookPiston.setRetracted();
  }

  public boolean isHookDeployed() {
    return climberHookPiston.isDeployed();
  }

  // TODO: change when location of mag switch is (ex: isClimberRaised)
  public boolean isClimberAtBottom() {
    return !climberBottomSafetySwitch.get();
  }

  public double getClimberClosedLoopError() {
    return climbMotor.getClosedLoopError();
  }

  public boolean isClimberClosedLoopErrorAcceptable() {
    return Math.abs(getClimberClosedLoopError()) < ClimberPrefs.climberAcceptableClosedLoopError.getValue();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    tab.add("Climber Encoder Counts", getClimberEncoderCount()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    tab.add("Climber Closed Loop Error", getClimberClosedLoopError()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    tab.add("Is Climber At Bottom", isClimberAtBottom()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    tab.add("Is Climber Locked", isClimberLocked()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    tab.add("Is Climber Angled", isClimberAngled()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    tab.add("Is Climber Hooked", isHookDeployed()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    tab.add("Is Climber Error Acceptable", isClimberClosedLoopErrorAcceptable()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
    tab.add("Climber Motor Speed", climbMotor.getMotorOutputPercent()).withSize(shuffleboardWidgetWidth,
        shuffleboardWidgetHeight);
  }
}