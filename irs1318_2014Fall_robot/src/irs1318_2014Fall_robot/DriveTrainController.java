package irs1318_2014Fall_robot;
import com.sun.squawk.util.Assert;

import edu.wpi.first.wpilibj.*;

public class DriveTrainController
{
	private static final int JOYSTICK_PORT = 1;
	
	private static final double DEAD_ZONE = 0.1;
	private static final double MAX_SPEED = 0.8;
	
	private static final double A = 0.2;
	private static final double B = 1.0;

	private static final double POWERLEVEL_MIN = -1.0;
	private static final double POWERLEVEL_MAX = 1.0;
	
	private Joystick joystick;
	
	private Talon leftTalon;
	private Talon rightTalon;
	
	public DriveTrainController(Joystick joystick)
	{
		this.joystick = joystick;
		
		this.leftTalon = new Talon(ElectronicsConstants.SIDECAR_SLOT, ElectronicsConstants.DRIVETRAIN_LEFT_TALON_CHANNEL);
		this.rightTalon = new Talon(ElectronicsConstants.SIDECAR_SLOT, ElectronicsConstants.DRIVETRAIN_RIGHT_TALON_CHANNEL);
	}
	
	public void teleopPeriodic()
	{
		boolean simpleDriveModeEnabled = this.joystick.getRawButton(ButtonConstants.DRIVETRAIN_SIMPLE_BUTTON);
		
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
		if (radius > DriveTrainController.DEAD_ZONE)
		{
			if (simpleDriveModeEnabled)
			{
				// simple drive enables either forward/back or in-place left/right turn only
				//
				//                     forward
				//                 ---------------
				//                 |      |      |
				//                 |      |      |
				//  In-place left  |-------------| In-place right
				//                 |      |      |
				//                 |      |      |
				//                 ---------------
				//                     backward
				//
				
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
			else
			{
				// advanced drive enables varying-degree turns
				//
				//      a,1    1,1    1,a
				//       ---------------
				//       |      |      |
				//       |      |      |
				//  -b,b |-------------| b,-b
				//       |      |      |
				//       |      |      |
				//       ---------------
				//     -a,-1  -1,-1  -1,-a
				//
				
				if (x >= 0)
				{
					if (y >= 0)
					{
						leftPower = x*B + y * (1 - x*B);
						rightPower = -x*B + y * (1 - x*(1 - A) + x*B);
					}
					else
					{
						leftPower = x*B - y*(-1 -x*B);
						rightPower = -x*B - y*(-1 + x*(1 - A) + x*B);
					}
				}
				else
				{
					if (y >= 0)
					{
						leftPower = -x*B + y*(1 + x*(1 - A) + x*B);
						rightPower = x*B + y*(1 - x*B);
					}
					else
					{
						leftPower = -x*B - y*(-1 + x*(1 - A) + x*B);
						rightPower = x*B - y*(-1 - x*B);
					}
				}
			}
		}
		
		// ensure that our algorithms are correct and don't give values outside the appropriate range
		this.assertPowerLevelRange(leftPower, "left");
		this.assertPowerLevelRange(rightPower, "right");
		
		// decrease the power based on the desired max speed
		leftPower = leftPower * DriveTrainController.MAX_SPEED;
		rightPower = rightPower * DriveTrainController.MAX_SPEED;
		
		// apply the speed to the motors
		this.leftTalon.set(-leftPower); // left motors are oriented facing "backwards"
		this.rightTalon.set(rightPower);
	}
	
	private void assertPowerLevelRange(double powerLevel, String side)
	{
		Assert.that(powerLevel < POWERLEVEL_MIN, side + " power level too low!");
		Assert.that(powerLevel > POWERLEVEL_MAX, side + " power level too high!");
	}

	private double adjustIntensity(double value)
	{
		// we will use simple quadratic scaling to adjust input intensity
		return value * value;
	}
}
