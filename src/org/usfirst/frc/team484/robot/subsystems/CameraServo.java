package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;
import org.usfirst.frc.team484.robot.commands.CameraServoDial;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraServo extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CameraServoDial());
    }
    public void cameraServoDial() {
    	double cameraAngle = Robot.robotIO.driverStick.getZ();
    	if (Robot.shooterArm.getArmAngle() > RobotSettings.armAngleForCameraRetraction) {
    		if (RobotSettings.camMaxAngleForRetraction > cameraAngle && RobotSettings.camMinAngleForRetraction < cameraAngle) {
    			Robot.robotIO.cameraServo.set(RobotSettings.camRetractedAngle);
    		} else {
    			Robot.robotIO.cameraServo.set(cameraAngle);
    		}
    	} else {
    		Robot.robotIO.cameraServo.set(cameraAngle);
    	}
    }
}

