package irs1318_2014Fall_robot.Collector;

import irs1318_2014Fall_robot.ElectronicsConstants;
import irs1318_2014Fall_robot.Common.SmartDashboardLogger;
import edu.wpi.first.wpilibj.*;

/**
 * The collector component class describes the electronics of the collector and defines the abstract way to control it.
 * The collector electronics include a solenoid, a talon, and a digital switch. 
 *  
 * @author Will
 *
 */
public class CollectorComponent implements ICollectorComponent
{
    // logging constants
    private static final String BALL_PRESENT_LOG_KEY = "cl.bp";
    private static final String COLLECT_POWER_LOG_KEY = "cl.ms";
    private static final String EXTEND_LOG_KEY = "cl.es";
    private static final String RETRACT_LOG_KEY = "cl.rs";

    private Talon collectorTalon;
    private DoubleSolenoid collectorSolenoid;
    private DigitalInput collectorLimitSwitch;

    /**
     * Initializes a new CollectorComponent
     */
    public CollectorComponent()
    {
        this.collectorSolenoid = new DoubleSolenoid(
            ElectronicsConstants.COLLECTOR_SOLENOID_MODULE_PORT,
            ElectronicsConstants.COLLECTOR_EXTENDER_SOLENOID_CHANNEL,
            ElectronicsConstants.COLLECTOR_RETRACTOR_SOLENOID_CHANNEL);

        this.collectorTalon = new Talon(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.COLLECTOR_MOTOR_CHANNEL);

        this.collectorLimitSwitch = new DigitalInput(
            ElectronicsConstants.DIGITAL_IO,
            ElectronicsConstants.COLLECTOR_LIMIT_SWITCH_PORT);
    }

    /**
     * Read the limit switch
     * @return the current value of the collector limit switch
     */
    public boolean readLimitSwitch()
    {
        boolean ballPresent = this.collectorLimitSwitch.get();

        SmartDashboardLogger.putBoolean(CollectorComponent.BALL_PRESENT_LOG_KEY, ballPresent);

        return ballPresent;
    }

    /**
     * set the collector's settings
     * @param extend the collector
     * @param retract the collector
     * @param collectorPower the power level to set the collector
     */
    public void setCollector(boolean extend, boolean retract, double collectorPower)
    {
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

        this.collectorTalon.set(collectorPower);

        SmartDashboardLogger.putNumber(CollectorComponent.COLLECT_POWER_LOG_KEY, collectorPower);
        SmartDashboardLogger.putBoolean(CollectorComponent.EXTEND_LOG_KEY, extend);
        SmartDashboardLogger.putBoolean(CollectorComponent.RETRACT_LOG_KEY, retract);
    }
}
