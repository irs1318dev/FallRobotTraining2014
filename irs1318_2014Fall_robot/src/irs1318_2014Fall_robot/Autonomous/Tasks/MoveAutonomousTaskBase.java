package irs1318_2014Fall_robot.Autonomous.Tasks;

import irs1318_2014Fall_robot.Autonomous.AutonomousConstants;
import irs1318_2014Fall_robot.Autonomous.AutonomousControlData;
import irs1318_2014Fall_robot.Autonomous.IAutonomousTask;
import irs1318_2014Fall_robot.DriveTrain.IDriveTrainComponent;

/**
 * Abstract class defining a task that moves the robot using the drive train.
 * 
 * @author Will
 *
 */
public abstract class MoveAutonomousTaskBase implements IAutonomousTask
{
    private final IDriveTrainComponent driveTrain;

    protected double startLeftEncoderDistance;
    protected double startRightEncoderDistance;

    protected double desiredFinalLeftEncoderDistance;
    protected double desiredFinalRightEncoderDistance;

    /**
     * Initializes a new MoveAutonomousTaskBase
     * @param driveTrain component to use to detect our current position
     */
    protected MoveAutonomousTaskBase(IDriveTrainComponent driveTrain)
    {
        this.driveTrain = driveTrain;
    }

    /**
     * Begin the current task
     */
    public void begin()
    {
        // get the start location
        this.startLeftEncoderDistance = this.driveTrain.getLeftEncoderDistance();
        this.startRightEncoderDistance = this.driveTrain.getRightEncoderDistance();

        // calculate the desired end location
        this.determineFinalEncoderDistance();
    }

    /**
     * Determine the final encoder distance
     */
    protected abstract void determineFinalEncoderDistance();

    /**
     * Run an iteration of the current task and apply any control changes 
     * @param data to which we should apply updated settings
     */
    public void update(AutonomousControlData data)
    {
        data.setDriveTrainLeftPosition(this.desiredFinalLeftEncoderDistance);
        data.setDriveTrainRightPosition(this.desiredFinalRightEncoderDistance);
        data.setDriveTrainPositionMode(true);
    }

    /**
     * Cancel the current task and clear control changes
     * @param data to which we should clear any updated control settings
     */
    public void cancel(AutonomousControlData data)
    {
        data.setDriveTrainLeftPosition(0.0);
        data.setDriveTrainRightPosition(0.0);
        data.setDriveTrainPositionMode(false);
    }

    /**
     * End the current task and reset control changes appropriately
     * @param data to which we should apply updated settings
     */
    public void end(AutonomousControlData data)
    {
        data.setDriveTrainLeftPosition(0.0);
        data.setDriveTrainRightPosition(0.0);
        data.setDriveTrainPositionMode(false);
    }

    /**
     * Checks whether we should continue processing this task or whether it should end
     * @return true if we should continue, otherwise false
     */
    public boolean shouldContinue()
    {
        double leftEncoderDistance = this.driveTrain.getLeftEncoderDistance();
        double rightEncoderDistance = this.driveTrain.getRightEncoderDistance();

        // check how far away we are from the desired end location
        double leftDelta = Math.abs(leftEncoderDistance - this.desiredFinalLeftEncoderDistance);
        double rightDelta = Math.abs(rightEncoderDistance - this.desiredFinalRightEncoderDistance);

        // return done if we are within an acceptable distance from the desired end location...
        return leftDelta < AutonomousConstants.DRIVETRAIN_POSITIONAL_ACCEPTABLE_DELTA &&
            rightDelta < AutonomousConstants.DRIVETRAIN_POSITIONAL_ACCEPTABLE_DELTA;
    }
}
