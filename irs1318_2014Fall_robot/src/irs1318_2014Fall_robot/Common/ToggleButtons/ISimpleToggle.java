package irs1318_2014Fall_robot.Common.ToggleButtons;

/**
 * Describes a toggle that switches between true and false.
 * 
 * @author Will
 *
 */
public interface ISimpleToggle extends IToggle
{
    /**
     * Gets a value indicating whether this is currently toggled
     * @return true if toggled, otherwise false
     */
    public boolean isToggled();
}
