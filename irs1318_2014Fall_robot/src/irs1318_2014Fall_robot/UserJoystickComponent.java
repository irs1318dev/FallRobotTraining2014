package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class UserJoystickComponent implements IJoystickComponent
{
    private Joystick joystick;

    public UserJoystickComponent()
    {
        this.joystick = new Joystick(ButtonConstants.JOYSTICK_PORT);
    }

    public void checkToggles()
    {
        
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
        return 5;
    }

    public boolean getShooterShoot()
    {
        return this.joystick.getRawButton(ButtonConstants.SHOOTER_SHOOT_BUTTON);
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
