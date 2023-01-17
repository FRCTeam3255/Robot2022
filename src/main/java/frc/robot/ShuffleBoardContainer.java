//ShuffleBoardContainer.java
package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

//ShuffleBoard should act similar to RobotContainer but for ShuffleBoard
//So basically the values of ShuffleBoard Widgets will be declared here
public class ShuffleBoardContainer {

  // The new Shuffleboard Tabs
  private ShuffleboardTab climberShuffleBoardTab;
  private ShuffleboardTab drivetrainShuffleBoardTab;
  private ShuffleboardTab intakeShuffleBoardTab;
  private ShuffleboardTab turretShuffleboardTab;
  private ShuffleboardTab transferShuffleboardTab;
  private ShuffleboardTab visionShuffleboardTab;

  // The new Shuffleboard Layouts

  // Climber Layouts
  private ShuffleboardLayout climberBooleanLayout;
  private ShuffleboardLayout climberOtherLayout;

  // Drivetrain Layouts
  private ShuffleboardLayout drivetrainEncodersLayout;
  private ShuffleboardLayout drivetrainMotionProfileLayout;
  private ShuffleboardLayout drivetrainMpoLayout;
  private ShuffleboardLayout drivetrainFtDrivenLayout;
  private ShuffleboardLayout drivetrainFtPerSecondLayout;
  private ShuffleboardLayout drivetrainVelocityLayout;
  private ShuffleboardLayout drivetrainClosedLoopErrorLayout;

  // Intake Layouts
  private ShuffleboardLayout intakeBooleansLayout;
  {
    // Tabs get named here :)
    climberShuffleBoardTab = Shuffleboard.getTab("Climber");
    drivetrainShuffleBoardTab = Shuffleboard.getTab("Drivetrain");
    intakeShuffleBoardTab = Shuffleboard.getTab("Intake");
    turretShuffleboardTab = Shuffleboard.getTab("Turret");
    transferShuffleboardTab = Shuffleboard.getTab("Transfer");
    visionShuffleboardTab = Shuffleboard.getTab("Vision");

    Shuffleboard.selectTab("Climber");
    Shuffleboard.selectTab("Drivetrain");
    Shuffleboard.selectTab("Intake");
    Shuffleboard.selectTab("Turret");
    Shuffleboard.selectTab("Transfer");
    Shuffleboard.selectTab("Vision");

    climberBooleanLayout = climberShuffleBoardTab.getLayout("Booleans", BuiltInLayouts.kList).withSize(8, 1)
        .withPosition(0, 0);
    climberOtherLayout = climberShuffleBoardTab.getLayout("Other", BuiltInLayouts.kList).withSize(6, 2).withPosition(0,
        2);
    // Drivetrain Layouts
    drivetrainEncodersLayout = drivetrainShuffleBoardTab.getLayout("Encoders", BuiltInLayouts.kList).withSize(3, 3)
        .withPosition(0, 0);
    drivetrainMotionProfileLayout = drivetrainShuffleBoardTab.getLayout("Booleans", BuiltInLayouts.kList).withSize(1, 1)
        .withPosition(4, 0);
    drivetrainMpoLayout = drivetrainShuffleBoardTab.getLayout("MPO", BuiltInLayouts.kList).withSize(4, 1)
        .withPosition(5, 0);
    drivetrainFtDrivenLayout = drivetrainShuffleBoardTab.getLayout("Ft Driven", BuiltInLayouts.kList).withSize(1, 4)
        .withPosition(4, 1);
    drivetrainFtPerSecondLayout = drivetrainShuffleBoardTab.getLayout("Ft/Second", BuiltInLayouts.kList).withSize(1, 4)
        .withPosition(5, 1);
    drivetrainClosedLoopErrorLayout = drivetrainShuffleBoardTab.getLayout("Closed Loop Error", BuiltInLayouts.kList)
        .withSize(1, 4).withPosition(6, 0);

    // Intake Layouts
    intakeBooleansLayout = intakeShuffleBoardTab.getLayout("Booleans", BuiltInLayouts.kList).withSize(2, 4)
        .withPosition(0, 0);

  }

