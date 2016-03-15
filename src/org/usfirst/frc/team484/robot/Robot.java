
package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team484.robot.commands.*;
import org.usfirst.frc.team484.robot.subsystems.*;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final RobotIO robotIO = new RobotIO(); //Initializing robotIO, a class which initializes all IO on the robot
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final ShooterArm shooterArm = new ShooterArm();
	public static final ShooterWheels shooterWheels = new ShooterWheels();
	public static final ShooterPiston shooterPiston = new ShooterPiston();
	public static final VisionCalculations visionCalc = new VisionCalculations();
	public static final CameraServo cameraServo = new CameraServo();
	public static OI oi;
	CameraServer camera;

	Command autoCrossCommand; //Creates command for crossing the defenses
    Command autoShootCommand; //Creates a command for shooting after the cross
    SendableChooser autoCrossChooser; //Creates a choose menu for selecting crossing command on dashboard
    SendableChooser autoShootChooser; //Creates a choose menu for selecting shooting command on dashboard

    public void robotInit() {
		oi = new OI();
		robotIO.airCompressor.start();
		try {
			camera = CameraServer.getInstance();
			camera.startAutomaticCapture("cam1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 autoCrossChooser = new SendableChooser(); //Initializes the choose menu for selecting crossing command
	        autoCrossChooser.addDefault("Do Nothing", new AutoCrossNothing()); //adds do nothing as an option for crossing
	        autoCrossChooser.addObject("ChevalDeFrise", new AutoCrossChevalDeFrise()); //adds cheval de frise as an option for crossing
	        autoCrossChooser.addObject("LowBar", new AutoCrossLowBar()); //adds low bar as an option for crossing
	        autoCrossChooser.addObject("Moat", new AutoCrossMoat()); //adds moat as an option for crossing
	        autoCrossChooser.addObject("Portcullis", new AutoCrossPortcullis()); //adds portcullis as an option for crossing
	        autoCrossChooser.addObject("Ramparts", new AutoCrossRamparts()); //adds ramparts as an option for crossing
	        autoCrossChooser.addObject("RockWall", new AutoCrossRockWall()); //adds rock wall as an option for crossing
	        autoCrossChooser.addObject("RoughTerrain", new AutoCrossRoughTerrain()); //adds rough terrain as an option for crossing
	        SmartDashboard.putData("Auto Defence", autoCrossChooser); //Places the choose menu for selecting a crossing command on the dashboard
	        
	        autoShootChooser = new SendableChooser(); //Initializes the choose menu for selecting shooting command
	        autoShootChooser.addDefault("Don't Shoot", new AutoShootNothing()); //adds do nothing as an option for shooting
	        autoShootChooser.addObject("Pos 1", new AutoShootHigh1()); //adds shoot high from position 1 as an option for shooting
	        autoShootChooser.addObject("Pos 2", new AutoShootHigh2()); //adds shoot high from position 2 as an option for shooting
	        autoShootChooser.addObject("Pos 3", new AutoShootHigh3()); //adds shoot high from position 3 as an option for shooting
	        autoShootChooser.addObject("Pos 4", new AutoShootHigh4()); //adds shoot high from position 4 as an option for shooting
	        autoShootChooser.addObject("Pos 5", new AutoShootHigh5()); //adds shoot high from position 5 as an option for shooting
	        SmartDashboard.putData("Auto Position: ", autoShootChooser); //Places the choose menu for selecting a shooting command on the dashboard

    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        visionCalc.run();
        SmartDashboard.putNumber("Arm Angle", shooterArm.getArmAngle());
        SmartDashboard.putNumber("Current", robotIO.pdp.getTotalCurrent());
        SmartDashboard.putNumber("irR", robotIO.rightBallIR.getAverageVoltage());
        SmartDashboard.putNumber("irL", robotIO.leftBallIR.getAverageVoltage());
        SmartDashboard.putNumber("irBall", robotIO.ballInIR.getAverageVoltage());
        SmartDashboard.putNumber("camera", robotIO.driverStick.getZ());
        SmartDashboard.putBoolean("is in", shooterPiston.ballInShooter());
        SmartDashboard.putNumber("camera IR", robotIO.cameraIR.getAverageVoltage());
        SmartDashboard.putNumber("Distance Traveled: ", drivetrain.currentDistance());
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
