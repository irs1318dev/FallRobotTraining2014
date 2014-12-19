package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.SensorBase;

public class ElectronicsConstants
{
	public static final int SIDECAR_SLOT = SensorBase.getDefaultDigitalModule();
	public static final int DIGITAL_IO = SensorBase.getDefaultSolenoidModule();

	public static final int DRIVETRAIN_LEFT_TALON_CHANNEL = 1;
	public static final int DRIVETRAIN_RIGHT_TALON_CHANNEL = 2;
	
	public static final int COLLECTOR_MOTOR_CHANNEL = 3;
	
	public static final int COLLECTOR_SOLENOID_MODULE_PORT = 2;
	
	public static final int COLLECTOR_EXTENDER_SOLENOID_CHANNEL = 4;
	public static final int COLLECTOR_RETRACTOR_SOLENOID_CHANNEL = 3;
	
	public static final int COMPRESSOR_PRESSURE_SWITCH_CHANNEL = 6;
	public static final int COMPRESSOR_RELAY_CHANNEL = 1;
}
