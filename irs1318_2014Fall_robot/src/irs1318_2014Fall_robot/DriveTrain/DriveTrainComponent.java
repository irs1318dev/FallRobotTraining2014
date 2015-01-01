package irs1318_2014Fall_robot.DriveTrain;

import irs1318_2014Fall_robot.ElectronicsConstants;
import irs1318_2014Fall_robot.HardwareConstants;
import irs1318_2014Fall_robot.Common.SmartDashboardLogger;
import edu.wpi.first.wpilibj.*;

public class DriveTrainComponent implements IDriveTrainComponent
{
    public static final String LEFT_TALON_POWER_LOG_KEY = "dt.re";
    public static final String RIGHT_TALON_POWER_LOG_KEY = "dt.le";
    public static final String LEFT_ENCODER_VELOCITY_LOG_KEY = "dt.lev";
    public static final String RIGHT_ENCODER_VELOCITY_LOG_KEY = "dt.rev";
    public static final String LEFT_ENCODER_DISTANCE_LOG_KEY = "dt.led";
    public static final String RIGHT_ENCODER_DISTANCE_LOG_KEY = "dt.red";

    private Talon leftTalon;
    private Talon rightTalon;

    private Encoder leftEncoder;
    private Encoder rightEncoder;

    /**
     * Initializes a new DriveTrainComponent
     */
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

        this.leftEncoder.setDistancePerPulse(HardwareConstants.DRIVETRAIN_LEFT_PULSE_DISTANCE);
        this.rightEncoder.setDistancePerPulse(HardwareConstants.DRIVETRAIN_RIGHT_PULSE_DISTANCE);
    }

    /**
     * set the power levels to the drive train
     * @param leftPower level to apply to the left motor
     * @param rightPower level to apply to the right motor
     */
    public void setDriveTrainPower(double leftPower, double rightPower)
    {
        this.leftTalon.set(-leftPower); // note: left motors are oriented facing "backwards"
        this.rightTalon.set(rightPower);

        SmartDashboardLogger.putNumber(DriveTrainComponent.LEFT_TALON_POWER_LOG_KEY, leftPower);
        SmartDashboardLogger.putNumber(DriveTrainComponent.RIGHT_TALON_POWER_LOG_KEY, rightPower);
    }

    /**
     * get the velocity from the left encoder
     * @return a value indicating the velocity
     */
    public double getLeftEncoderVelocity()
    {
        double leftRate = this.leftEncoder.getRate();

        SmartDashboardLogger.putNumber(DriveTrainComponent.LEFT_ENCODER_VELOCITY_LOG_KEY, leftRate);

        return leftRate;
    }

    /**
     * get the velocity from the right encoder
     * @return a value indicating the velocity
     */
    public double getRightEncoderVelocity()
    {
        double rightRate = this.rightEncoder.getRate();

        SmartDashboardLogger.putNumber(DriveTrainComponent.RIGHT_ENCODER_VELOCITY_LOG_KEY, rightRate);

        return rightRate;
    }

    /**
     * get the distance from the left encoder
     * @return a value indicating the distance
     */
    public double getLeftEncoderDistance()
    {
        double leftDistance = this.leftEncoder.getDistance(); 

        SmartDashboardLogger.putNumber(DriveTrainComponent.LEFT_ENCODER_DISTANCE_LOG_KEY, leftDistance);

        return leftDistance;
    }

    /**
     * get the distance from the right encoder
     * @return a value indicating the distance
     */
    public double getRightEncoderDistance()
    {
        double rightDistance = this.rightEncoder.getDistance(); 

        SmartDashboardLogger.putNumber(DriveTrainComponent.RIGHT_ENCODER_DISTANCE_LOG_KEY, rightDistance);

        return rightDistance;
    }
}
