package irs1318_2014Fall_robot.Compressor;

import irs1318_2014Fall_robot.ElectronicsConstants;
import edu.wpi.first.wpilibj.*;

public class CompressorComponent
{
    private Compressor compressor;
    private AnalogChannel analogPressureSensor;

    /**
     * Initializes a new CompressorComponent
     */
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

    /**
     * Start the compressor 
     */
    public void start()
    {
        this.compressor.start();
    }

    /**
     * Stop the compressor
     */
    public void stop()
    {
        this.compressor.stop();
    }

    /**
     * Get the PSI in the pneumatic system
     * @return the current PSI
     */
    public double getPSI()
    {
        return this.analogPressureSensor.getVoltage() * (ElectronicsConstants.COMPRESSOR_MAX_PSI / ElectronicsConstants.COMPRESSOR_MAX_VOLTAGE);
    }
}
