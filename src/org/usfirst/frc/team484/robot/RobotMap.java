package org.usfirst.frc.team484.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//-------Motors-------
	public static int frontLeftMotor = 0; //Talon in PWM slot
	public static int rearLeftMotor = 1; //Talon in PWM slot
	public static int frontRightMotor = 2; //Talon in PWM slot
	public static int rearRightMotor = 3; //Talon in PWM slot
	
	public static int shooterArmMotor = 4; //Talon in PWM slot
	public static int shooterLeftWheelMotor = 0; //Might be changed for use with TalonSRX defined for PWM Talon
	public static int shooterRightWheelMotor = 1; //Might be changed for use with TalonSRX defined for PWM Talon
		
	public static int climberLeftMotor = 9; //Victor in PWM slot
	public static int climberRightMotor = 10; //Victor in PWM slot
	
	//------Solenoids-----
	public static int shooterPistonExtend = 0; //solenoid in PCM slot
	public static int shooterPistonRetract = 1; //solenoid in PCM slot
	
	//-------Sensors------
	public static int topGyro = 0; //Gyro in Analog IO slot
	public static int bottomGyro = 1; //Gyro in Analog IO slot
	
	public static int leftBallIR = 2; //Analog Input in Analog IO slot
	public static int rightBallIR = 3; //Analog Input in Analog IO slot
	
	public static int driveEncoderA = 0; //Encoder in Digital IO slot
	public static int driveEncoderB = 1; //Encoder in Digital IO slot
	
	public static int shooterArmEncoderA = 4; //Encoder in Digital IO slot
	public static int shooterArmEncoderB = 5; //Encoder in Digital IO slot
	
	//-----Joysticks-----
	public static int driverStick = 0; //Port assigned to the driver stick on the driver station
	public static int operatorStick = 1; //Port assigned to the operator stick on the driver station
	
}