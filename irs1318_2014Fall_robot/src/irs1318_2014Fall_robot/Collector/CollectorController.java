package irs1318_2014Fall_robot.Collector;

import irs1318_2014Fall_robot.TuningConstants;
import irs1318_2014Fall_robot.Common.IController;
import irs1318_2014Fall_robot.Common.IOperator;

public class CollectorController implements IController
{
    private IOperator operator;
    private ICollectorComponent component;

    /**
     * Initializes a new CollectorController
     * @param operator to control the collector
     * @param component to control
     */
    public CollectorController(IOperator operator, ICollectorComponent component)
    {
        this.operator = operator;
        this.component = component;
    }

    /**
     * calculate the various outputs to use based on the inputs and apply them to the outputs for the relevant component
     */
    public void run()
    {
        // get the various values from the user interface
        boolean extend = this.operator.getCollectorExtendButton();
        boolean retract = this.operator.getCollectorRetractButton();
        boolean collect = this.operator.getCollectorCollectButton();
        boolean expel = this.operator.getCollectorExpelButton();

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
