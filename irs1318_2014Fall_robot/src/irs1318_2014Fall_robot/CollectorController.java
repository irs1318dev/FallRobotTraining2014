package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class CollectorController implements IActiveComponent
{
    private static final double COLLECTOR_SPEED = 0.8;

    private IJoystick userInterface;
    private Talon collectorTalon;
    private DoubleSolenoid collectorSolenoid;

    public CollectorController(IJoystick userInterface)
    {
        this.userInterface = userInterface;

        this.collectorSolenoid = new DoubleSolenoid(
            ElectronicsConstants.COLLECTOR_SOLENOID_MODULE_PORT,
            ElectronicsConstants.COLLECTOR_EXTENDER_SOLENOID_CHANNEL,
            ElectronicsConstants.COLLECTOR_RETRACTOR_SOLENOID_CHANNEL);

        this.collectorTalon = new Talon(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.COLLECTOR_MOTOR_CHANNEL);
    }

    public void run()
    {
        // get the various values from the user interface
        boolean extend = this.userInterface.getCollectorExtendButton();
        boolean retract = this.userInterface.getCollectorRetractButton();
        boolean collect = this.userInterface.getCollectorCollectButton();
        boolean expel = this.userInterface.getCollectorExpelButton();

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

        if (collect)
        {
            this.collectorTalon.set(COLLECTOR_SPEED);
        }
        else if (expel)
        {
            this.collectorTalon.set(-COLLECTOR_SPEED);
        }
    }
}
