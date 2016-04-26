package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HorizontalLineup extends Command {
	int oscil = 0;
	boolean isFinished = false;
    public HorizontalLineup() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinished = false;
    	oscil = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double horizontal = Robot.visionCalc.lastHorizontal;
    	if (horizontal > 2) {
    		if (oscil > 5) {
    			oscil = 0;
    			Robot.drivetrain.setDrive(0.0, 0.0);
    		} else {
    			Robot.drivetrain.setDrive(0.0, -0.6 - 0.005 * horizontal);
    			oscil++;
    		}
    	} else if (horizontal < -1) {
    		if (oscil > 5) {
    			oscil = 0;
    			Robot.drivetrain.setDrive(0.0, 0.0);
    		} else {
    			Robot.drivetrain.setDrive(0.0, 0.6 - 0.005 * horizontal);
    			oscil++;
    		}
    	} else {
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveWithJoysticks();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
