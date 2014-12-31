package irs1318_2014Fall_robot.Common;

import edu.wpi.first.wpilibj.Timer;

public class SimpleTimedToggleButton extends SimpleToggleButton implements ITimedToggle
{
    private final double toggleDuration;

    private Timer timer;
    private Double startTime;

    /**
     * Initializes a new SimpleTimedToggleButton
     * @param toggleDuration time duration for the toggle
     */
    public SimpleTimedToggleButton(double toggleDuration)
    {
        this.toggleDuration = toggleDuration;
        this.timer = new Timer();
        this.timer.start();
        this.startTime = null;
    }

    /**
     * Gets a value indicating whether we can toggle
     * @return true if we can toggle, otherwise false
     */
    public boolean canToggle()
    {
        return this.startTime == null;
    }

    /**
     * Indicates that some time has passed
     */
    public void tick()
    {
        if (this.startTime != null &&
            this.timer.get() > this.startTime + this.toggleDuration)
        {
            this.startTime = null;
        }
    }

    /**
     * Attempt to change the current state
     */
    public void toggle()
    {
        super.toggle();
        this.startTime = this.timer.get();
    }

    /**
     * Cancel the toggle timing
     */
    public void cancel()
    {
        this.startTime = null;
    }
}
