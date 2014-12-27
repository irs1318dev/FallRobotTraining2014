package irs1318_2014Fall_robot;

public class DriveTrainController implements IController
{
    private static final double POWERLEVEL_MIN = -1.0;
    private static final double POWERLEVEL_MAX = 1.0;

    private IJoystickComponent userInterface;
    private IDriveTrainComponent component;

    private boolean usePID;
    private PIDHandler leftPID;
    private PIDHandler rightPID;

    public DriveTrainController(IJoystickComponent userInterface, IDriveTrainComponent component, boolean usePID)
    {
        this.userInterface = userInterface;
        this.component = component;
        this.usePID = usePID;
        
        if (usePID)
        {
            this.leftPID = new PIDHandler(TuningConstants.DRIVETRAIN_PID_LEFT_KP, 0.0, 0.0, TuningConstants.DRIVETRAIN_PID_LEFT_KF);
            this.rightPID = new PIDHandler(TuningConstants.DRIVETRAIN_PID_RIGHT_KP, 0.0, 0.0, TuningConstants.DRIVETRAIN_PID_RIGHT_KF);
        }
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
        double leftPowerGoal = 0.0;
        double rightPowerGoal = 0.0;

        // if we are outside of our dead zone, calculate desired power values
        double radius = Math.sqrt(x * x + y * y);
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

                if (Math.abs(y) < Math.abs(x))
                {
                    leftPowerGoal = x;
                    rightPowerGoal = -x;
                }
                else
                {
                    leftPowerGoal = y;
                    rightPowerGoal = y;
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
                        leftPowerGoal = x * TuningConstants.DRIVETRAIN_B + y * (1 - x * TuningConstants.DRIVETRAIN_B);
                        rightPowerGoal = -x * TuningConstants.DRIVETRAIN_B + y * (1 + x * (TuningConstants.DRIVETRAIN_A - 1) + x * TuningConstants.DRIVETRAIN_B);
                    }
                    else
                    {
                        // Q4:
                        // y=-1 => lp = -1.  rp = -1 + x*(-a - -1)  
                        // y=0  => lp = x*B.  rp = -x*B (see Q1)
                        // lp = x*B + -1*y*(-1 - x*B)
                        // rp = x*-B + -1*y*(-1+x*(-a - -1) - x*-B)
                        leftPowerGoal = x * TuningConstants.DRIVETRAIN_B - y * (-1 - x * TuningConstants.DRIVETRAIN_B);
                        rightPowerGoal = -x * TuningConstants.DRIVETRAIN_B - y * (-1 + x * (-TuningConstants.DRIVETRAIN_A + 1) + x * TuningConstants.DRIVETRAIN_B);
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
                        leftPowerGoal = x * TuningConstants.DRIVETRAIN_B + y * (1 - x * (TuningConstants.DRIVETRAIN_A - 1) - x * TuningConstants.DRIVETRAIN_B);
                        rightPowerGoal = -x * TuningConstants.DRIVETRAIN_B + y * (1 + x * TuningConstants.DRIVETRAIN_B);
                    }
                    else
                    {
                        // Q3:
                        // y=-1 => lp = -1 + -1*x*(-a - -1) = -1 - x*(-a + 1).  rp = -1 
                        // y=0  => lp = x*b.  rp = -x*b (see Q2) 
                        // lp = x*b + -1*y*(-1 - x*(-a + 1) - x*b)
                        // rp = -x*b + -1*y*(-1 - -x*b)
                        leftPowerGoal = x * TuningConstants.DRIVETRAIN_B - y * (-1 - x * (-TuningConstants.DRIVETRAIN_A + 1) - x * TuningConstants.DRIVETRAIN_B);
                        rightPowerGoal = -x * TuningConstants.DRIVETRAIN_B - y * (-1 + x * TuningConstants.DRIVETRAIN_B);
                    }
                }
            }
        }

        // ensure that our algorithms are correct and don't give values outside
        // the appropriate range
        this.assertPowerLevelRange(leftPowerGoal, "left (goal)");
        this.assertPowerLevelRange(rightPowerGoal, "right (goal)");

        // decrease the power based on the desired max speed
        leftPowerGoal = leftPowerGoal * TuningConstants.DRIVETRAIN_MAX_SPEED;
        rightPowerGoal = rightPowerGoal * TuningConstants.DRIVETRAIN_MAX_SPEED;

        double leftPower;
        double rightPower;
        if (this.usePID)
        {
            this.leftPID.calculate(leftPowerGoal, this.component.getLeftEncoderVelocity());
            this.rightPID.calculate(rightPowerGoal, this.component.getLeftEncoderVelocity());
            
            leftPower = this.leftPID.getOutput();
            rightPower = this.rightPID.getOutput();
        }
        else
        {
            leftPower = leftPowerGoal;
            rightPower = rightPowerGoal;
        }

        // ensure that our algorithms are correct and don't give values outside
        // the appropriate range
        this.assertPowerLevelRange(leftPower, "left");
        this.assertPowerLevelRange(rightPower, "right");

        // apply the power to the motors
        this.component.setDriveTrainPower(leftPower, rightPower);
    }

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
