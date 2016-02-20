package org.usfirst.frc.team484.robot;

import org.usfirst.frc.team484.robot.commands.DriveDistance;
import org.usfirst.frc.team484.robot.commands.RotateAngle;
import org.usfirst.frc.team484.robot.commands.ShooterArmToAngle;
import org.usfirst.frc.team484.robot.commands.ShooterPistonExtend;
import org.usfirst.frc.team484.robot.commands.ShooterPistonFloat;
import org.usfirst.frc.team484.robot.commands.ShooterPistonRetract;
import org.usfirst.frc.team484.robot.commands.ShooterWheelsDoNothing;
import org.usfirst.frc.team484.robot.commands.ShooterWheelsIntake;
import org.usfirst.frc.team484.robot.commands.ShooterWheelsShoot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Button prepareToShoot = new JoystickButton(Robot.robotIO.operatorStick, 3);
	Button intake = new JoystickButton(Robot.robotIO.operatorStick, 2);
	Button shoot = new JoystickButton(Robot.robotIO.operatorStick, 1);
	Button armVertical = new JoystickButton(Robot.robotIO.operatorStick, 11);
	Button armHorizontal = new JoystickButton(Robot.robotIO.operatorStick, 10);
	Button rotate90 = new JoystickButton(Robot.robotIO.driverStick, 11);
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	public OI() {
		prepareToShoot.whileHeld(new ShooterWheelsShoot());
		prepareToShoot.whenReleased(new ShooterWheelsDoNothing());
		intake.whileHeld(new ShooterWheelsIntake());
		intake.whileHeld(new ShooterPistonFloat());
		intake.whenReleased(new ShooterWheelsDoNothing());
		intake.whenReleased(new ShooterPistonRetract());
		shoot.whileHeld(new ShooterPistonExtend());
		shoot.whenReleased(new ShooterPistonRetract());
		
		armVertical.whileHeld(new ShooterArmToAngle(0));
		armHorizontal.whileHeld(new ShooterArmToAngle(-1.570796));
		rotate90.whileHeld(new RotateAngle(90.0));
	}
}

