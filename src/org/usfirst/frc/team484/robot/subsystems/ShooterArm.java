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
			Robot.robotIO.shooterArmMotor.set(Robot.robotIO.operatorStick.getY());
		} else {
			adjustedShooterArmValue(Robot.robotIO.operatorStick.getY());
		}

	}

	public void adjustedShooterArmValue(double speed) {
		if (speed >= 0 && Math.abs(getArmAngle() - RobotSettings.shooterArmAngleStart) < 0.06 ) {
			Robot.robotIO.shooterArmMotor.set(0.12);
		} else {
			double modSpeed;
			if (speed > 0) {
				modSpeed = speed / RobotSettings.shooterArmUpSpeedDivisor;
			} else {
				modSpeed = speed / RobotSettings.shooterArmDownSpeedDivisor;
			}
			modSpeed = modSpeed * RobotSettings.shooterArmVoltageTarget / Robot.robotIO.pdp.getVoltage();
			Robot.robotIO.shooterArmMotor.set(adjustTorque(modSpeed - RobotSettings.shooterArmGravityCompensationCoefficient * Math.sin(getArmAngle())));
		}
	}
	public double maxTorque = 0.25;
	public double adjustTorque(double setValue) {
		double outputValue = setValue;
		double maxOutput = 0.0;
		double minOutput = 0.0;
		double currentDraw = Robot.robotIO.pdp.getCurrent(1);
		double voltage = Robot.robotIO.pdp.getVoltage();
		if (Robot.robotIO.shooterArmEncoder.getRate() > 0) {
			maxOutput = 89.0 * maxTorque / (0.0867 * currentDraw * voltage);
			minOutput = -89.0 * maxTorque / (0.0867 * (178.0 - currentDraw) * voltage);
		} else if (Robot.robotIO.shooterArmEncoder.getRate() < 0) {
			maxOutput = 89.0 * maxTorque / (0.0867 * (178.0 - currentDraw) * voltage);
			minOutput = 89.0 * maxTorque / (0.0867 * (-1.0) * currentDraw * voltage);
		} else {
			maxOutput = 89.0 * maxTorque / (0.0867 * currentDraw * voltage);
			minOutput = 89.0 * maxTorque / (0.0867 * (-1.0) * currentDraw * voltage);
		}
		if (outputValue > maxOutput) {
			outputValue = maxOutput;
		} else if (outputValue < minOutput) {
			outputValue = minOutput;
		}
		return outputValue;
	}

	public double getArmAngle() {
		return Robot.robotIO.shooterArmEncoder.getDistance() + RobotSettings.shooterArmAngleStart;
	}
}

