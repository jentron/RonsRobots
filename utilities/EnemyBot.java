package utilities;

import robocode.ScannedRobotEvent;
/**
 * EnemyBot is a class borrowed from https://github.com/AdyCastEX/u1l
 * This is a simple getter/setter class to store data about the opponents,
 * but it is well done.
 */
public class EnemyBot
{
	private double bearing;
	private double distance;
	private double energy;
	private double heading;
	private double velocity;
	private String name;
	
	public EnemyBot()
	{
		reset();
	}
	
	public void reset()
	{
		this.bearing = 0.0;
		this.distance = 0.0;
		this.energy = 0.0;
		this.heading = 0.0;
		this.velocity = 0.0;
		this.name = "";
	}
	
	public void update(ScannedRobotEvent e)
	{
		this.bearing = e.getBearing();
		this.distance = e.getDistance();
		this.energy = e.getEnergy();
		this.heading = e.getHeading();
		this.velocity = e.getVelocity();
		this.name = e.getName();
	}
	
	public boolean none()
	{
		if(this.name.equals("")) return true;
		else return false;
	}

	//getter-setter section start
	public double getBearing()
	{
		return this.bearing;
	}
	
	public void setBearing(double newBearing)
	{
		this.bearing = newBearing;
	}
	
	public double getDistance()
	{
		return this.distance;
	}
	
	public void setDistance(double newDistance)
	{
		this.distance = newDistance;
	}
	
	public double getEnergy()
	{
		return this.energy;
	}
	
	public void setEnergy(double newEnergy)
	{
		this.energy = newEnergy;
	}
	
	public double getHeading()
	{
		return this.heading;
	}
	
	public void setHeading(double newHeading)
	{
		this.heading = newHeading;
	}
	
	public double getVelocity()
	{
		return this.velocity;
	}
	
	public void setVelocity(double newVelocity)
	{
		this.velocity = newVelocity;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	//getter-setter section end

	

}