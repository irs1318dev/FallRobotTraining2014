package irs1318_2014Fall_robot;

public class CachedJoystick implements IJoystick
{
    public boolean getCollectorExtendButton()
    {
        return false;
    }

    public boolean getCollectorRetractButton()
    {
        return false;
    }

    public boolean getCollectorCollectButton()
    {
        return false;
    }

    public boolean getCollectorExpelButton()
    {
        return false;
    }

    public double getDriveTrainXAxis()
    {
        return 0;
    }

    public double getDriveTrainYAxis()
    {
        return 0;
    }

    public boolean getDriveTrainSimpleModeButton()
    {
        return false;
    }
}
