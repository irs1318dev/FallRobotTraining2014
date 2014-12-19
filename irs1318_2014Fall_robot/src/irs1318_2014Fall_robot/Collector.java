package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class Collector extends RobotComponentBase
{
	private static final int SIDECAR_SLOT = SensorBase.getDefaultDigitalModule();;
	private static final int DIGITAL_IO = SensorBase.getDefaultSolenoidModule();
	
	private static final int JOYSTICK_PORT = 1;
	
	private static final int COLLECTOR_MOTOR_CHANNEL = 3;
	
	private static final int COLLECTOR_SOLENOID_MODULE_PORT = 2;
	
	private static final int COLLECTOR_EXTENDER_SOLENOID_CHANNEL = 4;
	private static final int COLLECTOR_RETRACTOR_SOLENOID_CHANNEL = 3;
	
	private static final int EXTEND_BUTTON = 1;
	private static final int RETRACT_BUTTON = 2;
	
	private static final int COLLECT_BUTTON = 3;
	private static final int EXPEL_BUTTON = 4;
	
	private static final double COLLECTOR_SPEED = 0.8;
	
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
		boolean extend = this.joystick.getRawButton(EXTEND_BUTTON);
		boolean retract = this.joystick.getRawButton(RETRACT_BUTTON);
		boolean collect = this.joystick.getRawButton(COLLECT_BUTTON);
		boolean expel = this.joystick.getRawButton(EXPEL_BUTTON);
		
		if (extend)
		{
			this.collectorSolenoid.set(DoubleSolenoid.Value.kForward);
		}
		else if (retract)
		{
			this.collectorSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
		else
		{
			this.collectorSolenoid.set(DoubleSolenoid.Value.kOff);
		}
		
		if (collect)
		{
			this.collectorTalon.set(COLLECTOR_SPEED);
		}
		else if (expel)
		{
			this.collectorTalon.set(-COLLECTOR_SPEED);
		}
	}
}
