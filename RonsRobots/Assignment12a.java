package RonsRobots;

import java.awt.Color;
import java.util.Random;

import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.RobotStatus;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;

public class Assignment12a extends Robot {
//	private double angle = 90;
//	private double distance = 100;
	private int opponents = 0;
	private double[] opponentDir;
	private int opponentCount;
	private enum Mode {Scan, Move};
	private Mode mode = Mode.Scan;
	private Random random = new Random();

	/**
	 * Robot's run method - Square-ish
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.green);
		setGunColor(Color.black);
		setRadarColor(Color.red);
		setScanColor(Color.red);

		scanAll();

		while (true) {
			switch(mode)
			{
			case Scan:
//				setAdjustRadarForGunTurn(false);
				turnRadarRight(360);
				break;
			case Move:
				for(int i = 0; i< opponents ; i++ )
				{	
					double gunHeading = getGunHeading();
					double gunTurn = opponentDir[i] - gunHeading;
//					setAdjustRadarForGunTurn(true);
					
					System.out.println("Turning gun heading: " + gunHeading + " Left " + gunTurn + " degrees.");
					turnGunLeft( gunTurn );
				}
				turnLeft(opponentDir[0]);
				ahead(50);
				scanAll();
				break;
			}
		}
	}
	

	public void scanAll(){
		opponents = getOthers();
		opponentDir = new double[opponents];
		opponentCount = 0;
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
				opponentDir[opponentCount] = e.getBearing() + getHeading();
				System.out.println("Target at: " + opponentDir[opponentCount] );
				if( ++opponentCount >= opponents ) mode = Mode.Move;
				break;
			case Move:
				fireBullet(3);		
				break;
			}
	}
	/**
	 * onHitRobot:  If it's our fault, we'll stop turning and moving,
	 * so we need to turn again to keep moving.
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(1);
		}
		if (e.isMyFault()) {
			turnRight(random.nextInt(30) - 15);
		}
	} // onHitRobot
	
	public void onStatus(StatusEvent e)
	{
		RobotStatus es = e.getStatus();
		es.getGunTurnRemaining();
	}
}
