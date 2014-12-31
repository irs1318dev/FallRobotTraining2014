package irs1318_2014Fall_robot.Common.ToggleButtons;

public interface ITimedToggle
{
    /**
     * Gets a value indicating whether we can toggle
     * @return true if we can toggle, otherwise false
     */
    public boolean canToggle();

    /**
     * Indicates that some time has passed
     */
    public void tick();
}
