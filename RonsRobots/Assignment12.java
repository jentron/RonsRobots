package RonsRobots;

import java.awt.Color;

import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class Assignment12 extends Robot {
	private double angle = 90;
	private double distance = 100;
	/**
	 * Robot's run method - Square-ish
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.green);
		setGunColor(Color.black);
		setRadarColor(Color.blue);
		setScanColor(Color.red);
		
		while (true) {
			for(int i=0; i < 4; i++){ 
				ahead(distance); // Move
				turnLeft(angle); // Spin around
			}
			ahead(distance/2); // mix up the pattern a bit
			turnLeft(angle/2);
			angle *= -1; // then run it the other way
		}
	}
	
	/**
	 * Fire when we see a robot
	 */
	
	public void onScannedRobot(ScannedRobotEvent e) {
		fireBullet(1);
		System.out.println("Gun Heat: " + getGunHeat());
	}
	/**
	 * onHitRobot:  If it's our fault, we'll stop turning and moving,
	 * so we need to turn again to keep moving.
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(1);
		}
		System.out.println("Gun Heat: " + getGunHeat());
		if (e.isMyFault()) {
			turnRight(10);
		}
	}
}
