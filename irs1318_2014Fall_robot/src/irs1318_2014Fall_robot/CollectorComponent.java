package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;

public class CollectorComponent implements ICollectorComponent
{
    private Talon collectorTalon;
    private DoubleSolenoid collectorSolenoid;
    
    public CollectorComponent()
    {
        this.collectorSolenoid = new DoubleSolenoid(
            ElectronicsConstants.COLLECTOR_SOLENOID_MODULE_PORT,
            ElectronicsConstants.COLLECTOR_EXTENDER_SOLENOID_CHANNEL,
            ElectronicsConstants.COLLECTOR_RETRACTOR_SOLENOID_CHANNEL);

        this.collectorTalon = new Talon(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.COLLECTOR_MOTOR_CHANNEL);
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
