package frc.team852.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.team852.Robot;
import frc.team852.RobotMap;
import frc.team852.command.WristHold;

public class WristSubsystem extends PIDSubsystem {

  private final WPI_TalonSRX motor;
  private final Encoder encoder;
  private DigitalInput lowerLimit, upperLimit;
  private final int elevatorLowerSafeDist = 10, elevatorUpperSafeDist = 30; // IDK what these values really are TODO fix on on reception of robot
  private final double wristBottom = 0, place = 90, wristSafe = 30; // IDK what these values really are TODO fix on on reception of robot

  public WristSubsystem() {
    super("Wrist", 0, 0, 0); // TODO Tune
    this.motor = RobotMap.wristMotor;
    this.encoder = RobotMap.wristEncoder;
    this.lowerLimit = RobotMap.wristLowerLimit;
    this.upperLimit = RobotMap.wristUpperLimit;
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new WristHold());
  }

  public void stopMotors() {
    this.motor.stopMotor();
  }

  public double getSpeed() {
    return this.motor.get();
  }

  /**
   * Manually set the speed while paying attention to limit switches in the mechanism
   *
   * @param speed
   */
  public void setSpeed(double speed) {
    if (speed > 0 && upperLimit.get()) {
      motor.set(0);
      System.out.println("[!!] Wrist on upper limit.");
    } else if (speed < 0 && lowerLimit.get()) {
      motor.set(0);
      System.out.println("[!!] Wrist on lower limit.");
    } else {
      motor.set(speed);
    }
  }

  /**
   * Used in conjunction with a command controlling the elevator to keep the back of the plate from breaking
   *
   * @param elevatorHeight
   */
  public void safeMove(int elevatorHeight) {
    if (!getPIDController().isEnabled())
      enable();

    if (elevatorHeight <= elevatorLowerSafeDist)
      setSetpoint(wristBottom);
    else if (elevatorHeight <= elevatorUpperSafeDist)
      setSetpoint(wristSafe);
    else
      setSetpoint(place);
  }

  public double getSafeSetpoint(int elevatorHeight) {
    if (elevatorHeight <= elevatorLowerSafeDist)
      return wristBottom;
    else if (elevatorHeight <= elevatorUpperSafeDist)
      return wristSafe;
    else
      return place;
  }

  public boolean canMoveUp() {
    return !upperLimit.get();
  }

  public boolean canMoveDown() {
    return !lowerLimit.get();
  }

  public boolean canMove() {
    int elevatorHeight = Robot.elevatorLidar.getLidarDistance();
    return !(elevatorHeight <= elevatorUpperSafeDist);
  }


  @Override
  protected void usePIDOutput(double output) {
    setSpeed(output);
  }

  public double getEncoderPos() {
    return encoder.pidGet();
  }

  public void resetEncoders() {
    encoder.reset();
  }

  @Override
  protected double returnPIDInput() {
    return encoder.get();
  }

}

