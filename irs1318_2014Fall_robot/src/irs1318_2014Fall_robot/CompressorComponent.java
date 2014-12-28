package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class CompressorComponent
{
    private Compressor compressor;
    private AnalogChannel analogPressureSensor;

    public CompressorComponent()
    {
        this.compressor = new Compressor(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.COMPRESSOR_PRESSURE_SWITCH_CHANNEL,
            ElectronicsConstants.DIGITAL_IO,
            ElectronicsConstants.COMPRESSOR_RELAY_CHANNEL);
        
        this.analogPressureSensor = new AnalogChannel(
            ElectronicsConstants.ANALOG_MODULE,
            ElectronicsConstants.COMPRESSOR_ANALOG_PRESSURE_SENSOR);
    }

    public void start()
    {
        this.compressor.start();
    }

    public double getPSI()
    {
        return this.analogPressureSensor.getVoltage() * (ElectronicsConstants.COMPRESSOR_MAX_PSI / 10.0);
    }
}
