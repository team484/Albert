
package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team484.robot.subsystems.Drivetrain;
import org.usfirst.frc.team484.robot.subsystems.ShooterArm;
import org.usfirst.frc.team484.robot.subsystems.ShooterPiston;
import org.usfirst.frc.team484.robot.subsystems.ShooterWheels;

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
	public static OI oi;
	CameraServer camera;

    public void robotInit() {
		oi = new OI();
		robotIO.airCompressor.start();
		try {
			camera = CameraServer.getInstance();
			camera.startAutomaticCapture("cam1");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
        	robotIO.cameraServo.set(robotIO.driverStick.getZ());
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
