package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterArmToGround extends Command {
	private boolean exit = true;
    public ShooterArmToGround(boolean exitOnGround) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterArm);
    	exit = exitOnGround;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterArm.adjustedShooterArmValue(-RobotSettings.shooterArmTargetSpeed * 1.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.shooterArm.getArmAngle() < -1.36 && Robot.robotIO.shooterArmEncoder.getRate() >= 0.0 && exit);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterArm.adjustedShooterArmValue(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
