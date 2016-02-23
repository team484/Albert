package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterArmThroughAngle extends Command {
	private double angle;
	private boolean wasGoingUp = true;
    public ShooterArmThroughAngle() {
    	requires(Robot.shooterArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Double.isNaN(Robot.visionCalc.lastAngle)) {
    		this.angle = Robot.shooterArm.getArmAngle();
    	} else {
    		this.angle = Robot.visionCalc.lastAngle;
    		wasGoingUp = (Robot.shooterArm.getArmAngle() < this.angle);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.shooterArm.getArmAngle() < angle) {
    		Robot.shooterArm.adjustedShooterArmValue(RobotSettings.shooterArmTargetSpeed);
    	} else {
    		Robot.shooterArm.adjustedShooterArmValue(-RobotSettings.shooterArmTargetSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("" + wasGoingUp + angle + Robot.shooterArm.getArmAngle());
    	if (wasGoingUp && Robot.shooterArm.getArmAngle() >= angle) {
    		return true;
    	} else if (!wasGoingUp && Robot.shooterArm.getArmAngle() <= angle) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
