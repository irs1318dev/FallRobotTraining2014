package irs1318_2014Fall_robot.Autonomous;

import edu.wpi.first.wpilibj.Timer;

public abstract class TimedAutonomousTask implements IAutonomousTask
{
    private final double duration;

    private Timer timer;
    private Double startTime;

    protected TimedAutonomousTask(double duration)
    {
        this.duration = duration;
        this.timer = new Timer();

        this.startTime = null;
    }

    public void begin()
    {
        this.startTime = this.timer.get();
    }

    public abstract void run(AutonomousControlData data);

    public void cancel(AutonomousControlData data)
    {
        this.startTime = null;
    }

    public void end(AutonomousControlData data)
    {
    }

    public boolean shouldContinue()
    {
        return timer.get() > this.startTime + this.duration;
    }
}
