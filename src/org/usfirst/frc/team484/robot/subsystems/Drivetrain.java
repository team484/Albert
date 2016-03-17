package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	double timeSinceBall = 0;
	boolean wasLeft = false;
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystick());
    }
    public boolean lineUpHighGoal() {
    	double offset = Robot.visionCalc.lastHorizontal;
    	double offsetMod = offset;
    	if (Math.abs(offset) < 3) {
    		return true;
    	} else if (offset > 0) {
    		setDrive(0, -1.0 * (Math.abs(offset) / 150.0 + 0.5));
    	} else if (offset < 0) {
    		setDrive(0, (Math.abs(offset) / 150.0 + 0.5));
    	}
    	return false;
    }
    public void findBall() {
    	double min = 0.7;
    	if (timeSinceBall > 27) {
    		timeSinceBall = 27;
    	}
    	if (Robot.robotIO.leftBallIR.getAverageVoltage() > min && Robot.robotIO.rightBallIR.getAverageVoltage() <= min) {
    		timeSinceBall++;
    		if (!wasLeft) {
    			wasLeft = true;
    			timeSinceBall = 0;
    		}
    		setDrive(0.3, -0.04 * timeSinceBall);
    	} else if (Robot.robotIO.rightBallIR.getAverageVoltage() > min && Robot.robotIO.leftBallIR.getAverageVoltage() <= min) {
    		timeSinceBall++;
    		if (wasLeft) {
    			wasLeft = false;
    			timeSinceBall = 0;
    		}
    		setDrive(0.3, 0.04 * timeSinceBall);
    	} else if (Robot.robotIO.leftBallIR.getAverageVoltage() > min && Robot.robotIO.rightBallIR.getAverageValue() > min) {
    		timeSinceBall = 0;
    		setDrive(0.7, 0.0);
    	} else {
    		timeSinceBall = 0;
    		setDrive(0.7, 0.0);
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

