package irs1318_2014Fall_robot.Collector;

import irs1318_2014Fall_robot.ElectronicsConstants;
import edu.wpi.first.wpilibj.*;

public class CollectorComponent implements ICollectorComponent
{
    private Talon collectorTalon;
    private DoubleSolenoid collectorSolenoid;
    private DigitalInput collectorLimitSwitch;
    
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
    
    public boolean readLimitSwitch()
    {
        // note: this wasn't really used last year except possibly for the "SmartDash"
        return this.collectorLimitSwitch.get();
    }

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
    }
}
