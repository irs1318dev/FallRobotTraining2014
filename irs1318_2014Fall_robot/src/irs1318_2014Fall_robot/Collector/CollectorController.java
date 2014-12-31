package irs1318_2014Fall_robot.Collector;

import irs1318_2014Fall_robot.TuningConstants;
import irs1318_2014Fall_robot.Common.IController;
import irs1318_2014Fall_robot.Common.IOperatorComponent;

public class CollectorController implements IController
{
    private IOperatorComponent operatorInterface;
    private ICollectorComponent component;

    /**
     * Initializes a new CollectorController
     * @param operatorInterface to control the collector
     * @param component to control
     */
    public CollectorController(IOperatorComponent operatorInterface, ICollectorComponent component)
    {
        this.operatorInterface = operatorInterface;
        this.component = component;
    }

    /**
     * calculate the various outputs to use based on the inputs and apply them to the outputs for the relevant component
     */
    public void run()
    {
        // get the various values from the user interface
        boolean extend = this.operatorInterface.getCollectorExtendButton();
        boolean retract = this.operatorInterface.getCollectorRetractButton();
        boolean collect = this.operatorInterface.getCollectorCollectButton();
        boolean expel = this.operatorInterface.getCollectorExpelButton();

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
    }

    /**
     * stop the relevant component
     */
    public void stop()
    {
        this.component.setCollector(false, false, 0.0);
    }
}
