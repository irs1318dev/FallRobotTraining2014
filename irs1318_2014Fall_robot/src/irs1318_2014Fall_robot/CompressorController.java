package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class CompressorController extends RobotComponentBase
{	
	private Compressor compressor;
	
	public void robotInit()
	{
		this.compressor = new Compressor(ElectronicsConstants.SIDECAR_SLOT, ElectronicsConstants.COMPRESSOR_PRESSURE_SWITCH_CHANNEL, ElectronicsConstants.DIGITAL_IO, ElectronicsConstants.COMPRESSOR_RELAY_CHANNEL);
		this.compressor.start();
	}
}
