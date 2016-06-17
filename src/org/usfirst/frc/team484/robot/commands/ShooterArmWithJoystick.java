package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterArmWithJoystick extends Command {
	PIDController pid;
	boolean wasPIDOperated = false;
    public ShooterArmWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterArm);
    	pid = new PIDController(RobotSettings.shooterArmkP, RobotSettings.shooterArmkI, RobotSettings.shooterArmkD, new PIDSource() {
    		public double pidGet() {
    			return Robot.shooterArm.getArmAngle();
    		}

			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub
				
			}
    	}, new PIDOutput() {
    		public void pidWrite(double d) {
    			Robot.shooterArm.adjustedShooterArmValue(d);
    		}
    	});
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	wasPIDOperated = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(Robot.robotIO.operatorStick.getY()) > 0.09 || Robot.shooterArm.getArmAngle() > 0.0) {
    		Robot.shooterArm.shooterArmWithJoystick();
    		if (wasPIDOperated) {
    			wasPIDOperated = false;
    			pid.disable();
    		}
    	} else {
    		if (!wasPIDOperated) {
    			wasPIDOperated = true;
    			pid.setSetpoint(Robot.shooterArm.getArmAngle());
    			pid.reset();
    			pid.enable();
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	pid.disable();
    }
}
