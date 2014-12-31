package irs1318_2014Fall_robot.DriveTrain;

import irs1318_2014Fall_robot.TuningConstants;
import irs1318_2014Fall_robot.Common.IController;
import irs1318_2014Fall_robot.Common.IOperatorComponent;
import irs1318_2014Fall_robot.Common.PIDHandler;

public class DriveTrainController implements IController
{
    private static final double POWERLEVEL_MIN = -1.0;
    private static final double POWERLEVEL_MAX = 1.0;

    private IOperatorComponent operatorInterface;
    private IDriveTrainComponent component;

    private boolean usePID;
    private boolean usePositionalMode;
    private PIDHandler leftPID;
    private PIDHandler rightPID;

    /**
     * Initializes a new DriveTrainController
     * @param operatorInterface to use to control the drive train
     * @param component to control
     * @param usePID indicates whether we should use PID control
     */
    public DriveTrainController(IOperatorComponent operatorInterface, IDriveTrainComponent component, boolean usePID)
    {
        this.operatorInterface = operatorInterface;
        this.component = component;
        this.usePID = usePID;
        this.usePositionalMode = false;

        this.createPIDHandler();
    }

    /**
     * calculate the various outputs to use based on the inputs and apply them to the outputs for the relevant component
     */
    public void run()
    {
        // check our desired PID mode
        boolean newUsePositionalMode = this.operatorInterface.getDriveTrainPositionMode();
        if (newUsePositionalMode != this.usePositionalMode)
        {
            this.usePositionalMode = newUsePositionalMode;

            // re-create PID handler
            this.createPIDHandler();
        }

        PowerSetting powerSetting;
        if (!this.usePositionalMode)
        {
            powerSetting = this.calculateVelocityModePowerSetting();
        }
        else
        {
            powerSetting = this.calculatePositionModePowerSetting();
        }

        double leftPower = powerSetting.getLeftPower();
        double rightPower = powerSetting.getRightPower();

        // ensure that our algorithms are correct and don't give values outside
        // the appropriate range
        this.assertPowerLevelRange(leftPower, "left");
        this.assertPowerLevelRange(rightPower, "right");

        // apply the power to the motors
        this.component.setDriveTrainPower(leftPower, rightPower);
    }

    /**
     * stop the relevant component
     */
    public void stop()
    {
        this.component.setDriveTrainPower(0.0, 0.0);
    }

    /**
     * create a PIDHandler based on our current settings
     */
    private void createPIDHandler()
    {
        if (!usePID)
        {
            this.leftPID = null;
            this.rightPID = null;
        }
        else
        {
            if (this.usePositionalMode)
            {
                this.leftPID = new PIDHandler(
                    TuningConstants.DRIVETRAIN_POSITION_PID_LEFT_KP,
                    TuningConstants.DRIVETRAIN_POSITION_PID_LEFT_KI,
                    TuningConstants.DRIVETRAIN_POSITION_PID_LEFT_KD,
                    TuningConstants.DRIVETRAIN_POSITION_PID_LEFT_KF);

                this.rightPID = new PIDHandler(
                    TuningConstants.DRIVETRAIN_POSITION_PID_RIGHT_KP,
                    TuningConstants.DRIVETRAIN_POSITION_PID_RIGHT_KI,
                    TuningConstants.DRIVETRAIN_POSITION_PID_RIGHT_KD,
                    TuningConstants.DRIVETRAIN_POSITION_PID_RIGHT_KF);
            }
            else
            {
                this.leftPID = new PIDHandler(
                    TuningConstants.DRIVETRAIN_VELOCITY_PID_LEFT_KP,
                    TuningConstants.DRIVETRAIN_VELOCITY_PID_LEFT_KI,
                    TuningConstants.DRIVETRAIN_VELOCITY_PID_LEFT_KD,
                    TuningConstants.DRIVETRAIN_VELOCITY_PID_LEFT_KF);

                this.rightPID = new PIDHandler(
                    TuningConstants.DRIVETRAIN_VELOCITY_PID_RIGHT_KP,
                    TuningConstants.DRIVETRAIN_VELOCITY_PID_RIGHT_KI,
                    TuningConstants.DRIVETRAIN_VELOCITY_PID_RIGHT_KD,
                    TuningConstants.DRIVETRAIN_VELOCITY_PID_RIGHT_KF);
            }
        }
    }

