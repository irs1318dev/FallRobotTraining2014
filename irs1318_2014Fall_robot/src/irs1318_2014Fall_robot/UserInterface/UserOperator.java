package irs1318_2014Fall_robot.UserInterface;

import irs1318_2014Fall_robot.JoystickButtonConstants;
import irs1318_2014Fall_robot.TuningConstants;
import irs1318_2014Fall_robot.Common.IOperatorComponent;
import irs1318_2014Fall_robot.Common.ToggleButtons.MultiToggleButton;
import irs1318_2014Fall_robot.Common.ToggleButtons.SimpleTimedToggleButton;
import edu.wpi.first.wpilibj.*;

public class UserOperator implements IOperatorComponent
{
    private Joystick joystick;
    private MultiToggleButton shooterMode;
    private SimpleTimedToggleButton shootButton;

    /**
     * Initializes a new UserOperator
     */
    public UserOperator()
    {
        this.joystick = new Joystick(JoystickButtonConstants.JOYSTICK_PORT);
        this.shooterMode = new MultiToggleButton(new int[] { 3, 4, 5 });
        this.shootButton = new SimpleTimedToggleButton(TuningConstants.SHOOTER_TOGGLE_DURATION);
    }

    /**
     * Tell the operator component that some time has passed
     */
    public void tick()
    {
        // check the toggles
        if (this.joystick.getRawButton(JoystickButtonConstants.SHOOTER_MODE_TOGGLE_BUTTON))
        {
            this.shooterMode.toggle();
        }

        this.shootButton.tick();
        if (this.shootButton.canToggle() && this.joystick.getRawButton(JoystickButtonConstants.SHOOTER_SHOOT_BUTTON))
        {
            this.shootButton.toggle();
        }
    }
    
    /**
     * Tell the operator component that operation is stopping
     */
    public void stop()
    {
        this.shootButton.cancel();
    }

    /**
     * Get a value indicating whether we should extend the collector 
     * @return true if we should extend, otherwise false
     */
    public boolean getCollectorExtendButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.COLLECTOR_EXTEND_BUTTON);
    }

    /**
     * Get a value indicating whether we should retract the collector 
     * @return true if we should retract, otherwise false
     */
    public boolean getCollectorRetractButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.COLLECTOR_RETRACT_BUTTON);
    }

    /**
     * Get a value indicating whether we should collect a ball using the collector 
     * @return true if we should collect, otherwise false
     */
    public boolean getCollectorCollectButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.COLLECTOR_COLLECT_BUTTON);
    }

    /**
     * Get a value indicating whether we should expel a ball using the collector 
     * @return true if we should expel, otherwise false
     */
    public boolean getCollectorExpelButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.COLLECTOR_EXPEL_BUTTON);
    }

    /**
     * Get a value indicating whether we should adjust the shooter angle 
     * @return true if we should move in, otherwise false
     */
    public boolean getShooterAngle()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.SHOOTER_EXTEND_BUTTON);
    }

    /**
     * Get a value indicating the shooter's current mode 
     * @return a value indicating the number of pistons to use in the shot
     */
    public int getShooterMode()
    {
        return this.shooterMode.getToggledState();
    }

    /**
     * Get a value indicating whether we should attempt to shoot
     * @return true if we should be shooting, otherwise false
     */
    public boolean getShooterShoot()
    {
        return this.shootButton.isToggled();
    }

    /**
     * Get a value indicating the desired drive train X Velocity 
     * @return value between -1.0 and 1.0 (percentage of max right turn velocity)
     */
    public double getDriveTrainXVelocity()
    {
        return this.joystick.getX();
    }

    /**
     * Get a value indicating the desired drive train Y velocity (turn amount) 
     * @return value between -1.0 and 1.0 (percentage of max forward velocity)
     */
    public double getDriveTrainYVelocity()
    {
        return this.joystick.getY();
    }

    /**
     * Get a value indicating whether we should be using the drive train in simple mode 
     * @return true if we should be in simple mode, otherwise false
     */
    public boolean getDriveTrainSimpleModeButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.DRIVETRAIN_SIMPLE_BUTTON);
    }

    /**
     * Get a value indicating the desired drive train left position for positional mode
     * @return position
     */
    public double getDriveTrainLeftPosition()
    {
        // position mode is only used for autonomous
        return 0.0;
    }

    /**
     * Get a value indicating the desired drive train right position for positional mode
     * @return position
     */
    public double getDriveTrainRightPosition()
    {
        // position mode is only used for autonomous
        return 0.0;
    }

    /**
     * Get a value indicating whether the drive train should be in position or velocity mode
     * @return true if position mode, false if velocity mode
     */
    public boolean getDriveTrainPositionMode()
    {
        // position mode is only used for autonomous
        return false;
    }
}
