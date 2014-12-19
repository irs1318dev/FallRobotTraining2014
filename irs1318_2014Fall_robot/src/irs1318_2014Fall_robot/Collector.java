package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class Collector extends RobotComponentBase
{
	private static final int SIDECAR_SLOT = SensorBase.getDefaultDigitalModule();;
	private static final int DIGITAL_IO = SensorBase.getDefaultSolenoidModule();
	
	private static final int JOYSTICK_PORT = 1;
		
	private static final int COLLECTOR_MOTOR_CHANNEL = 3;

	private static final int COLLECTOR_SOLENOID_MODULE_PORT = 2;

	private static final int COLLECTOR_EXTENDER_SOLENOID_CHANNEL = 5;
	private static final int COLLECTOR_RETRACTOR_SOLENOID_CHANNEL = 6;
	
	private Joystick joystick;	
	private Talon collectorTalon;	
	private DoubleSolenoid collectorSolenoid;
	
	public void robotInit()
	{
		this.joystick = new Joystick(JOYSTICK_PORT);
		
		this.collectorSolenoid = new DoubleSolenoid(COLLECTOR_SOLENOID_MODULE_PORT, COLLECTOR_EXTENDER_SOLENOID_CHANNEL, COLLECTOR_RETRACTOR_SOLENOID_CHANNEL);
	
		this.collectorTalon = new Talon(SIDECAR_SLOT, COLLECTOR_MOTOR_CHANNEL);
	}
	
	public void teleopPeriodic()
	{
		// get the X and Y values from the joystick
		boolean in = this.joystick.getRawButton(1);
		boolean out = this.joystick.getRawButton(2);
		
		if (in)
		{
		}
		else if (out)
		{
		}
	}
}
