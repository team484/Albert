package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LineUpHighGoal extends Command {
	boolean isFinished;
    public LineUpHighGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinished = false;
    	Robot.drivetrain.onTargetCounter = 11;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	isFinished = Robot.drivetrain.lineUpHighGoal();
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
