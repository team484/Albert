package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateAngle extends Command {
	PIDController pid;
    public RotateAngle(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	pid = new PIDController(RobotSettings.rotateAnglekP, RobotSettings.rotateAnglekI, RobotSettings.rotateAnglekD, new PIDSource() {
    		public double pidGet() {
    			return Robot.drivetrain.getRobotAngle();
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
    			Robot.drivetrain.setDrive(0, d);
    		}
    	});
    	//pid.setOutputRange(-2.0, 2.0);
    	pid.setSetpoint(setpoint);
    	//pid.setOutputRange(-0.4, 0.4);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.robotIO.topGyro.reset();
    	Robot.robotIO.bottomGyro.reset();
    	pid.reset();
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("ang", pid.getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	Robot.drivetrain.driveWithJoysticks();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
