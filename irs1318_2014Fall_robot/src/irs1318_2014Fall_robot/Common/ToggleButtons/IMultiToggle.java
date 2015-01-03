package irs1318_2014Fall_robot.Common.ToggleButtons;

/**
 * Describes a toggle that cycles through multiple different states
 * 
 * @author Will
 *
 */
public interface IMultiToggle extends IToggle
{
    /**
     * Gets a value indicating the currently toggled state
     * @return current toggle state
     */
    public int getToggledState();
}
