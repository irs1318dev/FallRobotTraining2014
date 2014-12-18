package irs1318_2014Fall_robot;
import edu.wpi.first.wpilibj.*;

public class BasicOneStickDriveTrain extends RobotComponentBase
{
	private static final int JOYSTICK_PORT = 1;
	
	private static final int TALON_PORT = 1;
	
	private static final int LEFT_TALON_CHANNEL = 1;
	private static final int RIGHT_TALON_CHANNEL = 2;
	
	private static final double DEAD_ZONE = 0.1;
	private static final double MAX_SPEED = 0.8;
	
	private Joystick joystick;
	
	private Talon leftTalon;
	private Talon rightTalon;
	
	public void robotInit()
	{
		this.joystick = new Joystick(JOYSTICK_PORT);
		
		this.leftTalon = new Talon(TALON_PORT, LEFT_TALON_CHANNEL);
		this.rightTalon = new Talon(TALON_PORT, RIGHT_TALON_CHANNEL);
	}
	
	public void teleopPeriodic()
	{
		// get the X and Y values from the joystick
		double x = this.joystick.getX();
		double y = this.joystick.getY();
		
		// adjust the intensity of the input 
		x = this.adjustIntensity(x);
		y = this.adjustIntensity(y);
		
		// default to no power
		double leftPower = 0.0;
		double rightPower = 0.0;
		
		// if we are outside of our dead zone, calculate desired power values 
		double radius = Math.sqrt(x*x + y*y);
		if (radius > BasicOneStickDriveTrain.DEAD_ZONE)
		{
			if (Math.abs(y) < Math.abs(x))
			{
				leftPower = -x;
				rightPower = x;
			}
			else
			{
				leftPower = y;
				rightPower = y;
			}
		}
		
		// decrease the power based on the desired max speed
		leftPower = leftPower * BasicOneStickDriveTrain.MAX_SPEED;
		rightPower = rightPower * BasicOneStickDriveTrain.MAX_SPEED;
		
		// apply the speed to the motors
		this.leftTalon.set(-leftPower);
		this.rightTalon.set(rightPower);
	}
	
	private double adjustIntensity(double value)
	{
		// we will use simple quadratic scaling to adjust input intensity
		return value * value;
	}
}
