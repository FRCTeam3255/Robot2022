package frc.robot;

import com.frcteam3255.preferences.SN_DoublePreference;
import com.frcteam3255.preferences.SN_IntPreference;

public final class RobotPreferences {
  public static final class DrivetrainPrefs {
    public static final SN_DoublePreference arcadeSpeed = new SN_DoublePreference("arcadeSpeed", 1);
    public static final SN_DoublePreference arcadeTurn = new SN_DoublePreference("arcadeTurn", 1);

    public static final SN_DoublePreference driveF = new SN_DoublePreference("driveF", 0);
    public static final SN_DoublePreference driveP = new SN_DoublePreference("driveP", 1);
    public static final SN_DoublePreference driveI = new SN_DoublePreference("driveI", 0);
    public static final SN_DoublePreference driveD = new SN_DoublePreference("driveD", 0);
    public static final SN_DoublePreference driveAllowableCLError = new SN_DoublePreference("driveAllowableCLError",
        1000);
    public static final SN_DoublePreference driveCLPeakOutput = new SN_DoublePreference("driveCLPeakOutput", 1);

    // drivetrain gear ratio: 10:60 aka motor rotates once, wheel rotates 1/6
    public static final SN_IntPreference driveEncoderCountsPerWheelRotation = new SN_IntPreference(
        "driveEncoderCountsPerWheelRotation", 12288);
    // 4 inch wheel * pi = inches per rotation: 12.56637
    public static final SN_IntPreference driveEncoderCountsPerInch = new SN_IntPreference(
        "driveEncoderCountsPerInch", 978);
    public static final SN_IntPreference driveEncoderCountsPerFoot = new SN_IntPreference(
        "driveEncoderCountsPerFoot", 11734);
    public static final SN_IntPreference driveEncoderCountsPerMeter = new SN_IntPreference(
        "driveEncoderCountsPerMeter", 38499);
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
    public static final SN_DoublePreference turretMotorEncoderAngleMultiplier = new SN_DoublePreference(
        "turretMotorEncoderAngleMultipler", 90);

    public static final SN_DoublePreference turretMaxEncoderCount = new SN_DoublePreference("turretMaxEncoderCount",
        999999);

    public static final SN_DoublePreference turretMinEncoderCount = new SN_DoublePreference("turretMinEncoderCount",
        -999999);
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