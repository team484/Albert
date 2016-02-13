package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class RobotIO {
	//-------Motors-------
	public Talon frontLeftMotor;
	public Talon rearLeftMotor;
	public Talon frontRightMotor;
	public Talon rearRightMotor;
	
	public Talon shooterArmMotor;
	public Talon shooterLeftWheelMotor;
	public Talon shooterRightWheelMotor;
	
	//------Solenoids-----
	public Solenoid shooterPistonExtend;
	public Solenoid shooterPistonRetract;
	
	
	//-------Sensors------
	public AnalogGyro topGyro;
	public AnalogGyro bottomGyro;
	
	public Encoder driveEncoderLeft;
	public Encoder driveEncoderRight;
	public Encoder shooterArmEncoder;
	
	//-----Joysticks-----
	public Joystick driverStick;
	public Joystick operatorStick;
	
	
	//-------Misc--------
	public RobotDrive driveRobot;
	public PowerDistributionPanel pdp;
	public DriverStation ds;
	RobotIO() {
		//-------Motors-------
		frontLeftMotor = new Talon(RobotMap.frontLeftMotor);
		rearLeftMotor = new Talon(RobotMap.rearLeftMotor);
		frontRightMotor = new Talon(RobotMap.frontRightMotor);
		rearRightMotor = new Talon(RobotMap.rearRightMotor);
		
		shooterArmMotor = new Talon(RobotMap.shooterArmMotor);
		shooterLeftWheelMotor = new Talon(RobotMap.shooterLeftWheelMotor);
		shooterRightWheelMotor = new Talon(RobotMap.shooterRightWheelMotor);
		
		
		//------Solenoids-----
		shooterPistonExtend = new Solenoid(RobotMap.shooterPistonExtend);
		shooterPistonRetract = new Solenoid(RobotMap.shooterPistonRetract);

		
		//-------Sensors------
		topGyro = new AnalogGyro(RobotMap.topGyro);
		bottomGyro = new AnalogGyro(RobotMap.bottomGyro);

		driveEncoderLeft = new Encoder(RobotMap.driveEncoderLeftA, RobotMap.driveEncoderLeftB);
		driveEncoderRight = new Encoder(RobotMap.driveEncoderRightA, RobotMap.driveEncoderRightB);
		shooterArmEncoder = new Encoder(RobotMap.shooterArmEncoderA,RobotMap.shooterArmEncoderB);
		shooterArmEncoder.setDistancePerPulse(RobotSettings.shooterArmEncoderAnglePerPulse);
		
		//-----Joysticks-----
		driverStick = new Joystick(RobotMap.driverStick);
		operatorStick = new Joystick(RobotMap.operatorStick);
		
		
		//-------Misc--------
		driveRobot = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		pdp = new PowerDistributionPanel();
		ds = DriverStation.getInstance();
	}
}