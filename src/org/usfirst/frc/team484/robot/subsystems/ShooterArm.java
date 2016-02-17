package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;
import org.usfirst.frc.team484.robot.commands.ShooterArmWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterArm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShooterArmWithJoystick());
    }
    public void shooterArmWithJoystick() {
    	adjustedShooterArmValue(Robot.robotIO.operatorStick.getY());
    	
    }
    public void adjustedShooterArmValue(double speed) {
    	double modSpeed;
    	if (speed > 0) {
    		modSpeed = speed / RobotSettings.shooterArmUpSpeedDivisor;
    	} else {
    		modSpeed = speed / RobotSettings.shooterArmDownSpeedDivisor;
    	}
    	Robot.robotIO.shooterArmMotor.set(modSpeed - RobotSettings.shooterArmGravityCompensationCoefficient * Math.sin(Robot.robotIO.shooterArmEncoder.getDistance()));
    }
}

