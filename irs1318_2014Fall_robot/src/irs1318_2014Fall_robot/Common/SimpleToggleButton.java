package irs1318_2014Fall_robot.Common;

public class SimpleToggleButton implements ISimpleToggle
{
    private boolean currentState;

    /**
     * Initializes a new SimpleToggleButton
     */
    public SimpleToggleButton()
    {
        this.currentState = false;
    }

    /**
     * Attempt to change the current state
     */
    public void toggle()
    {
        this.currentState = !this.currentState;
    }

    /**
     * Gets a value indicating whether this is currently toggled
     * @return true if toggled, otherwise false
     */
    public boolean isToggled()
    {
        return this.currentState;
    }
}