  // The new ShuffleBoard Widgets

  // Boolean Widget ints
  private int BooleanWidgetWidth;
  private int BooleanWidgetHeight;
  private int WiderBooleanWidgetWidth;

  // Climber Widget ints
  private int ClimberNumWidgetWidth;
  private int ClimberNumWidgetHeight;

  // Drivetrain Widget ints

  // Encoders
  private int EncodersWidgetWidth;
  private int EncodersWidgetHeight;

  // MPO
  private int MotorPercentOutputWidgetWidth;
  private int MotorPercentOutputWidgetHeight;

  // Feet Driven
  private int FtDrivenWidgetWidth;
  private int FtDrivenWidgetHeight;

  // Feet/Second
  private int FtPerSecondWidgetWidth;
  private int FtPerSecondWidgetHeight;

  // Velocity
  private int VelocityWidgetWidth;
  private int VelocityWidgetHeight;

  // Closed Loop Error
  private int ClosedLoopErrorWidgetWidth;
  private int ClosedLoopErrorWidgetHeight;

  // Intake ints
  private int IntakeMotorCountWidgetWidth;
  // Turret ints
  private int TurretWidgetWidth;
  private int TurretWidgetHeight;

  // Vision ints
  private int VisionWidgetWidth;
  private int VisionWidgetHeight;
  {

    // Widget Width
    BooleanWidgetWidth = 2;
    WiderBooleanWidgetWidth = 3;
    ClimberNumWidgetWidth = 3;
    EncodersWidgetWidth = 2;
    MotorPercentOutputWidgetWidth = 1;
    FtDrivenWidgetWidth = 1;
    FtPerSecondWidgetWidth = 1;
    VelocityWidgetWidth = 1;
    ClosedLoopErrorWidgetWidth = 1;
    IntakeMotorCountWidgetWidth = 2;
    TurretWidgetWidth = 2;
    VisionWidgetWidth = 3;

    // Widget Height
    BooleanWidgetHeight = 1;
    ClimberNumWidgetHeight = 2;
    EncodersWidgetHeight = 1;
    MotorPercentOutputWidgetHeight = 1;
    FtDrivenWidgetHeight = 1;
    FtPerSecondWidgetHeight = 1;
    VelocityWidgetHeight = 1;
    ClosedLoopErrorWidgetHeight = 1;
    TurretWidgetHeight = 1;
    VisionWidgetHeight = 1;

    // Climber Subsystem
    // Booleans
    climberBooleanLayout
        .add("Is Climber Error Acceptable", RobotContainer.sub_climber.isClimberClosedLoopErrorAcceptable())
        .withSize(BooleanWidgetWidth, BooleanWidgetHeight);
    climberBooleanLayout.add("Is Climber Angled", RobotContainer.sub_climber.isClimberAngled())
        .withSize(BooleanWidgetWidth, BooleanWidgetHeight);
    climberBooleanLayout.add("Is Hook Deployed", RobotContainer.sub_climber.isHookDeployed())
        .withSize(BooleanWidgetWidth, BooleanWidgetHeight);
    climberBooleanLayout.add("Is Climber At Bottom", RobotContainer.sub_climber.isClimberAtBottom())
        .withSize(BooleanWidgetWidth, BooleanWidgetHeight);

    // Other Climber
    climberOtherLayout.add("Climber Encoder Counts", RobotContainer.sub_climber.getClimberEncoderCount())
        .withSize(ClimberNumWidgetWidth, ClimberNumWidgetHeight);
    climberOtherLayout.add("Climber Closed Loop Error", RobotContainer.sub_climber.getClimberClosedLoopError())
        .withSize(ClimberNumWidgetWidth, ClimberNumWidgetHeight);

    // jfhkjdsz
    // climberOtherLayout.add

    // Drivetrain Subsystem

    // Encoder Counts
    drivetrainEncodersLayout.add("Drivetrain Left Encoder", RobotContainer.sub_drivetrain.getLeftEncoderCount())
        .withSize(EncodersWidgetWidth, EncodersWidgetHeight);
    drivetrainEncodersLayout.add("Drivetrain Right Encoder", RobotContainer.sub_drivetrain.getRightEncoderCount())
        .withSize(EncodersWidgetWidth, EncodersWidgetHeight);
    drivetrainEncodersLayout.add("Drivetrain Average Encoder", RobotContainer.sub_drivetrain.getAverageEncoderCount())
        .withSize(EncodersWidgetWidth, EncodersWidgetHeight);

    // Motion Profile
    // Motion Profile Booleans
    drivetrainMotionProfileLayout
        .add("Is Drivetrain Motion Profile Finished", RobotContainer.sub_drivetrain.isMotionProfileFinished())
        .withSize(BooleanWidgetWidth, BooleanWidgetHeight);

    // Motor Percent Output (MPO)
    drivetrainMpoLayout
        .add("Drivetrain Left Lead Motor Speed", RobotContainer.sub_drivetrain.leftLeadMotor.getMotorOutputPercent())
        .withSize(MotorPercentOutputWidgetWidth, MotorPercentOutputWidgetHeight);
    drivetrainMpoLayout
        .add("Drivetrain Right Lead Motor Speed", RobotContainer.sub_drivetrain.rightLeadMotor.getMotorOutputPercent())
        .withSize(MotorPercentOutputWidgetWidth, MotorPercentOutputWidgetHeight);
    drivetrainMpoLayout
        .add("Drivetrain Left Follow Motor Speed",
            RobotContainer.sub_drivetrain.leftFollowMotor.getMotorOutputPercent())
        .withSize(MotorPercentOutputWidgetWidth, MotorPercentOutputWidgetHeight);
    drivetrainMpoLayout
        .add("Drivetrain Right Follow Motor Speed",
            RobotContainer.sub_drivetrain.rightFollowMotor.getMotorOutputPercent())
        .withSize(MotorPercentOutputWidgetWidth, MotorPercentOutputWidgetHeight);

    // Feet Driven
    drivetrainFtDrivenLayout.add("Drivetrain Left Feet Driven", RobotContainer.sub_drivetrain.getLeftFeetDriven())
        .withSize(FtDrivenWidgetWidth, FtDrivenWidgetHeight);
    drivetrainFtDrivenLayout.add("Drivetrain Right Feet Driven", RobotContainer.sub_drivetrain.getRightFeetDriven())
        .withSize(FtDrivenWidgetWidth, FtDrivenWidgetHeight);
    drivetrainFtDrivenLayout.add("Drivetrain Average Feet Driven", RobotContainer.sub_drivetrain.getAverageFeetDriven())
        .withSize(FtDrivenWidgetWidth, FtDrivenWidgetHeight);

    // Feet Per Second
    drivetrainFtPerSecondLayout
        .add("Drivetrain Left Feet Per Second", RobotContainer.sub_drivetrain.getLeftFeetDriven())
        .withSize(FtPerSecondWidgetWidth, FtPerSecondWidgetHeight);
    drivetrainFtPerSecondLayout
        .add("Drivetrain Right Feet Per Second", RobotContainer.sub_drivetrain.getRightFeetPerSecond())
        .withSize(FtPerSecondWidgetWidth, FtPerSecondWidgetHeight);
    drivetrainFtPerSecondLayout
        .add("Drivetrain Average Feet Per Second", RobotContainer.sub_drivetrain.getAverageFeetPerSecond())
        .withSize(FtPerSecondWidgetWidth, FtPerSecondWidgetHeight);

    // Encoder Counts Per 100ms
    drivetrainVelocityLayout.add("Drivetrain Left Velocity", RobotContainer.sub_drivetrain.getLeftVelocity())
        .withSize(VelocityWidgetWidth, VelocityWidgetHeight);
    drivetrainVelocityLayout.add("Drivetrain Right Velocity", RobotContainer.sub_drivetrain.getRightVelocity())
        .withSize(VelocityWidgetWidth, VelocityWidgetHeight);
    drivetrainVelocityLayout.add("Drivetrain Average Velocity", RobotContainer.sub_drivetrain.getAverageVelocity())
        .withSize(VelocityWidgetWidth, VelocityWidgetHeight);

    // Closed Loop Error
    drivetrainClosedLoopErrorLayout
        .add("Drivtrain Left Closed Loop Error Inches", RobotContainer.sub_drivetrain.getLeftClosedLoopErrorInches())
        .withSize(ClosedLoopErrorWidgetWidth, ClosedLoopErrorWidgetHeight);
    drivetrainClosedLoopErrorLayout
        .add("Drivetrain Right Closed Loop Error Inches", RobotContainer.sub_drivetrain.getRightClosedLoopErrorInches())
        .withSize(ClosedLoopErrorWidgetWidth, ClosedLoopErrorWidgetHeight);
    drivetrainClosedLoopErrorLayout
        .add("Drivetrain Average Closed Loop Error Inches",
            RobotContainer.sub_drivetrain.getAverageClosedLoopErrorInches())
        .withSize(ClosedLoopErrorWidgetWidth, ClosedLoopErrorWidgetHeight);

    // Intake Subsystem
    intakeShuffleBoardTab.add("Intake Motor", RobotContainer.sub_intake.getIntakeMotorCount())
        .withSize(IntakeMotorCountWidgetWidth, MotorPercentOutputWidgetHeight).withPosition(0, 0);
    intakeBooleansLayout.add("Intake Solenoid", RobotContainer.sub_intake.isIntakeDeployed())
        .withSize(BooleanWidgetWidth, BooleanWidgetHeight);
    intakeBooleansLayout.add("Is Alliance Blue", RobotContainer.sub_intake.isAllianceBlue())
        .withSize(BooleanWidgetWidth, BooleanWidgetHeight);

    // Shooter Subsystem
    // shooterShuffleBoardTab.add("Shooter")

    // Transfer Subsystem
    transferShuffleboardTab.add("Is Top Ball Collected", RobotContainer.sub_transfer.isTopBallCollected())
        .withSize(WiderBooleanWidgetWidth, BooleanWidgetHeight).withPosition(0, 0);
    transferShuffleboardTab.add("Is Bottom Ball Collected", RobotContainer.sub_transfer.isBottomBallCollected())
        .withSize(WiderBooleanWidgetWidth, BooleanWidgetHeight).withPosition(0, 2);

    // Turret Subsystem
    turretShuffleboardTab.add("Turret Encoder", RobotContainer.sub_turret.getTurretMotorEncoderCounts())
        .withSize(TurretWidgetWidth, TurretWidgetHeight).withPosition(0, 0);
    turretShuffleboardTab.add("Turret Angle", RobotContainer.sub_turret.getTurretAngle())
        .withSize(TurretWidgetWidth, TurretWidgetHeight).withPosition(2, 0);
    turretShuffleboardTab.add("Turret Closed Loop Error", RobotContainer.sub_turret.getTurretClosedLoopErrorDegrees())
        .withSize(TurretWidgetWidth, TurretWidgetHeight).withPosition(4, 0);

    // Vison Subsystem
    visionShuffleboardTab.add("Limelight Has Target", RobotContainer.sub_vision.limelight.hasTarget())
        .withSize(VisionWidgetWidth, VisionWidgetHeight).withPosition(0, 0);
    visionShuffleboardTab.add("Limelight X Error", RobotContainer.sub_vision.limelight.getOffsetX())
        .withSize(VisionWidgetWidth, VisionWidgetHeight).withPosition(1, 0);
    visionShuffleboardTab.add("Limelight Y Error", RobotContainer.sub_vision.limelight.getOffsetY())
        .withSize(VisionWidgetWidth, VisionWidgetHeight).withPosition(2, 0);
    visionShuffleboardTab.add("Limelight Ideal Lower Hub RPM", RobotContainer.sub_vision.getIdealLowerHubRPM())
        .withSize(VisionWidgetWidth, VisionWidgetHeight).withPosition(3, 0);

  }
}