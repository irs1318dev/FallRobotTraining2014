package irs1318_2014Fall_robot.Common;

public interface IOperatorComponent
{
    public void tick();

    public void stop();

    public boolean getCollectorExtendButton();

    public boolean getCollectorRetractButton();

    public boolean getCollectorCollectButton();

    public boolean getCollectorExpelButton();

    public boolean getShooterAngle();

    public int getShooterMode();

    public boolean getShooterShoot();

    public double getDriveTrainXVelocity();

    public double getDriveTrainYVelocity();

    public boolean getDriveTrainSimpleModeButton();

    public double getDriveTrainLeftPosition();

    public double getDriveTrainRightPosition();

    public boolean getDriveTrainPositionMode();
}
