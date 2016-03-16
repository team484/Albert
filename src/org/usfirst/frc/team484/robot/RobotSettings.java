package org.usfirst.frc.team484.robot;

public class RobotSettings {
	//PID Loop Settings
	public static final double DriveDistancekP = 0.148;
	public static final double DriveDistancekI = 0.0;
	public static final double DriveDistancekD = 0.5;

	public static final double rotateAnglekP = 0.04;
	public static final double rotateAnglekI = 0.0;
	public static final double rotateAnglekD = 1.38;
	
	public static final double shooterArmkP = 2.4;
	public static final double shooterArmkI = 0.01;
	public static final double shooterArmkD = 1.0;
	
	
	//Shooter Wheels Settings
	public static final double shooterWheelsShootSpeedLeft = 10.2; //0.8
	public static final double shooterWheelsShootSpeedRight = -shooterWheelsShootSpeedLeft;
	public static final double shooterWheelsIntakeSpeedLeft = -6; //-5
	public static final double shooterWheelsIntakeSpeedRight = -shooterWheelsIntakeSpeedLeft;
	public static final double shooterWheelsVoltageRampRate = 24.0;
	
	//Shooter Arm Settings
	public static final double shooterArmEncoderAnglePerPulse = 0.026511; //0.0187
	public static final double shooterArmUpSpeedDivisor = 3.0;
	public static final double shooterArmDownSpeedDivisor = 4.0;
	public static final double shooterArmGravityCompensationCoefficient = 0.07;
	public static final double shooterArmVoltageTarget = 12.0; //Will compensate by lowering voltages to this value
	public static final double shooterArmTargetSpeed = 0.5;
	public static final double shooterArmAngleStart = 0.0; //Vertical is 0 horizontal is -1.57
	
	public static final double drivetrainDistancePerEncoderPulse = -0.0966748;
	public static final boolean invertDrivetrain = false;
	
	
	//Camera Servo Settings
	public static final double camRetractedAngle = 0.0; //setpoint for servo when camera should be retracted
	public static final double camMaxAngleForRetraction = 0.0; //max setpoint for servo that hits brace
	public static final double camMinAngleForRetraction = 0.0; //min setpoint for servo that hits brace
	public static final double armAngleForCameraRetraction = 0.0; //arm angle where camera is about to hit
}