    /**
     * Calculate the power setting to use based on the inputs when in velocity mode
     * @return power settings for left and right motor
     */
    private PowerSetting calculateVelocityModePowerSetting()
    {
        double leftVelocityGoal = 0.0;
        double rightVelocityGoal = 0.0;

        // get a value indicating that we should be in simple mode...
        boolean simpleDriveModeEnabled = this.operatorInterface.getDriveTrainSimpleModeButton();

        // get the X and Y values from the user interface
        double xVelocity = this.operatorInterface.getDriveTrainXVelocity();
        double yVelocity = this.operatorInterface.getDriveTrainYVelocity();

        // adjust the intensity of the input
        xVelocity = this.adjustIntensity(xVelocity);
        yVelocity = this.adjustIntensity(yVelocity);

        // if we are outside of our dead zone, calculate desired power values
        double radius = Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
        if (radius > TuningConstants.DRIVETRAIN_DEAD_ZONE)
        {
            if (simpleDriveModeEnabled)
            {
                // simple drive enables either forward/back or in-place left/right turn only
                //
                //                   forward
                //               ---------------
                //               |      |      |
                //               |      |      |
                // In-place left |-------------| In-place right
                //               |      |      |
                //               |      |      |
                //               ---------------
                //                  backward
                //

                if (Math.abs(yVelocity) < Math.abs(xVelocity))
                {
                    // in-place turn
                    leftVelocityGoal = xVelocity;
                    rightVelocityGoal = -xVelocity;
                }
                else
                {
                    // forward/backward
                    leftVelocityGoal = yVelocity;
                    rightVelocityGoal = yVelocity;
                }
            }
            else
            {
                // advanced drive enables varying-degree turns.
                // math is derived using linear interpolation
                //
                //     a,1       1,1       1,a
                //      ---------------------
                //      |         |         |
                //      |   Q2    |   Q1    |
                //      |         |         |
                // -b,b |-------------------| b,-b
                //      |         |         |
                //      |   Q3    |   Q4    |
                //      |         |         |
                //      ---------------------
                //    -a,-1     -1,-1     -1,-a
                //
                // for x: 0 -> 1, power(x) = power(0) + x*(power(1) - power(0)) 
                // for y: 0 -> 1, power(x,y) = power(x,0) + y*(power(x,1) - power(x,0))

                if (xVelocity >= 0)
                {
                    if (yVelocity >= 0)
                    {
                        // Q1:
                        // y=1 => lp = 1.  rp = 1 + x*(a - 1)
                        // y=0 => lp = 0 + x*b = x*b.  rp = 0 + x*-b = -x*b
                        // lp = x*b + y*(1 - x*b)
                        // rp = x*-b + y*(1+x*(a-1) - x*-b)
                        leftVelocityGoal = xVelocity * TuningConstants.DRIVETRAIN_B + yVelocity * (1 - xVelocity * TuningConstants.DRIVETRAIN_B);
                        rightVelocityGoal = -xVelocity * TuningConstants.DRIVETRAIN_B + yVelocity * (1 + xVelocity * (TuningConstants.DRIVETRAIN_A - 1) + xVelocity * TuningConstants.DRIVETRAIN_B);
                    }
                    else
                    {
                        // Q4:
                        // y=-1 => lp = -1.  rp = -1 + x*(-a - -1)  
                        // y=0  => lp = x*B.  rp = -x*B (see Q1)
                        // lp = x*B + -1*y*(-1 - x*B)
                        // rp = x*-B + -1*y*(-1+x*(-a - -1) - x*-B)
                        leftVelocityGoal = xVelocity * TuningConstants.DRIVETRAIN_B - yVelocity * (-1 - xVelocity * TuningConstants.DRIVETRAIN_B);
                        rightVelocityGoal = -xVelocity * TuningConstants.DRIVETRAIN_B - yVelocity * (-1 + xVelocity * (-TuningConstants.DRIVETRAIN_A + 1) + xVelocity * TuningConstants.DRIVETRAIN_B);
                    }
                }
                else
                {
                    if (yVelocity >= 0)
                    {
                        // Q2:
                        // y=1 => lp = 1 + -1*x*(a - 1) = 1 - x*(a - 1).  rp = 1
                        // y=0 => lp = 0 + -1*x*(-b - 0) = x*b.  rp = 0 + -1*x*(b - 0) = -x*b
                        // lp = x*b + y*(1 - x*(a-1) - x*b)
                        // rp = -x*b + y*(1 - -x*B)
                        leftVelocityGoal = xVelocity * TuningConstants.DRIVETRAIN_B + yVelocity * (1 - xVelocity * (TuningConstants.DRIVETRAIN_A - 1) - xVelocity * TuningConstants.DRIVETRAIN_B);
                        rightVelocityGoal = -xVelocity * TuningConstants.DRIVETRAIN_B + yVelocity * (1 + xVelocity * TuningConstants.DRIVETRAIN_B);
                    }
                    else
                    {
                        // Q3:
                        // y=-1 => lp = -1 + -1*x*(-a - -1) = -1 - x*(-a + 1).  rp = -1 
                        // y=0  => lp = x*b.  rp = -x*b (see Q2) 
                        // lp = x*b + -1*y*(-1 - x*(-a + 1) - x*b)
                        // rp = -x*b + -1*y*(-1 - -x*b)
                        leftVelocityGoal = xVelocity * TuningConstants.DRIVETRAIN_B - yVelocity * (-1 - xVelocity * (-TuningConstants.DRIVETRAIN_A + 1) - xVelocity * TuningConstants.DRIVETRAIN_B);
                        rightVelocityGoal = -xVelocity * TuningConstants.DRIVETRAIN_B - yVelocity * (-1 + xVelocity * TuningConstants.DRIVETRAIN_B);
                    }
                }
            }
        }

        // ensure that our algorithms are correct and don't give values outside
        // the appropriate range
        this.assertPowerLevelRange(leftVelocityGoal, "left velocity (goal)");
        this.assertPowerLevelRange(rightVelocityGoal, "right velocity (goal)");

        // decrease the velocity based on the configured max speed
        leftVelocityGoal = leftVelocityGoal * TuningConstants.DRIVETRAIN_MAX_SPEED;
        rightVelocityGoal = rightVelocityGoal * TuningConstants.DRIVETRAIN_MAX_SPEED;

        // convert velocity goal to power level...
        double leftPower;
        double rightPower;
        if (this.usePID)
        {
            this.leftPID.calculate(leftVelocityGoal, this.component.getLeftEncoderVelocity());
            this.rightPID.calculate(rightVelocityGoal, this.component.getLeftEncoderVelocity());
            
            leftPower = this.leftPID.getOutput();
            rightPower = this.rightPID.getOutput();
        }
        else
        {
            leftPower = leftVelocityGoal;
            rightPower = rightVelocityGoal;
        }

        return new PowerSetting(leftPower, rightPower);
    }

