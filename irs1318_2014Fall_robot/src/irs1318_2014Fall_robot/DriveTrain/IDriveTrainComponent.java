package irs1318_2014Fall_robot.DriveTrain;

public interface IDriveTrainComponent
{
    public void setDriveTrainPower(double leftPower, double rightPower);
    public double getLeftEncoderVelocity();
    public double getRightEncoderVelocity();
    public double getLeftEncoderDistance();
    public double getRightEncoderDistance();
}
