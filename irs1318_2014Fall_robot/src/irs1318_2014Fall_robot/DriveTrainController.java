package irs1318_2014Fall_robot;

public class DriveTrainController implements IController
{
    // these are public only so that the test cases can access them...
    public static final double DEAD_ZONE = 0.1;
    public static final double MAX_SPEED = 0.8;

    public static final double A = 0.2;
    public static final double B = 1.0;

    private static final double POWERLEVEL_MIN = -1.0;
    private static final double POWERLEVEL_MAX = 1.0;

    private IJoystickComponent userInterface;
    private IDriveTrainComponent component;

    public DriveTrainController(IJoystickComponent userInterface, IDriveTrainComponent component)
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

                if (Math.abs(y) < Math.abs(x))
                {
                    leftPower = x;
                    rightPower = -x;
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

                if (x >= 0)
                {
                    if (y >= 0)
                    {
                        // Q1:
                        // y=1 => lp = 1.  rp = 1 + x*(a - 1)
                        // y=0 => lp = 0 + x*b = x*b.  rp = 0 + x*-b = -x*b
                        // lp = x*b + y*(1 - x*b)
                        // rp = x*-b + y*(1+x*(a-1) - x*-b)
                        leftPower = x * B + y * (1 - x * B);
                        rightPower = -x * B + y * (1 + x * (A - 1) + x * B);
                    }
                    else
                    {
                        // Q4:
                        // y=-1 => lp = -1.  rp = -1 + x*(-a - -1)  
                        // y=0  => lp = x*B.  rp = -x*B (see Q1)
                        // lp = x*B + -1*y*(-1 - x*B)
                        // rp = x*-B + -1*y*(-1+x*(-a - -1) - x*-B)
                        leftPower = x * B - y * (-1 - x * B);
                        rightPower = -x * B - y * (-1 + x * (-A + 1) + x * B);
                    }
                }
                else
                {
                    if (y >= 0)
                    {
                        // Q2:
                        // y=1 => lp = 1 + -1*x*(a - 1) = 1 - x*(a - 1).  rp = 1
                        // y=0 => lp = 0 + -1*x*(-b - 0) = x*b.  rp = 0 + -1*x*(b - 0) = -x*b
                        // lp = x*b + y*(1 - x*(a-1) - x*b)
                        // rp = -x*b + y*(1 - -x*B)
                        leftPower = x * B + y * (1 - x * (A - 1) - x * B);
                        rightPower = -x * B + y * (1 + x * B);
                    }
                    else
                    {
                        // Q3:
                        // y=-1 => lp = -1 + -1*x*(-a - -1) = -1 - x*(-a + 1).  rp = -1 
                        // y=0  => lp = x*b.  rp = -x*b (see Q2) 
                        // lp = x*b + -1*y*(-1 - x*(-a + 1) - x*b)
                        // rp = -x*b + -1*y*(-1 - -x*b)
                        leftPower = x * B - y * (-1 - x * (-A + 1) - x * B);
                        rightPower = -x * B - y * (-1 + x * B);
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
        if (powerLevel < POWERLEVEL_MIN)
        {
            throw new RuntimeException(side + " power level too low!");
        }
        
        if (powerLevel > POWERLEVEL_MAX)
        {
            throw new RuntimeException(side + " power level too high!");
        }
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
