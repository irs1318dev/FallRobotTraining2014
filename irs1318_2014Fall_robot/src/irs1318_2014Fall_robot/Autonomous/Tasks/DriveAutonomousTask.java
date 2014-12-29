package irs1318_2014Fall_robot.Autonomous.Tasks;

import irs1318_2014Fall_robot.Autonomous.IAutonomousTask;

public class DriveAutonomousTask implements IAutonomousTask
{
    private final double distance;

    public DriveAutonomousTask(double distance)
    {
        this.distance = distance;
    }

    public void start()
    {

    }

    public void run()
    {
    }

    public void stop()
    {
    }

    public boolean shouldContinue()
    {
        // TODO Auto-generated method stub
        return false;
    }
}
