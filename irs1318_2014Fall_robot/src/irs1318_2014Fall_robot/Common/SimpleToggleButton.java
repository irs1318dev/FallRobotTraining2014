package irs1318_2014Fall_robot.Common;

public class SimpleToggleButton implements ISimpleToggle
{
    private boolean currentState;

    public SimpleToggleButton()
    {
        this.currentState = false;
    }

    public void toggle()
    {
        this.currentState = !this.currentState;
    }

    public boolean isToggled()
    {
        return this.currentState;
    }
}
