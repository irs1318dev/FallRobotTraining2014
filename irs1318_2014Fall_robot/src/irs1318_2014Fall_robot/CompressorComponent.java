package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.Compressor;

public class CompressorComponent
{
    private Compressor compressor;

    public CompressorComponent()
    {
        this.compressor = new Compressor(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.COMPRESSOR_PRESSURE_SWITCH_CHANNEL,
            ElectronicsConstants.DIGITAL_IO,
            ElectronicsConstants.COMPRESSOR_RELAY_CHANNEL);
    }
    
    public void start()
    {
        this.compressor.start();
    }
}
