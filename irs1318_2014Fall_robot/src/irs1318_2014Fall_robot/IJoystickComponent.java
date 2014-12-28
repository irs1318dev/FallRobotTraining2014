package irs1318_2014Fall_robot;

public interface IJoystickComponent
{
    public void checkToggles();

    public boolean getCollectorExtendButton();

    public boolean getCollectorRetractButton();

    public boolean getCollectorCollectButton();

    public boolean getCollectorExpelButton();

    public boolean getShooterAngle();

    public int getShooterMode();

    public boolean getShooterShoot();

    public double getDriveTrainXAxis();

    public double getDriveTrainYAxis();

    public boolean getDriveTrainSimpleModeButton();
}
