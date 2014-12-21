package irs1318_2014Fall_robot;

public interface IJoystick
{
    public boolean getCollectorExtendButton();

    public boolean getCollectorRetractButton();

    public boolean getCollectorCollectButton();

    public boolean getCollectorExpelButton();

    public double getDriveTrainXAxis();

    public double getDriveTrainYAxis();

    public boolean getDriveTrainSimpleModeButton();
}
