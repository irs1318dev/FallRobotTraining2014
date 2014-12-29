package irs1318_2014Fall_robot.UserInterface;

import irs1318_2014Fall_robot.JoystickButtonConstants;
import irs1318_2014Fall_robot.TuningConstants;
import irs1318_2014Fall_robot.Common.IOperatorComponent;
import irs1318_2014Fall_robot.Common.MultiToggleButton;
import irs1318_2014Fall_robot.Common.SimpleTimedToggleButton;
import edu.wpi.first.wpilibj.*;

public class UserOperator implements IOperatorComponent
{
    private Joystick joystick;
    private MultiToggleButton shooterMode;
    private SimpleTimedToggleButton shootButton;

    public UserOperator()
    {
        this.joystick = new Joystick(JoystickButtonConstants.JOYSTICK_PORT);
        this.shooterMode = new MultiToggleButton(new int[] { 3, 4, 5 });
        this.shootButton = new SimpleTimedToggleButton(TuningConstants.SHOOTER_TOGGLE_DURATION);
    }

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
    
    public void stop()
    {
        this.shootButton.cancel();
    }

    public boolean getCollectorExtendButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.COLLECTOR_EXTEND_BUTTON);
    }

    public boolean getCollectorRetractButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.COLLECTOR_RETRACT_BUTTON);
    }

    public boolean getCollectorCollectButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.COLLECTOR_COLLECT_BUTTON);
    }

    public boolean getCollectorExpelButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.COLLECTOR_EXPEL_BUTTON);
    }

    public boolean getShooterAngle()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.SHOOTER_EXTEND_BUTTON);
    }

    public int getShooterMode()
    {
        return this.shooterMode.getToggledState();
    }

    public boolean getShooterShoot()
    {
        return this.shootButton.isToggled();
    }

    public double getDriveTrainXVelocity()
    {
        return this.joystick.getX();
    }

    public double getDriveTrainYVelocity()
    {
        return this.joystick.getY();
    }

    public boolean getDriveTrainSimpleModeButton()
    {
        return this.joystick.getRawButton(JoystickButtonConstants.DRIVETRAIN_SIMPLE_BUTTON);
    }

    public double getDriveTrainLeftPosition()
    {
        // position mode is only used for autonomous
        return 0.0;
    }

    public double getDriveTrainRightPosition()
    {
        // position mode is only used for autonomous
        return 0.0;
    }

    public boolean getDriveTrainPositionMode()
    {
        // position mode is only used for autonomous
        return false;
    }
}
