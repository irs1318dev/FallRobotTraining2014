package irs1318_2014Fall_robot;
import java.util.*;
import edu.wpi.first.wpilibj.*;

public class BasicOneStickDriveTrainComponent extends RobotComponentBase
{
	private static final int JOYSTICK_PORT = 1;
	
	private static final int TALON_PORT = 1;
	
	private static final int LEFT_TALON_CHANNEL = 1;
	private static final int RIGHT_TALON_CHANNEL = 2;
	
	private static final double DEAD_ZONE = 0.2;
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
		double x = this.joystick.getX();
		double y = this.joystick.getY();

		double leftPower = 0.0;
		double rightPower = 0.0;
		
		double radius = Math.sqrt(x*x + y*y);
		if (radius > BasicOneStickDriveTrainComponent.DEAD_ZONE)
		{
			if (Math.abs(y) < Math.abs(x))
			{
				leftPower = BasicOneStickDriveTrainComponent.MAX_SPEED * x;
				rightPower = -leftPower;
			}
			else
			{
				leftPower = BasicOneStickDriveTrainComponent.MAX_SPEED * y;
				rightPower = leftPower;
			}
		}
		
		this.leftTalon.set(leftPower);
		this.rightTalon.set(rightPower);
	}
}