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
	public static final double shooterWheelsIntakeSpeedLeft = -5; //0.5
	public static final double shooterWheelsIntakeSpeedRight = -shooterWheelsIntakeSpeedLeft;
	public static final double shooterWheelsVoltageRampRate = 24.0;
	
	//Shooter Arm Settings
	public static final double shooterArmEncoderAnglePerPulse = 0.026511; //radians
	public static final double shooterArmUpSpeedDivisor = 3.0;
	public static final double shooterArmDownSpeedDivisor = 4.0;
	public static final double shooterArmGravityCompensationCoefficient = 0.1;
	public static final double shooterArmVoltageTarget = 12.0; //Will compensate by lowering voltages to this value
	public static final double shooterArmTargetSpeed = 0.4;
	
	public static final double drivetrainDistancePerEncoderPulse = -0.0966748;
	public static final boolean invertDrivetrain = true;
}
