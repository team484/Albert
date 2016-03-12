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
    	double modSpeed;
    	if (speed > 0) {
    		modSpeed = speed / RobotSettings.shooterArmUpSpeedDivisor;
    	} else {
    		modSpeed = speed / RobotSettings.shooterArmDownSpeedDivisor;
    	}
    	modSpeed = modSpeed * RobotSettings.shooterArmVoltageTarget / Robot.robotIO.pdp.getVoltage();
    	Robot.robotIO.shooterArmMotor.set(modSpeed - RobotSettings.shooterArmGravityCompensationCoefficient * Math.sin(getArmAngle()));
    }
    
    public double getArmAngle() {
    	return Robot.robotIO.shooterArmEncoder.getDistance() + RobotSettings.shooterArmAngleStart;
    }
}

