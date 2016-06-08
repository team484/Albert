package org.usfirst.frc.team484.robot;

public class RobotSettings {
	//PID Loop Settings
	public static final double DriveDistancekP = 0.148;
	public static final double DriveDistancekI = 0.0;
	public static final double DriveDistancekD = 0.5;

	public static final double rotateAnglekP = 0.08;
	public static final double rotateAnglekI = 0.0;
	public static final double rotateAnglekD = 0.02;
	
	public static final double rotateAngle2kP = 0.08;
	public static final double rotateAngle2kI = 0.03;
	public static final double rotateAngle2kD = 0.1;
	
	public static final double shooterArmkP = 1.7;
	public static final double shooterArmkI = 0.002;
	public static final double shooterArmkD = 0.2;
	
	
	//Shooter Wheels Settings
	public static final double shooterWheelsShootSpeedLeft = 12.5; //8.0
	public static final double shooterWheelsShootSpeedRight = -shooterWheelsShootSpeedLeft;
	public static final double shooterWheelsIntakeSpeedLeft = -6; //-5
	public static final double shooterWheelsIntakeSpeedRight = -shooterWheelsIntakeSpeedLeft;
	public static final double shooterWheelsVoltageRampRate = 24.0;
	
	//Shooter Arm Settings
	public static final double shooterArmEncoderAnglePerPulse = 0.017; //0.0175//0.0185
	public static final double shooterArmUpSpeedDivisor = 2.0;
	public static final double shooterArmDownSpeedDivisor = 4.0;
	public static final double shooterArmGravityCompensationCoefficient = 0.12;
	public static final double shooterArmVoltageTarget = 12.0; //Will compensate by lowering voltages to this value
	public static final double shooterArmTargetSpeed = 0.4;
	public static final double shooterArmAngleStart = 0.08; //0.035//Vertical is 0 horizontal is -1.57 <-
	public static final double shooterArmMaxTorque = 0.25; //0-1
	
	public static final double drivetrainDistancePerEncoderPulse = -0.094; //0966748
	public static final boolean invertDrivetrain = true;
	
	
	//Camera Servo Settings
	public static final double camRetractedAngle = 0.0; //setpoint for servo when camera should be retracted
	public static final double camRetractedAngle2 = 0.5;
	public static final double camMaxAngleForRetraction = 0.45; //max setpoint for servo that hits brace
	public static final double camMinAngleForRetraction = 0.09; //min setpoint for servo that hits brace
	public static final double armAngleForCameraRetraction = -0.70; //arm angle where camera is about to hit
}
