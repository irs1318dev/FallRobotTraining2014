package irs1318_2014Fall_robot;

import com.sun.squawk.util.Assert;

public class DriveTrainController implements IController
{
    // these are public only so that the test cases can access them...
    public static final double DEAD_ZONE = 0.1;
    public static final double MAX_SPEED = 0.8;

    public static final double A = 0.2;
    public static final double B = 1.0;

    private static final double POWERLEVEL_MIN = -1.0;
    private static final double POWERLEVEL_MAX = 1.0;

    private IJoystick userInterface;
    private IDriveTrainComponent component;

    public DriveTrainController(IJoystick userInterface, IDriveTrainComponent component)
    {
        this.userInterface = userInterface;
        this.component = component;
    }

    public void run()
    {
        // get a value indicating that we should be in simple mode...
        boolean simpleDriveModeEnabled = this.userInterface.getDriveTrainSimpleModeButton();

        // get the X and Y values from the user interface
        double x = this.userInterface.getDriveTrainXAxis();
        double y = this.userInterface.getDriveTrainYAxis();

        // adjust the intensity of the input
        x = this.adjustIntensity(x);
        y = this.adjustIntensity(y);

        // default to no power
        double leftPower = 0.0;
        double rightPower = 0.0;

        // if we are outside of our dead zone, calculate desired power values
        double radius = Math.sqrt(x * x + y * y);
        if (radius > DriveTrainController.DEAD_ZONE)
        {
            if (simpleDriveModeEnabled)
            {
                // simple drive enables either forward/back or in-place
                // left/right turn only
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

                if (Math.abs(y) < Math.abs(x))
                {
                    leftPower = -x;
                    rightPower = x;
                }
                else
                {
                    leftPower = y;
                    rightPower = y;
                }
            }
            else
            {
                // advanced drive enables varying-degree turns.
                // math is derived using linear interpolation
                //
                //     a,1    1,1    1,a
                //      ---------------------
                //      |         |         |
                //      |   Q2    |   Q1    |
                //      |         |         |
                // -b,b |-------------------| b,-b
                //      |         |         |
                //      |   Q3    |   Q4    |
                //      |         |         |
                //      ---------------------
                //    -a,-1  -1,-1  -1,-a
                //

                if (x >= 0)
                {
                    if (y >= 0)
                    {
                        // Q1:
                        leftPower = x * B + y * (1 - x * B);
                        rightPower = -x * B + y * (1 - x * (1 - A) + x * B);
                    }
                    else
                    {
                        // Q4:
                        leftPower = x * B - y * (-1 - x * B);
                        rightPower = -x * B - y * (-1 + x * (1 - A) + x * B);
                    }
                }
                else
                {
                    if (y >= 0)
                    {
                        // Q2:
                        leftPower = -x * B + y * (1 + x * (1 - A) + x * B);
                        rightPower = x * B + y * (1 - x * B);
                    }
                    else
                    {
                        // Q3:
                        leftPower = -x * B - y * (-1 + x * (1 - A) + x * B);
                        rightPower = x * B - y * (-1 - x * B);
                    }
                }
            }
        }

        // ensure that our algorithms are correct and don't give values outside
        // the appropriate range
        this.assertPowerLevelRange(leftPower, "left");
        this.assertPowerLevelRange(rightPower, "right");

        // decrease the power based on the desired max speed
        leftPower = leftPower * DriveTrainController.MAX_SPEED;
        rightPower = rightPower * DriveTrainController.MAX_SPEED;

        // apply the power to the motors
        this.component.setDriveTrainPower(leftPower, rightPower);
    }

    private void assertPowerLevelRange(double powerLevel, String side)
    {
        Assert.that(powerLevel < POWERLEVEL_MIN, side + " power level too low!");
        Assert.that(powerLevel > POWERLEVEL_MAX, side + " power level too high!");
    }

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
}
