package irs1318_2014Fall_robot.Autonomous.Tasks;

import irs1318_2014Fall_robot.Autonomous.AutonomousConstants;
import irs1318_2014Fall_robot.Autonomous.AutonomousControlData;
import irs1318_2014Fall_robot.Autonomous.IAutonomousTask;
import irs1318_2014Fall_robot.DriveTrain.IDriveTrainComponent;

public class DriveAutonomousTask implements IAutonomousTask
{
    private final double distance;
    private final IDriveTrainComponent driveTrain;

    private double startLeftEncoderDistance;
    private double startRightEncoderDistance;

    public DriveAutonomousTask(double distance, IDriveTrainComponent driveTrain)
    {
        this.distance = distance;
        this.driveTrain = driveTrain;
    }

    public void begin()
    {
        this.startLeftEncoderDistance = this.driveTrain.getLeftEncoderDistance();
        this.startRightEncoderDistance = this.driveTrain.getRightEncoderDistance();
    }

    public void run(AutonomousControlData data)
    {
        data.setDriveTrainLeftPosition(this.startLeftEncoderDistance + this.distance);
        data.setDriveTrainRightPosition(this.startRightEncoderDistance + this.distance);
        data.setDriveTrainPositionMode(true);
    }

    public void cancel(AutonomousControlData data)
    {
        data.setDriveTrainLeftPosition(0.0);
        data.setDriveTrainRightPosition(0.0);
        data.setDriveTrainPositionMode(false);
    }

    public void end(AutonomousControlData data)
    {
        data.setDriveTrainLeftPosition(0.0);
        data.setDriveTrainRightPosition(0.0);
        data.setDriveTrainPositionMode(false);
    }

    public boolean shouldContinue()
    {
        double leftEncoderDistance = this.driveTrain.getLeftEncoderDistance();
        double rightEncoderDistance = this.driveTrain.getRightEncoderDistance();

        double leftDelta = Math.abs(leftEncoderDistance - this.startLeftEncoderDistance - this.distance);
        double rightDelta = Math.abs(rightEncoderDistance - this.startRightEncoderDistance - this.distance);

        return leftDelta < AutonomousConstants.DRIVETRAIN_POSITIONAL_ACCEPTABLE_DELTA &&
            rightDelta < AutonomousConstants.DRIVETRAIN_POSITIONAL_ACCEPTABLE_DELTA;
    }
}
