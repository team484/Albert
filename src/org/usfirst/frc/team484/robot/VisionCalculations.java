package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionCalculations {
	double radiansPerPixel = 0.00133;
	double cameraHeight = 12.25;
	double cameraHorizontalOffset = 11.59375;
	double cameraImageWidth = 640.0;
	double cameraImageHeight = 479.0;
	double cameraAngleUp = 0.345915; //0.0174533 converts to radians
	double heightOfShotInGoal = 15.0;
	double heightOfGoalBottom = 83.5;
	double heightOfGoalTop = 95.5;
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
				double verticalAngleBottom = cameraAngleUp + ((cameraImageHeight - centerY[maxAreaSpot]) - height[maxAreaSpot]/2.0) * radiansPerPixel;
				double verticalAngleTop = cameraAngleUp + ((cameraImageHeight - centerY[maxAreaSpot]) + height[maxAreaSpot]/2.0) * radiansPerPixel;
				double horizontalAngleCenter = 1.5707963268 - (cameraImageWidth / 2.0 - centerX[maxAreaSpot]) * radiansPerPixel;
				
				double distance1 = (heightOfGoalBottom - cameraHeight)/Math.tan(verticalAngleBottom);
				double distance2 = (heightOfGoalTop - cameraHeight)/Math.tan(verticalAngleTop);
				double horizontalOffset1 = (Math.cos(horizontalAngleCenter) * distance1 - cameraHorizontalOffset * Math.sin(horizontalAngleCenter))/Math.sin(horizontalAngleCenter);
				double horizontalOffset2 = (Math.cos(horizontalAngleCenter) * distance2 - cameraHorizontalOffset * Math.sin(horizontalAngleCenter))/Math.sin(horizontalAngleCenter);
				double shootAngle = Math.atan((heightOfGoalBottom + heightOfShotInGoal)/distance1);
				System.out.println("D1: " + distance1 + "  h1: " + horizontalOffset1);
				System.out.println("D2: " + distance2 + "  h2: " + horizontalOffset2);
				System.out.println("Too low by: " + (shootAngle - (1.5707963268 + Robot.robotIO.shooterArmEncoder.getDistance())));
			} else {
				System.out.println("Target Not Found");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
