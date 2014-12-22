package irs1318_2014Fall_robot;

public class CollectorController implements IController
{
    private static final double COLLECTOR_SPEED = 0.8;

    private IJoystick userInterface;
    private ICollectorComponent component;

    public CollectorController(IJoystick userInterface, ICollectorComponent component)
    {
        this.userInterface = userInterface;
        this.component = component;
    }

    public void run()
    {
        // get the various values from the user interface
        boolean extend = this.userInterface.getCollectorExtendButton();
        boolean retract = this.userInterface.getCollectorRetractButton();
        boolean collect = this.userInterface.getCollectorCollectButton();
        boolean expel = this.userInterface.getCollectorExpelButton();

        double collectorPower = 0.0;
        if (collect)
        {
            collectorPower = COLLECTOR_SPEED;
        }
        else if (expel)
        {
            collectorPower = -COLLECTOR_SPEED;
        }
        
        this.component.setCollector(extend, retract, collectorPower);
    }
}
