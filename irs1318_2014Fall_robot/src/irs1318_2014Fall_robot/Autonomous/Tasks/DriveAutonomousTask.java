package irs1318_2014Fall_robot.Autonomous.Tasks;

import irs1318_2014Fall_robot.Autonomous.IAutonomousTask;
import irs1318_2014Fall_robot.DriveTrain.IDriveTrainComponent;

public class DriveAutonomousTask extends MoveAutonomousTaskBase implements IAutonomousTask
{
    private final double distance;

    /**
     * Initializes a new DriveAutonomousTask
     * @param distance from the current location to move (positive => move forward, negative => move backwards) in centimeters
     * @param driveTrain component to use to detect our current position
     */
    public DriveAutonomousTask(double distance, IDriveTrainComponent driveTrain)
    {
        super(driveTrain);

        this.distance = distance;
    }

    /**
     * Determine the final encoder distance
     */
    protected void determineFinalEncoderDistance()
    {
        this.desiredFinalLeftEncoderDistance = this.startLeftEncoderDistance + this.distance;
        this.desiredFinalRightEncoderDistance = this.startRightEncoderDistance + this.distance;        
    }
}
