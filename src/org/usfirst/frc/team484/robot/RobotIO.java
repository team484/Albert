package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

public class RobotIO {
	//-------Motors-------
	public Talon frontLeftMotor;
	public Talon rearLeftMotor;
	public Talon frontRightMotor;
	public Talon rearRightMotor;
	
	public VictorSP shooterArmMotor;
	public CANTalon shooterLeftWheelMotor;
	public CANTalon shooterRightWheelMotor;
	
	public Servo cameraServo;
	//------Solenoids-----
	public Solenoid shooterPistonExtend;
	public Solenoid shooterPistonRetract;
	
	
	//-------Sensors------
	public AnalogGyro topGyro;
	public AnalogGyro bottomGyro;
	
	public AnalogInput leftBallIR;
	public AnalogInput rightBallIR;
	public AnalogInput ballInIR;
	public AnalogInput cameraIR;
	
	public Encoder driveEncoder;
	public Encoder shooterArmEncoder;
	
	//-----Joysticks-----
	public Joystick driverStick;
	public Joystick operatorStick;
	
	
	//-------Misc--------
	public RobotDrive driveRobot;
	public PowerDistributionPanel pdp;
	public DriverStation ds;
	public Compressor airCompressor;
	RobotIO() {
		//-------Motors-------
		frontLeftMotor = new Talon(RobotMap.frontLeftMotor);
		rearLeftMotor = new Talon(RobotMap.rearLeftMotor);
		frontRightMotor = new Talon(RobotMap.frontRightMotor);
		rearRightMotor = new Talon(RobotMap.rearRightMotor);
		
		shooterArmMotor = new VictorSP(RobotMap.shooterArmMotor);
		shooterLeftWheelMotor = new CANTalon(RobotMap.shooterLeftWheelMotor);
		shooterRightWheelMotor = new CANTalon(RobotMap.shooterRightWheelMotor);
		shooterLeftWheelMotor.changeControlMode(TalonControlMode.Voltage);
		shooterRightWheelMotor.changeControlMode(TalonControlMode.Voltage);
		shooterLeftWheelMotor.setVoltageRampRate(RobotSettings.shooterWheelsVoltageRampRate);
		shooterRightWheelMotor.setVoltageRampRate(RobotSettings.shooterWheelsVoltageRampRate);
		
		cameraServo = new Servo(RobotMap.cameraServo);
		//------Solenoids-----
		shooterPistonExtend = new Solenoid(RobotMap.shooterPistonExtend);
		shooterPistonRetract = new Solenoid(RobotMap.shooterPistonRetract);

		
		//-------Sensors------
		topGyro = new AnalogGyro(RobotMap.topGyro);
		bottomGyro = new AnalogGyro(RobotMap.bottomGyro);

		leftBallIR = new AnalogInput(RobotMap.leftBallIR);
		rightBallIR = new AnalogInput(RobotMap.rightBallIR);
		ballInIR = new AnalogInput(RobotMap.ballInIR);
		cameraIR = new AnalogInput(RobotMap.cameraIR);
		
		driveEncoder = new Encoder(RobotMap.driveEncoderA, RobotMap.driveEncoderB);
		driveEncoder.setDistancePerPulse(RobotSettings.drivetrainDistancePerEncoderPulse);
		shooterArmEncoder = new Encoder(RobotMap.shooterArmEncoderA,RobotMap.shooterArmEncoderB);
		shooterArmEncoder.setDistancePerPulse(RobotSettings.shooterArmEncoderAnglePerPulse);
		
		//-----Joysticks-----
		driverStick = new Joystick(RobotMap.driverStick);
		operatorStick = new Joystick(RobotMap.operatorStick);
		
		
		//-------Misc--------
		driveRobot = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		if (RobotSettings.invertDrivetrain) {
			driveRobot.setInvertedMotor(MotorType.kFrontLeft, true);
			driveRobot.setInvertedMotor(MotorType.kRearLeft, true);
			driveRobot.setInvertedMotor(MotorType.kFrontRight, true);
			driveRobot.setInvertedMotor(MotorType.kRearRight, true);
		}
		pdp = new PowerDistributionPanel();
		ds = DriverStation.getInstance();
		airCompressor = new Compressor();
	}
}