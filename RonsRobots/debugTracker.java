package RonsRobots;

import java.awt.Color;
import java.awt.Graphics2D;

import robocode.Robot;
import robocode.ScannedRobotEvent;

public class debugTracker extends Robot {
	 // How to run our robot
	 public void run() {
	     // Rotate the radar for ever in order to detect robots
	     while (true) {
	         turnRadarRight(360);
	     }
	 }
	 
	 // The coordinates of the last scanned robot
	 int scannedX = Integer.MIN_VALUE;
	 int scannedY = Integer.MIN_VALUE;
	 
	 // Called when we have scanned a robot
	 public void onScannedRobot(ScannedRobotEvent e) {
	     // Calculate the angle to the scanned robot
	     double angle = Math.toRadians((getHeading() + e.getBearing()) % 360);
	 
	     // Calculate the coordinates of the robot
	     scannedX = (int)(getX() + Math.sin(angle) * e.getDistance());
	     scannedY = (int)(getY() + Math.cos(angle) * e.getDistance());
	 }
	 
	 // Paint a transparent square on top of the last scanned robot
	 public void onPaint(Graphics2D g) {
	     // Set the paint color to a red half transparent color
	     g.setColor(new Color(0xff, 0x00, 0x00, 0x80));
	 
	     // Draw a line from our robot to the scanned robot
	     g.drawLine(scannedX, scannedY, (int)getX(), (int)getY());
	 
	     // Draw a filled square on top of the scanned robot that covers it
	     g.fillRect(scannedX - 20, scannedY - 20, 40, 40);
	 }
}
