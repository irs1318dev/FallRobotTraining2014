package irs1318_2014Fall_robot.DriveTrain;

import irs1318_2014Fall_robot.ElectronicsConstants;
import edu.wpi.first.wpilibj.*;

public class DriveTrainComponent implements IDriveTrainComponent
{
    private Talon leftTalon;
    private Talon rightTalon;

    private Encoder leftEncoder;
    private Encoder rightEncoder;

    public DriveTrainComponent()
    {
        this.leftTalon = new Talon(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.DRIVETRAIN_LEFT_TALON_CHANNEL);

        this.rightTalon = new Talon(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.DRIVETRAIN_RIGHT_TALON_CHANNEL);

        this.leftEncoder = new Encoder(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.DRIVETRAIN_LEFT_ENCODER_CHANNEL_A,
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.DRIVETRAIN_LEFT_ENCODER_CHANNEL_B);

        this.rightEncoder = new Encoder(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A,
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B);

        this.leftEncoder.setDistancePerPulse(ElectronicsConstants.DRIVETRAIN_LEFT_PULSE_DISTANCE);
        this.rightEncoder.setDistancePerPulse(ElectronicsConstants.DRIVETRAIN_RIGHT_PULSE_DISTANCE);
    }

    public void setDriveTrainPower(double leftPower, double rightPower)
    {
        this.leftTalon.set(-leftPower); // note: left motors are oriented facing "backwards"
        this.rightTalon.set(rightPower);
    }

    public double getLeftEncoderVelocity()
    {
        return this.leftEncoder.getRate();
    }

    public double getRightEncoderVelocity()
    {
        return this.rightEncoder.getRate();
    }

    public double getLeftEncoderDistance()
    {
        return this.leftEncoder.getDistance();
    }

    public double getRightEncoderDistance()
    {
        return this.leftEncoder.getDistance();
    }
}
