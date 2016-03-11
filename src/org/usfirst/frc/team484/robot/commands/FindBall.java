package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FindBall extends Command {
	int timeRunning = 0;
    public FindBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.shooterWheels);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeRunning = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timeRunning++;
    	if (!Robot.shooterPiston.ballInShooter()) {
    		if (timeRunning > 30) {
    			Robot.drivetrain.findBall();
    		} else {
    			Robot.drivetrain.driveWithJoysticks();
    		}
    		Robot.shooterWheels.shooterWheelsIntake();
    	} else {
    		Robot.drivetrain.driveWithJoysticks();
    		Robot.shooterWheels.shooterWheelsDoNothing();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shooterPiston.ballInShooter();
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