    /**
     * Calculate the power setting to use based on the inputs when in position mode
     * @return power settings for left and right motor
     */
    private PowerSetting calculatePositionModePowerSetting()
    {
        // TODO: figure out calculation...
        return new PowerSetting(0.0, 0.0);
    }

    /**
     * Assert that the power level is within the required range
     * @param powerLevel to verify
     * @param side indicator for the exception message if incorrect
     */
    private void assertPowerLevelRange(double powerLevel, String side)
    {
        if (powerLevel < DriveTrainController.POWERLEVEL_MIN)
        {
            throw new RuntimeException(side + " power level too low!");
        }

        if (powerLevel > DriveTrainController.POWERLEVEL_MAX)
        {
            throw new RuntimeException(side + " power level too high!");
        }
    }

    /**
     * Adjust the intensity of the input value
     * @param value to adjust
     * @return adjusted value
     */
    private double adjustIntensity(double value)
    {
        // we will use simple quadratic scaling to adjust input intensity
        if (value < 0)
        {
            return -value * value;
        }
        else
        {
            return value * value;
        }
    }

    /**
     * Simple holder of power setting information for the left and right motor
     * (This exists only to allow splitting out common code and have only one return value, because Java doesn't support multi-return)
     */
    private class PowerSetting
    {
        private double leftPower;
        private double rightPower;

        /**
         * Initializes a new PowerSetting
         * @param leftPower to apply
         * @param rightPower to apply
         */
        public PowerSetting(double leftPower, double rightPower)
        {
            this.leftPower = leftPower;
            this.rightPower = rightPower;
        }

        /**
         * gets the left power setting 
         * @return value between -1.0 and 1.0
         */
        public double getLeftPower()
        {
            return this.leftPower;
        }

        /**
         * gets the right power setting 
         * @return value between -1.0 and 1.0
         */
        public double getRightPower()
        {
            return this.rightPower;
        }
    }
}
