package irs1318_2014Fall_robot.Compressor;

import irs1318_2014Fall_robot.ElectronicsConstants;
import irs1318_2014Fall_robot.Common.SmartDashboardLogger;
import edu.wpi.first.wpilibj.*;

/**
 * The compressor component class describes the electronics of the compressor and defines the abstract way to control it.
 * The electronics include a compressor, and an analog pressure sensor. 
 *  
 * @author Will
 *
 */
public class CompressorComponent
{
    // logging constants
    private static final String RUNNING_LOG_KEY = "cm.r";
    private static final String PSI_LOG_KEY = "cm.p";

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

        SmartDashboardLogger.putBoolean(CompressorComponent.RUNNING_LOG_KEY, true);
    }

    /**
     * Stop the compressor
     */
    public void stop()
    {
        this.compressor.stop();

        SmartDashboardLogger.putBoolean(CompressorComponent.RUNNING_LOG_KEY, false);
    }

    /**
     * Get the PSI in the pneumatic system
     * @return the current PSI
     */
    public double getPSI()
    {
        double psi = this.analogPressureSensor.getVoltage() * (ElectronicsConstants.COMPRESSOR_MAX_PSI / ElectronicsConstants.COMPRESSOR_MAX_VOLTAGE);

        SmartDashboardLogger.putNumber(CompressorComponent.PSI_LOG_KEY, psi);

        return psi;
    }
}
