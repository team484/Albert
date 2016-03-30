package org.usfirst.frc.team484.robot;

public class RobotSettings {
	//PID Loop Settings
	public static final double DriveDistancekP = 0.148;
	public static final double DriveDistancekI = 0.0;
	public static final double DriveDistancekD = 0.5;

	public static final double rotateAnglekP = 0.08;
	public static final double rotateAnglekI = 0.0;
	public static final double rotateAnglekD = 0.02;
	
	public static final double shooterArmkP = 1.7;
	public static final double shooterArmkI = 0.002;
	public static final double shooterArmkD = 0.2;
	
	
	//Shooter Wheels Settings
	public static final double shooterWheelsShootSpeedLeft = 10.2; //0.8
	public static final double shooterWheelsShootSpeedRight = -shooterWheelsShootSpeedLeft;
	public static final double shooterWheelsIntakeSpeedLeft = -6; //-5
	public static final double shooterWheelsIntakeSpeedRight = -shooterWheelsIntakeSpeedLeft;
	public static final double shooterWheelsVoltageRampRate = 24.0;
	
	//Shooter Arm Settings
	public static final double shooterArmEncoderAnglePerPulse = 0.026511; //0.0187
	public static final double shooterArmUpSpeedDivisor = 2.0;
	public static final double shooterArmDownSpeedDivisor = 4.0;
	public static final double shooterArmGravityCompensationCoefficient = 0.14;
	public static final double shooterArmVoltageTarget = 12.0; //Will compensate by lowering voltages to this value
	public static final double shooterArmTargetSpeed = 0.5;
	public static final double shooterArmAngleStart = 0.01; //Vertical is 0 horizontal is -1.57
	public static final double shooterArmMaxTorque = 0.25; //0-1
	
	public static final double drivetrainDistancePerEncoderPulse = -0.0966748;
	public static final boolean invertDrivetrain = true;
	
	
	//Camera Servo Settings
	public static final double camRetractedAngle = 0.0; //setpoint for servo when camera should be retracted
	public static final double camRetractedAngle2 = 0.654;
	public static final double camMaxAngleForRetraction = 0.6; //max setpoint for servo that hits brace
	public static final double camMinAngleForRetraction = 0.09
			; //min setpoint for servo that hits brace
	public static final double armAngleForCameraRetraction = -0.85; //arm angle where camera is about to hit
}
