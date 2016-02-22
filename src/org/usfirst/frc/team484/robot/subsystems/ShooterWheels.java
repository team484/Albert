package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;
import org.usfirst.frc.team484.robot.commands.ShooterWheelsDoNothing;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterWheels extends Subsystem {

    public void initDefaultCommand() {
    	setDefaultCommand(new ShooterWheelsDoNothing());
    }
    
    public void shooterWheelsShoot() {
    	Robot.robotIO.shooterLeftWheelMotor.set(RobotSettings.shooterWheelsShootSpeedLeft);
    	Robot.robotIO.shooterRightWheelMotor.set(RobotSettings.shooterWheelsShootSpeedRight);
    }
    
    public void shooterWheelsIntake() {
    	Robot.robotIO.shooterLeftWheelMotor.set(RobotSettings.shooterWheelsIntakeSpeedLeft);
    	Robot.robotIO.shooterRightWheelMotor.set(RobotSettings.shooterWheelsIntakeSpeedRight);
    }
    
    public void shooterWheelsDoNothing() {
    	Robot.robotIO.shooterLeftWheelMotor.set(0);
    	Robot.robotIO.shooterRightWheelMotor.set(0);
    }
}

