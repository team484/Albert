package org.usfirst.frc.team484.robot;

import org.usfirst.frc.team484.robot.commands.RotateAngle;
import org.usfirst.frc.team484.robot.commands.ShooterArmToAngle;
import org.usfirst.frc.team484.robot.commands.ShooterPistonExtend;
import org.usfirst.frc.team484.robot.commands.ShooterPistonRetract;
import org.usfirst.frc.team484.robot.commands.ShooterWheelsDoNothing;
import org.usfirst.frc.team484.robot.commands.ShooterWheelsIntake;
import org.usfirst.frc.team484.robot.commands.ShooterWheelsShoot;
import org.usfirst.frc.team484.robot.commands.TargetedShoot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Button prepareToShoot = new JoystickButton(Robot.robotIO.operatorStick, 3);
	Button intake = new JoystickButton(Robot.robotIO.operatorStick, 2);
	Button shoot = new JoystickButton(Robot.robotIO.operatorStick, 1);
	Button armVertical = new JoystickButton(Robot.robotIO.operatorStick, 11);
	Button armHorizontal = new JoystickButton(Robot.robotIO.operatorStick, 10);
	Button rotate90 = new JoystickButton(Robot.robotIO.driverStick, 11);
	Button testAim = new JoystickButton(Robot.robotIO.operatorStick, 8);
 
	public OI() {
		prepareToShoot.whileHeld(new ShooterWheelsShoot());
		prepareToShoot.whenReleased(new ShooterWheelsDoNothing());
		intake.whileHeld(new ShooterWheelsIntake());
		intake.whenReleased(new ShooterWheelsDoNothing());
		intake.whenReleased(new ShooterPistonRetract());
		shoot.whileHeld(new ShooterPistonExtend());
		shoot.whenReleased(new ShooterPistonRetract());
		
		armVertical.whileHeld(new ShooterArmToAngle(0));
		armHorizontal.whileHeld(new ShooterArmToAngle(-1.570796));
		rotate90.whileHeld(new RotateAngle(90.0));
		testAim.whileHeld(new TargetedShoot());
	}
}

