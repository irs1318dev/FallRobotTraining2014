package irs1318_2014Fall_robot.Autonomous.Tasks;

import irs1318_2014Fall_robot.Autonomous.AutonomousControlData;
import irs1318_2014Fall_robot.Autonomous.IAutonomousTask;
import irs1318_2014Fall_robot.Autonomous.TimedAutonomousTask;

public class WaitAutonomousTask extends TimedAutonomousTask implements IAutonomousTask
{
    public WaitAutonomousTask(double duration)
    {
        super(duration);
    }

    public void run(AutonomousControlData data)
    {
        // no-op
    }
}
