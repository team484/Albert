package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionCalculations {
	double radiansPerPixel = 0.00119;
	double radiansPerPixelHorizontal = 0.0012544553999;
	double cameraHeight = 12.25;
	double cameraHorizontalOffset = 11.59375;
	double cameraImageWidth = 640.0;
	double cameraImageHeight = 480.0;
	double cameraAngleUp = 0.376; //0.0174533 converts to radians
	double heightOfShotInGoal = 15.0;
	double heightOfGoalBottom = 83.5;
	double heightOfGoalTop = 95.5;
	double heightOfGoalCenter = 89.25;
	double goalWidth = 20;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
				double horizontalOffset1 = distance / Math.tan(horizontalAngleCenter) - cameraHorizontalOffset;
				double horizontalOffset2 = distance / Math.tan(horizontalAngleRight) - (cameraHorizontalOffset - goalWidth/2.0);
				
				double shootAngle = Math.atan((heightOfGoalCenter + heightOfShotInGoal)/distance);
				if (centerY[maxAreaSpot] + height[maxAreaSpot]/2.0 >= 480) {
					//System.out.println("Camera At Max Distance");
				} else {
					//System.out.println("D: " + distance + "  h1: " + horizontalOffset1 + " h2: " + horizontalOffset2);
					//System.out.println("Too low by: " + (shootAngle - (1.5707963268 + Robot.robotIO.shooterArmEncoder.getDistance())));
					SmartDashboard.putNumber("Distance", distance);
					SmartDashboard.putNumber("H1", horizontalOffset1);
					SmartDashboard.putNumber("H2", horizontalOffset2);
				}
			} else {
				//System.out.println("Target Not Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
