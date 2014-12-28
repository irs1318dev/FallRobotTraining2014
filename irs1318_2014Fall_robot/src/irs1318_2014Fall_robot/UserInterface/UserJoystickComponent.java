package irs1318_2014Fall_robot.UserInterface;

import irs1318_2014Fall_robot.ButtonConstants;
import irs1318_2014Fall_robot.TuningConstants;
import irs1318_2014Fall_robot.Common.MultiToggleButton;
import irs1318_2014Fall_robot.Common.SimpleTimedToggleButton;
import edu.wpi.first.wpilibj.*;

public class UserJoystickComponent implements IJoystickComponent
{
    private Joystick joystick;
    private MultiToggleButton shooterMode;
    private SimpleTimedToggleButton shootButton;

    public UserJoystickComponent()
    {
        this.joystick = new Joystick(ButtonConstants.JOYSTICK_PORT);
        this.shooterMode = new MultiToggleButton(new int[] { 3, 4, 5 });
        this.shootButton = new SimpleTimedToggleButton(TuningConstants.SHOOTER_TOGGLE_DURATION);
    }

    public void checkToggles()
    {
        if (this.joystick.getRawButton(ButtonConstants.SHOOTER_MODE_TOGGLE_BUTTON))
        {
            this.shooterMode.toggle();
        }

        this.shootButton.tick();
        if (this.shootButton.canToggle() && this.joystick.getRawButton(ButtonConstants.SHOOTER_SHOOT_BUTTON))
        {
            this.shootButton.toggle();
        }
    }

    public boolean getCollectorExtendButton()
    {
        return this.joystick.getRawButton(ButtonConstants.COLLECTOR_EXTEND_BUTTON);
    }

    public boolean getCollectorRetractButton()
    {
        return this.joystick.getRawButton(ButtonConstants.COLLECTOR_RETRACT_BUTTON);
    }

    public boolean getCollectorCollectButton()
    {
        return this.joystick.getRawButton(ButtonConstants.COLLECTOR_COLLECT_BUTTON);
    }

    public boolean getCollectorExpelButton()
    {
        return this.joystick.getRawButton(ButtonConstants.COLLECTOR_EXPEL_BUTTON);
    }

    public boolean getShooterAngle()
    {
        return this.joystick.getRawButton(ButtonConstants.SHOOTER_EXTEND_BUTTON);
    }

    public int getShooterMode()
    {
        return this.shooterMode.getToggledState();
    }

    public boolean getShooterShoot()
    {
        return this.shootButton.isToggled();
    }

    public double getDriveTrainXAxis()
    {
        return this.joystick.getX();
    }

    public double getDriveTrainYAxis()
    {
        return this.joystick.getY();
    }

    public boolean getDriveTrainSimpleModeButton()
    {
        return this.joystick.getRawButton(ButtonConstants.DRIVETRAIN_SIMPLE_BUTTON);
    }
}
