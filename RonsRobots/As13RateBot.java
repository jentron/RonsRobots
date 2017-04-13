package RonsRobots;

import java.awt.Color;
import java.util.Random;

import robocode.RateControlRobot;
import robocode.ScannedRobotEvent;
import utilities.EnemyBot;
import utilities.Vector3D;

public class As13RateBot extends RateControlRobot {
	private double angle = 15;
	private double distance = 314;
	private int opponents = 0;
	private int totalOpponents = 0;
	private EnemyBot[] opponentBot;
	private int opponentCount;
	private enum Mode {Scan, Move};
	private Mode mode = Mode.Scan;
	private Random random = new Random();
	private int turnTimer;
	private boolean doScan;
	
	public Vector3D boardCenter;
	public Vector3D myPosi = new Vector3D(0,0);

	public void init(){
		// Set colors
		setBodyColor(Color.green);
		setGunColor(Color.black);
		setRadarColor(Color.blue);
		setScanColor(Color.red);		
// since we do this first, it should be the total number of opponents
		totalOpponents = getOthers();
		opponentBot = new EnemyBot[totalOpponents];
		for(int i = 0; i< totalOpponents; i++) opponentBot[i] = new EnemyBot();
		boardCenter = new Vector3D(getWidth(),getHeight()); // first the width and height
		boardCenter.multi(0.5); // then divide by 2
		
	}
	
	public void run() {
		// is there really no way to call a constructor in Robot?
		init();
		// Loop forever
		scanAll();
		double myTurnRate;
		
		while (true) {
			System.out.println("Looping! " + " Turn = " + getTime());
			myPosi.set(getX(), getY());
			Vector3D drive = new Vector3D(boardCenter);
			drive.subtract(myPosi);
			drive.multi(-1);
			double desiredHeading = (drive.angle() * 180/Math.PI);
			double error = desiredHeading - getHeading();
//			if(error > 360 ) error -= 360;
//			if(error > 180 ) error -= 180;
//			if(myTurnRate >  180 ) myTurnRate -= 360;
//			System.out.println("Board Center is " + drive.magnitude() + "units away");
			System.out.println("Heading: " + getHeading() + " Desired: " + desiredHeading + " Error:" +error);

			if(turnTimer < 50 && doScan)
			{
				doScan = false;
//				scanAll(); // every 100 turn or so
			}
			setTurnRate(5);// let the system clip
			setVelocityRate(5);
			
			if(turnTimer--< 0)
			{
				turnTimer = 100; // restart the timer
				doScan = true;
			}
			execute();
		}
	}
	
	public void scanAll(){
		opponents = getOthers();
		opponentCount = 0;
		for(int i = 0; i< totalOpponents; i++) opponentBot[i].reset();
		mode = Mode.Scan;
		System.out.println("There are :" + opponents + " others."); 
	}
	/**
	 * Fire when we see a robot
	 */
	
	public void onScannedRobot(ScannedRobotEvent e) {
			switch(mode)
			{
			case Scan:
				opponentBot[opponentCount].update(e);
				// getBearing is relative to tank body, so change it to world
				opponentBot[opponentCount].setBearing(opponentBot[opponentCount].getBearing() + getHeading());
				System.out.println("Target at: " + opponentBot[opponentCount].getBearing() );
				if( ++opponentCount >= opponents ) mode = Mode.Move;
				break;
			case Move:
				fireBullet(3);		
				break;
			}
	}

}
