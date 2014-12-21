package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class CollectorController
{
	private static final double COLLECTOR_SPEED = 0.8;
	
	private Joystick joystick;	
	private Talon collectorTalon;	
	private DoubleSolenoid collectorSolenoid;
	
	public CollectorController(Joystick joystick)
	{
		this.joystick = joystick;
		
		this.collectorSolenoid = new DoubleSolenoid(ElectronicsConstants.COLLECTOR_SOLENOID_MODULE_PORT, ElectronicsConstants.COLLECTOR_EXTENDER_SOLENOID_CHANNEL, ElectronicsConstants.COLLECTOR_RETRACTOR_SOLENOID_CHANNEL);
	
		this.collectorTalon = new Talon(ElectronicsConstants.SIDECAR_SLOT, ElectronicsConstants.COLLECTOR_MOTOR_CHANNEL);
	}
	
	public void teleopPeriodic()
	{
		// get the X and Y values from the joystick
		boolean extend = this.joystick.getRawButton(ButtonConstants.COLLECTOR_EXTEND_BUTTON);
		boolean retract = this.joystick.getRawButton(ButtonConstants.COLLECTOR_RETRACT_BUTTON);
		boolean collect = this.joystick.getRawButton(ButtonConstants.COLLECTOR_COLLECT_BUTTON);
		boolean expel = this.joystick.getRawButton(ButtonConstants.COLLECTOR_EXPEL_BUTTON);
		
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
