package irs1318_2014Fall_robot.Autonomous.Tasks;

import irs1318_2014Fall_robot.Autonomous.AutonomousControlData;
import irs1318_2014Fall_robot.Autonomous.IAutonomousTask;
import irs1318_2014Fall_robot.Autonomous.TimedAutonomousTask;

public class WaitAutonomousTask extends TimedAutonomousTask implements IAutonomousTask
{
    /**
     * Initializes a new WaitAutonomousTask
     * @param duration to wait
     */
    public WaitAutonomousTask(double duration)
    {
        super(duration);
    }

    /**
     * Run an iteration of the current task and apply any control changes 
     * @param data to which we should apply updated settings
     */
    public void run(AutonomousControlData data)
    {
        // no-op
    }
}
