package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public int onTargetCounter = 11;
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystick());
    }
    public boolean lineUpHighGoal() {
    	double offset = Robot.visionCalc.lastHorizontal;
    	if (Math.abs(offset) < 3) {
    		onTargetCounter++;
    	} else if (offset > 0) {
    		setDrive(0, -0.5);
    		onTargetCounter = 0;
    	} else if (offset < 0) {
    		setDrive(0, 0.5);
    		onTargetCounter = 0;
    	}
    	if (onTargetCounter > 10) {
    		return true;
    	} else {
    		return false;
    	}
    }
    public void findBall() {
    	double min = 0.0;
    	if (Robot.robotIO.leftBallIR.getAverageVoltage() > min && Robot.robotIO.rightBallIR.getAverageVoltage() <= min) {
    		setDrive(0, -0.5);
    	} else if (Robot.robotIO.rightBallIR.getAverageVoltage() > min && Robot.robotIO.leftBallIR.getAverageVoltage() <= min) {
    		setDrive(0, 0.5);
    	} else if (Robot.robotIO.leftBallIR.getAverageVoltage() > min && Robot.robotIO.rightBallIR.getAverageValue() > min) {
    		if (Robot.robotIO.leftBallIR.getAverageVoltage() > Robot.robotIO.rightBallIR.getAverageVoltage()) {
    			setDrive(0, -0.2);
    		} else {
    			setDrive(0, 0.2);
    		}
    	} else {
    		setDrive(0.5, 0.0);
    	}
    }
    public void driveWithJoysticks() {
    	setDrive(-Robot.robotIO.driverStick.getY(), Robot.robotIO.driverStick.getX());
    }
    
    public void setDrive(double translation, double rotation) { //clockwise positive
    	Robot.robotIO.driveRobot.arcadeDrive(translation, rotation);
    }
    
    public double currentDistance() {
    	return Robot.robotIO.driveEncoder.getDistance();
    }
    
    public double getRobotAngle() {
    	return (Robot.robotIO.topGyro.getAngle() - Robot.robotIO.bottomGyro.getAngle()) / 2.0;
    }
}

