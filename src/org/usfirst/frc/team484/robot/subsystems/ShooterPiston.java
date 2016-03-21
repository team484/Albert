package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.commands.ShooterPistonRetract;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterPiston extends Subsystem {

    public void initDefaultCommand() {
        setDefaultCommand(new ShooterPistonRetract());
    }
    
    public void shooterPistonExtend() {
    	Robot.robotIO.shooterPistonExtend.set(true);
    	Robot.robotIO.shooterPistonRetract.set(false);
    }
    
    public void shooterPistonRetract() {
    	Robot.robotIO.shooterPistonExtend.set(false);
    	Robot.robotIO.shooterPistonRetract.set(true);
    }
    
    public void shooterPistonFloat() {
    	Robot.robotIO.shooterPistonExtend.set(false);
    	Robot.robotIO.shooterPistonRetract.set(false);
    }
    public boolean ballInShooter() {
    	boolean isIn = false;
    	if (Robot.robotIO.shooterPistonRetract.get()) {
    		if (Robot.robotIO.ballInIR.getAverageVoltage() > 2.2 && Robot.robotIO.ballInIR.getAverageVoltage() < 3.2) {
    			isIn = true;
    		}
    	}
    	return isIn;
    }
    public boolean ballInShootPosition() {
    	boolean isIn = false;
    	if (Robot.robotIO.shooterPistonRetract.get()) {
    		if (Robot.robotIO.ballInIR.getAverageVoltage() > 2.8 && Robot.robotIO.ballInIR.getAverageVoltage() < 3.2) {
    			isIn = true;
    		}
    	}
    	return isIn;
    }
}

