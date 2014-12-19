package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class CompressorController extends RobotComponentBase
{
	private static final int JOYSTICK_PORT = 1;
	
	private static final int SIDECAR_SLOT = SensorBase.getDefaultDigitalModule();
	private static final int DIGITAL_IO = SensorBase.getDefaultSolenoidModule();
	
	private static final int PRESSURE_SWITCH_CHANNEL = 6;
	private static final int COMPRESSOR_RELAY_CHANNEL = 1;
	
	private Compressor compressor;
	
	public void robotInit()
	{
		this.compressor = new Compressor(SIDECAR_SLOT, PRESSURE_SWITCH_CHANNEL, DIGITAL_IO, COMPRESSOR_RELAY_CHANNEL);
		this.compressor.start();
	}
}
