package org.usfirst.frc.team484.robot;

public class RobotSettings {
	public static final double shooterWheelsShootSpeedLeft = 11.5; //0.8
	public static final double shooterWheelsShootSpeedRight = -shooterWheelsShootSpeedLeft;
	
	public static final double shooterWheelsIntakeSpeedLeft = -7; //0.5
	public static final double shooterWheelsIntakeSpeedRight = -shooterWheelsIntakeSpeedLeft;
	
	public static final double shooterArmEncoderAnglePerPulse = 0.026511; //radians
	public static final double shooterArmUpSpeedDivisor = 2.0;
	public static final double shooterArmDownSpeedDivisor = 4.0;
	public static final double shooterArmGravityCompensationCoefficient = 0.078;
	
	public static final boolean invertDrivetrain = true;
}
