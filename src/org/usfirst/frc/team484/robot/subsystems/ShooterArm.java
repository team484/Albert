package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;
import org.usfirst.frc.team484.robot.commands.ShooterArmWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterArm extends Subsystem {

	public void initDefaultCommand() {
		setDefaultCommand(new ShooterArmWithJoystick());
	}

	public void shooterArmWithJoystick() {
		if (Robot.robotIO.operatorStick.getRawButton(4)) {
			Robot.robotIO.shooterArmMotor.set(-Robot.robotIO.operatorStick.getY());
		} else {
			adjustedShooterArmValue(Robot.robotIO.operatorStick.getY());
		}

	}

	public void adjustedShooterArmValue(double speed) {
		if (speed >= 0 && Math.abs(getArmAngle() - RobotSettings.shooterArmAngleStart) < 0.1 ) {
			Robot.robotIO.shooterArmMotor.set(-0.15);
		} else {
			double modSpeed;
			if (speed > 0) {
				modSpeed = speed / RobotSettings.shooterArmUpSpeedDivisor;
			} else {
				modSpeed = speed / RobotSettings.shooterArmDownSpeedDivisor;
			}
			modSpeed = modSpeed * RobotSettings.shooterArmVoltageTarget / Robot.pdpVoltage;
			Robot.robotIO.shooterArmMotor.set(-adjustTorque(modSpeed - RobotSettings.shooterArmGravityCompensationCoefficient * Math.sin(getArmAngle())));
		}
	}
	public double adjustTorque(double setValue) {
		double outputValue = setValue;
		double maxOutput = 0.0;
		double minOutput = 0.0;
		double voltage = Robot.pdpVoltage;
		double currentDraw = Robot.shooterArmCurrent;
		if (currentDraw < 0.1) { currentDraw = 89; }
		if (Robot.robotIO.shooterArmEncoder.getRate() > 0) {
			maxOutput = 89.0 * RobotSettings.shooterArmMaxTorque / (0.0867 * currentDraw * voltage);
			minOutput = -89.0 * RobotSettings.shooterArmMaxTorque / (0.0867 * (178.0 - currentDraw) * voltage);
		} else if (Robot.robotIO.shooterArmEncoder.getRate() < 0) {
			maxOutput = 89.0 * RobotSettings.shooterArmMaxTorque / (0.0867 * (178.0 - currentDraw) * voltage);
			minOutput = 89.0 * RobotSettings.shooterArmMaxTorque / (0.0867 * (-1.0) * currentDraw * voltage);
		} else {
			maxOutput = 89.0 * RobotSettings.shooterArmMaxTorque / (0.0867 * currentDraw * voltage);
			minOutput = 89.0 * RobotSettings.shooterArmMaxTorque / (0.0867 * (-1.0) * currentDraw * voltage);
		}
		if (outputValue > maxOutput && maxOutput > 0.15) {
			outputValue = maxOutput;
		} else if (outputValue < minOutput && minOutput < - 0.15) {
			outputValue = minOutput;
		}
		return outputValue;
	}

	public double getArmAngle() {
		return Robot.robotIO.shooterArmEncoder.getDistance() + RobotSettings.shooterArmAngleStart;
	}
}

