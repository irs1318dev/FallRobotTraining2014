package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class IRS1318Robot extends IterativeRobot
{
    private IJoystick userInterface;
    private DriveTrainController driveTrain;
    private CollectorController collector;
    private CompressorController compressor;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
    }

    public void disabledInit()
    {
    }

    public void autonomousInit()
    {
    }

    public void teleopInit()
    {
        // create input components
        this.userInterface = new UserJoystick();
        
        // create component controllers
        this.compressor = new CompressorController();
        this.driveTrain = new DriveTrainController(this.userInterface);
        this.collector = new CollectorController(this.userInterface);
    }

    public void disabledPeriodic()
    {
    }

    public void autonomousPeriodic()
    {
    }

    public void teleopPeriodic()
    {
        this.driveTrain.run();
        this.collector.run();
    }

    public void disabledContinuous()
    {
    }

    public void autonomousContinuous()
    {
    }

    public void teleopContinuous()
    {
    }
}