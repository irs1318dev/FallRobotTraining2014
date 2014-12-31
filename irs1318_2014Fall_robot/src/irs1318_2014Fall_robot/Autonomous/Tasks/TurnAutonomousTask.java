package irs1318_2014Fall_robot.Autonomous.Tasks;

import irs1318_2014Fall_robot.HardwareConstants;
import irs1318_2014Fall_robot.Autonomous.IAutonomousTask;
import irs1318_2014Fall_robot.DriveTrain.IDriveTrainComponent;

public class TurnAutonomousTask extends MoveAutonomousTaskBase implements IAutonomousTask
{
    private final double degrees;

    /**
     * Initializes a new TurnAutonomousTask
     * @param degrees from the current orientation to rotate (positive => turn right, negative => turn left)
     * @param driveTrain component to use to detect our current position
     */
    public TurnAutonomousTask(double degrees, IDriveTrainComponent driveTrain)
    {
        super(driveTrain);

        this.degrees = degrees;
    }

    /**
     * Determine the final encoder distance
     */
    protected void determineFinalEncoderDistance()
    {
        double arcLength = Math.PI * HardwareConstants.DRIVETRAIN_WHEEL_SEPARATION_DISTANCE * (this.degrees / 360.0);
        this.desiredFinalLeftEncoderDistance = this.startLeftEncoderDistance + arcLength;
        this.desiredFinalRightEncoderDistance = this.startRightEncoderDistance - arcLength;        
    }
}
