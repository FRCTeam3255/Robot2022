package frc.robot;

import com.frcteam3255.preferences.SN_DoublePreference;
import com.frcteam3255.preferences.SN_IntPreference;

public final class RobotPreferences {

  /**
   * when mechanical builds stuff, it's almost always (and should be) designed to
   * where the motors can run at full speed. unless there is some actual real
   * issue, we shouldn't make the motors run slower than they should. for example,
   * in 2020, the turret was set to run half speed for no real reason. this made
   * our bot worse than it could have been. this year, the turret is (unless this
   * is unsafe for some reason) going to run at full speed under closed loop
   * control
   * 
   * The one situation where this doesn't apply is when the motor is under open
   * loop, user control. it may not be practical for a user to be controlling a
   * mechanism at full speed, so it's fine to slow it down then. Usually though,
   * mechanisms that users control will not need to have granular control, and
   * instead should use presets (closed loop, full speed control). Eg 2020 hood,
   * 2019 cascade, etc.
   */

  public static final class DrivetrainPrefs {
    public static final SN_DoublePreference arcadeSpeed = new SN_DoublePreference("arcadeSpeed", 1);
    public static final SN_DoublePreference arcadeTurn = new SN_DoublePreference("arcadeTurn", 1);
  }

  public static final class HoodPrefs {
  }

  public static final class ShooterPrefs {
    public static final SN_DoublePreference shooterMotorSpeed = new SN_DoublePreference("shooterMotorSpeed", 1);
    public static final SN_DoublePreference shooterMotorTargetVelocity = new SN_DoublePreference(
        "shooterMotorTargetVelocity", 5000.0);

    public static final SN_DoublePreference shooterF = new SN_DoublePreference("kF", 0);
    public static final SN_DoublePreference shooterP = new SN_DoublePreference("kP", 1);
    public static final SN_DoublePreference shooterI = new SN_DoublePreference("kI", 0);
    public static final SN_DoublePreference shooterD = new SN_DoublePreference("kD", 0);
  }

  public static final class TurretPrefs {
    public static final SN_DoublePreference turretMaxAngleDegrees = new SN_DoublePreference("turretMaxAngleDegrees",
        180);
    public static final SN_DoublePreference turretMinAngleDegrees = new SN_DoublePreference("turretMinAngleDegrees",
        -180);
    public static final SN_DoublePreference turretMaxAngleEncoder = new SN_DoublePreference("turretMaxEncoderCount",
        7560);
    public static final SN_DoublePreference turretMinAngleEncoder = new SN_DoublePreference("turretMinEncoderCount",
        -7560);
    // TODO: find this value (mathematically then emperically)
    public static final SN_DoublePreference turretEncoderCountsPerDegree = new SN_DoublePreference(
        "turretEncoderCountsPerDegree", 84);
    public static final SN_DoublePreference turretMaxAllowableError = new SN_DoublePreference("turretMaxAllowableError",
        84);
    public static final SN_DoublePreference turretClosedLoopPeakOutput = new SN_DoublePreference(
        "turretClosedLoopPeakOutput", 1);
    public static final SN_DoublePreference turretF = new SN_DoublePreference("turretF", 0);
    public static final SN_DoublePreference turretP = new SN_DoublePreference("turretP", 1);
    public static final SN_DoublePreference turretI = new SN_DoublePreference("turretI", 0);
    public static final SN_DoublePreference turretD = new SN_DoublePreference("turretD", 0);

    public static final SN_IntPreference turretLoopsToFinish = new SN_IntPreference("turretLoopsToFinish", 25);

    public static final SN_DoublePreference turretTestPos = new SN_DoublePreference("turretTestPos", 45);
  }

  public static final class TransferPrefs {
    public final static SN_DoublePreference transferSpeed = new SN_DoublePreference("transferSpeed", 0.80);

  }

  public static final class IntakePrefs {
    public final static SN_DoublePreference collectSpeed = new SN_DoublePreference("collectSpeed", 0.80);
    public final static SN_DoublePreference rejectSpeed = new SN_DoublePreference("rejectSpeed", -0.80);
    public final static SN_IntPreference colorSensorMinProximity = new SN_IntPreference("colorSensorMinProximity",
        1000);
  }

  public static final class VisionPrefs {
  }

  public static final class ClimberPrefs {
    public static final SN_DoublePreference climberMotorSpeed = new SN_DoublePreference("climberMotorSpeed", 0.5);
    public static final SN_DoublePreference climberMaxEncoderCount = new SN_DoublePreference("climberMaxEncoderCount",
        200000);
  }
}