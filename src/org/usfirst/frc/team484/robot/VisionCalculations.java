package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionCalculations {
	double radiansPerPixel = 0.00125445;
	double cameraHeight = 12.25;
	double cameraHorizontalOffset = 11.5;
	double cameraImageWidth = 640.0;
	double cameraImageHeight = 479.0;
	double cameraAngleUp = 0.443448; //0.0174533 converts to radians
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
				double horizontalAngleCenter = 1.5708 - (cameraImageWidth / 2.0 - centerX[maxAreaSpot]) * radiansPerPixel;
				
				double distance1 = (83.5 - cameraHeight)/Math.tan(verticalAngleBottom);
				double distance2 = (95.5 - cameraHeight)/Math.tan(verticalAngleTop);
				double  horizontalOffset1 = (Math.cos(horizontalAngleCenter) * distance1 - cameraHorizontalOffset * Math.sin(horizontalAngleCenter))/Math.sin(horizontalAngleCenter);
				double  horizontalOffset2 = (Math.cos(horizontalAngleCenter) * distance2 - cameraHorizontalOffset * Math.sin(horizontalAngleCenter))/Math.sin(horizontalAngleCenter);
				System.out.println("Dist1: " + distance1 + "  horizOff1: " + horizontalOffset1);
				System.out.println("Dist2: " + distance2 + "  horizOff2: " + horizontalOffset2);
			} else {
				System.out.println("Target Not Found");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
