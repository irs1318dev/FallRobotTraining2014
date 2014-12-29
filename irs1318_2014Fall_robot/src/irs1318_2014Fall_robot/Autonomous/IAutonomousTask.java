package irs1318_2014Fall_robot.Autonomous;

public interface IAutonomousTask
{
    public void start();
    public void run();
    public void stop();
    public boolean shouldContinue();
}
