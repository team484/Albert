package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystick());
    }
    public void findBall() {
    	double min = 0.0;
    	if (Robot.robotIO.leftBallIR.getAverageVoltage() > min && Robot.robotIO.rightBallIR.getAverageVoltage() <= min) {
    		setDrive(0, -0.5);
    	} else if (Robot.robotIO.rightBallIR.getAverageVoltage() > min && Robot.robotIO.leftBallIR.getAverageVoltage() <= min) {
    		setDrive(0, 0.5);
    	} else if (Robot.robotIO.leftBallIR.getAverageVoltage() > min && Robot.robotIO.rightBallIR.getAverageValue() > min) {
    		if (Robot.robotIO.leftBallIR.getAverageVoltage() > Robot.robotIO.rightBallIR.getAverageVoltage()) {
    			setDrive(0, -0.4);
    		} else {
    			setDrive(0, 0.4);
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

