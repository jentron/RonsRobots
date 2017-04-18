package RonsRobots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.RobotStatus;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;

import utilities.EnemyBot;

public class Assignment13 extends AdvancedRobot {
	private double angle = 45;
	private double distance = 314;
	private int opponents = 0;
	private int totalOpponents = 0;
	private EnemyBot[] opponentBot;
	private int opponentCount;
	private enum Mode {Scan, Move};
	private Mode mode = Mode.Scan;
	private Random random = new Random();
	private int target;

	public void init(){
		// Set colors
		setBodyColor(Color.green);
		setGunColor(Color.black);
		setRadarColor(Color.blue);
		setScanColor(Color.red);		
// since we do this first, it should be the total number of opponents
		totalOpponents = getOthers();
		opponentBot = new EnemyBot[totalOpponents];
		for(int i = 0; i< totalOpponents; i++) opponentBot[i] = new EnemyBot(this);
		
	}
	
	public void run() {
		// is there really no way to call a constructor in Robot?
		init();
		// Loop forever
		scanAll();

		while (true) {
			// Tell the game that when we take move,
			// we'll also want to turn right... a lot.
			System.out.println("Looping! " + " Turn = " + getTime());
			switch(mode)
			{
			case Scan:
				setAdjustRadarForGunTurn(false);
				turnRadarRight(360);
				break;
			case Move:
				int target = 0;
//				for(int i = 0; i< opponents ; i++ )
				// CHALLENGE 2: Pick a the closest robot to target
				for(int i = 1; i< opponents ; i++ )
				{
					if(opponentBot[i].getDistance() < opponentBot[target].getDistance())
					{
						target = i;
					}
				}
				System.out.println("Targeting " + opponentBot[target].getName() + " who is " + opponentBot[target].getDistance() + " away");

					double gunHeading = getGunHeading();
					double gunTurn = robocode.util.Utils.normalRelativeAngleDegrees(opponentBot[target].getBearing() - gunHeading);
					setAdjustRadarForGunTurn(true);
					setAdjustGunForRobotTurn(true);
					System.out.println("Gun heading: " + gunHeading + ", OpponentDir: " + opponentBot[target].getBearing() + ", Turn Amount: " + gunTurn + " degrees.");
					if(Math.abs(gunTurn) > 1.0){
						turnGunRight( gunTurn );						
					} else {
						System.out.println("Firing, gun heat: " + getGunHeat());
						fireBullet(3);		
					}
					
// finally, move the tank
				double robotHeading = getHeading();
				double robotTurn = robocode.util.Utils.normalRelativeAngleDegrees(opponentBot[target].getBearing() - robotHeading + angle);// + random.nextInt(60) - 30.0);
				setTurnRight(robotTurn);
				angle *= -1;
				distance = opponentBot[target].getDistance();
				setAhead(distance);
				scanAll();
				break;
			}
			// execute all the queued moves
//            waitFor(new TurnCompleteCondition(this));
//            waitFor(new MoveCompleteCondition(this));
			execute();
		} // while forever loop

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
	/**
	 * onHitRobot:  If it's our fault, we'll stop turning and moving,
	 * so we need to turn again to keep spinning.
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(3);
		} else {
			turnRight(random.nextInt(180) - 90);
			setAhead(50);
		}
	
		if (e.isMyFault()) {
			turnRight(10);
		}
	} // onHitRobot
	
	public void onStatus(StatusEvent e)
	{
		RobotStatus es = e.getStatus();
		es.getGunTurnRemaining();
	}
	
	 // Paint a transparent square on top of the last scanned robot
	 public void onPaint(Graphics2D g) {
	     // Set the paint color to a red half transparent color
	     g.setColor(new Color(0xff, 0x00, 0x00, 0x80));
	 
	     // Draw a line from our robot to the scanned robot
	     g.drawLine(opponentBot[0].iX, opponentBot[0].iY, (int)getX(), (int)getY());
	 
	     // Draw a filled square on top of the scanned robot that covers it
	     g.fillRect(opponentBot[0].iX - 20, opponentBot[0].iY - 20, 40, 40);
	 }

}

