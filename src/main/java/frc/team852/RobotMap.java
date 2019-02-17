package frc.team852;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import frc.team852.lib.utils.SparkMax;
import frc.team852.lib.utils.SparkMaxGroup;
import frc.team852.subsystem.Drivetrain;

/**
 * Map of all the sensors, motors, and other that the robot uses
 * This provides a lot of flexibility
 */

public class RobotMap {

  //Drivetrain
  private static SparkMax leftFront = new SparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO set based off of CAN id
  private static SparkMax leftBack = new SparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO set based off of CAN id
  private static SparkMax rightFront = new SparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO set based off of CAN id
  private static SparkMax rightBack = new SparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO set based off of CAN id
  public static SparkMaxGroup leftDrive = new SparkMaxGroup(leftFront, leftBack);
  public static SparkMaxGroup rightDrive = new SparkMaxGroup(rightFront, rightBack);
  public static Encoder leftGrayhill = new Encoder(9,8, false);
  public static Encoder rightGrayhill = new Encoder(0, 1, true);
  public static DoubleSolenoid gearbox = new DoubleSolenoid(0, 1); // TODO set based off of robot wiring


  //Elevator
  public static SparkMax elevatorMotor = new SparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO set based off of CAN id
  public static DigitalInput elevatorLowerLimit = new DigitalInput(10); // TODO set based off of robot wiring
  public static DigitalInput elevatorUpperLimit = new DigitalInput(11); // TODO set based off of robot wiring
  //TODO tune pid values
//  public static PIDController elevatorPID = new PIDController(0, 0, 0, Robot.elevatorLidar, elevatorMotor); // TODO tune


  //Wrist
  public static WPI_TalonSRX wristMotor = new WPI_TalonSRX(6); // TODO set based off of CAN id
  public static DigitalInput wristLowerLimit = new DigitalInput(12); // TODO set based off of robot wiring
  public static DigitalInput wristUpperLimit = new DigitalInput(13); // TODO set based off of robot wiring
  public static Encoder wristEncoder = new Encoder(7, 6); // TODO set based off of robot wiring
  //TODO tune pid values
//  public static PIDController wristPIDPosition = new PIDController(0, 0, 0, wristEncoder, wristMotor); // TODO tune

  //Cargo subsystem
  //public static WPI_TalonSRX cargoMotor = new WPI_TalonSRX(7);  // TODO might need to delete

  //Hatch Subsystem
  public static DoubleSolenoid hatchPancakePneumatics = new DoubleSolenoid(3, 4);

  //Climber Subsystem
  public static WPI_TalonSRX climberMotor = new WPI_TalonSRX(5); // TODO set based off of CAN id
  //public static Encoder climberEncoder = new Encoder(6, 7); // TODO set based off off robot wiring

//  public static AHRS gyro = Robot.gyro;

  // CONSTANTS



  public static DoubleSolenoid.Value FAST = DoubleSolenoid.Value.kForward;
  public static DoubleSolenoid.Value SLOW = DoubleSolenoid.Value.kReverse;

  public static Drivetrain.DriveMode currentDriveMode = Drivetrain.DriveMode.GTA;

  public RobotMap() {
    // TODO calculate these values (m/s) from gearing, check empirically
    //leftGrayhill.setDistancePerPulse(???);
    //rightGrayhill.setDistancePerPulse(???);
  }
}
