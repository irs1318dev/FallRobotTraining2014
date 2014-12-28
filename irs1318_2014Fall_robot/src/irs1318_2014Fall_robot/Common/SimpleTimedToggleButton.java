package irs1318_2014Fall_robot.Common;

import edu.wpi.first.wpilibj.Timer;

public class SimpleTimedToggleButton extends SimpleToggleButton implements ITimedToggle
{
    private final double toggleDuration;

    private Timer timer;
    private Double startTime;

    public SimpleTimedToggleButton(double toggleDuration)
    {
        this.toggleDuration = toggleDuration;
        this.timer = new Timer();
        this.timer.start();
        this.startTime = null;
    }

    public boolean canToggle()
    {
        return this.startTime == null;
    }

    public void tick()
    {
        if (this.startTime != null &&
            this.timer.get() > this.startTime + this.toggleDuration)
        {
            this.startTime = null;
        }
    }

    public void toggle()
    {
        super.toggle();
        this.startTime = this.timer.get();
    }
}
