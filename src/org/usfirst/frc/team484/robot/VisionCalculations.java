package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionCalculations {
	private double radiansPerPixel = 0.00119;
	private double radiansPerPixelHorizontal = 0.0012544553999;
	private double cameraHeight = 12.25;
	private double cameraHorizontalOffset = 11.59375;
	private double cameraImageWidth = 640.0;
	private double cameraImageHeight = 480.0;
	private double cameraAngleUp = 0.376;
	private double heightOfGoalCenter = 89.25;
	private double goalWidth = 20;

	public double lastHorizontal = Double.NaN;
	public double lastDistance = Double.NaN;
	public double lastAngle = Double.NaN;
	public static void main(String[] args) {
		new VisionCalculations().run();
	}
	public void run() {
		NetworkTable visionTable = NetworkTable.getTable("GRIP/vision");
		try {
			double[] centerX = visionTable.getNumberArray("centerX", new double[0]);
			double[] centerY = visionTable.getNumberArray("centerY", new double[0]);
			double[] area = visionTable.getNumberArray("area", new double[0]);
			double[] width = visionTable.getNumberArray("width", new double[0]);
			double[] height = visionTable.getNumberArray("height", new double[0]);
			int i = 0;
			double maxArea = 0.0;
			int maxAreaSpot = -1;
			while (i < centerX.length) {
				if (area[i] > maxArea) {
					maxArea = area[i];
					maxAreaSpot = i;
				}
				i++;
			}
			if (maxAreaSpot > -1) {
				double horizontalAngleCenter = 1.5707963268 - (cameraImageWidth / 2.0 - centerX[maxAreaSpot]) * radiansPerPixelHorizontal;
				double horizontalAngleRight = 1.5707963268 - (cameraImageWidth / 2.0 - (centerX[maxAreaSpot] + width[maxAreaSpot])) * radiansPerPixelHorizontal;
				double verticalAngleCenter =  cameraAngleUp + (cameraImageHeight - centerY[maxAreaSpot] - 1) * radiansPerPixel;
				double distance = (heightOfGoalCenter - cameraHeight)/Math.tan(verticalAngleCenter);
				double angularDistance = (heightOfGoalCenter - cameraHeight)/Math.sin(verticalAngleCenter);
				double horizontalOffset1 = angularDistance / Math.tan(horizontalAngleCenter) - cameraHorizontalOffset;
				double horizontalOffset2 = angularDistance / Math.tan(horizontalAngleRight) - (cameraHorizontalOffset - goalWidth/2.0) + cameraHorizontalOffset;				
				if (centerY[maxAreaSpot] + height[maxAreaSpot]/2.0 >= 479 || centerY[maxAreaSpot] - height[maxAreaSpot]/2.0 <= 1) {
					noTarget();
				} else {
					//System.out.println("D: " + distance + "  h1: " + horizontalOffset1 + " h2: " + horizontalOffset2);
					//System.out.println("Too low by: " + (shootAngle - (1.5707963268 + Robot.robotIO.shooterArmEncoder.getDistance())));
					lastAngle = -Math.atan(Math.log(-373*(distance * distance - 938.338 * distance - 33746.6))-17.0344);
					lastHorizontal = horizontalOffset1;
					lastDistance = distance;
					SmartDashboard.putNumber("Distance", distance);
					SmartDashboard.putNumber("H1", horizontalOffset1);
					SmartDashboard.putNumber("H2", horizontalOffset2);
					SmartDashboard.putNumber("Proper Angle", lastAngle);
				}
			} else {
				noTarget();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void noTarget() {
		lastHorizontal = Double.NaN;
		lastDistance = Double.NaN;
		lastAngle = Double.NaN;
		SmartDashboard.putNumber("Distance", Double.NaN);
		SmartDashboard.putNumber("H1", Double.NaN);
		SmartDashboard.putNumber("H2", Double.NaN);
		SmartDashboard.putNumber("Proper Angle", Double.NaN);
	}

}
