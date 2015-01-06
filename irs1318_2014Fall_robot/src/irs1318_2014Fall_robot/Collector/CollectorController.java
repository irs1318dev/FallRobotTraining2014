package irs1318_2014Fall_robot.Collector;

import irs1318_2014Fall_robot.TuningConstants;
import irs1318_2014Fall_robot.Common.IController;
import irs1318_2014Fall_robot.Common.IDriver;

/**
 * Collector controller.
 * The controller defines the logic that controls a mechanism given inputs (component) and operator-requested actions, and 
 * translates those into the abstract functions that should be applied to the outputs (component).
 * 
 * @author Will
 *
 */
public class CollectorController implements IController
{
    private IDriver driver;
    private ICollectorComponent component;

    /**
     * Initializes a new CollectorController
     * @param operator to control the collector
     * @param component to control
     */
    public CollectorController(IDriver operator, ICollectorComponent component)
    {
        this.driver = operator;
        this.component = component;
    }

    /**
     * calculate the various outputs to use based on the inputs and apply them to the outputs for the relevant component
     */
    public void update()
    {
        // get the various values from the user interface
        boolean extend = this.driver.getCollectorExtendButton();
        boolean retract = this.driver.getCollectorRetractButton();
        boolean collect = this.driver.getCollectorCollectButton();
        boolean expel = this.driver.getCollectorExpelButton();

        double collectorPower = 0.0;
        if (collect)
        {
            collectorPower = TuningConstants.COLLECTOR_SPEED;
        }
        else if (expel)
        {
            collectorPower = -TuningConstants.COLLECTOR_SPEED;
        }

        this.component.setCollector(extend, retract, collectorPower);

        // this is never called except for logging...  so, call it here:
        this.component.readLimitSwitch();
    }

    /**
     * stop the relevant component
     */
    public void stop()
    {
        this.component.setCollector(false, false, 0.0);
    }
}
