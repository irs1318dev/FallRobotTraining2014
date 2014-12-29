package irs1318_2014Fall_robot.Autonomous;

public interface IAutonomousTask
{
    public void begin();
    public void run(AutonomousControlData data);
    public void cancel(AutonomousControlData data);
    public void end(AutonomousControlData data);
    public boolean shouldContinue();
}
