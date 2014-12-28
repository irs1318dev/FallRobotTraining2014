package irs1318_2014Fall_robot;

import irs1318_2014Fall_robot.Collector.CollectorComponent;
import irs1318_2014Fall_robot.Collector.CollectorController;
import irs1318_2014Fall_robot.Compressor.CompressorComponent;
import irs1318_2014Fall_robot.Compressor.CompressorController;
import irs1318_2014Fall_robot.DriveTrain.DriveTrainComponent;
import irs1318_2014Fall_robot.DriveTrain.DriveTrainController;
import irs1318_2014Fall_robot.Shooter.ShooterComponent;
import irs1318_2014Fall_robot.Shooter.ShooterController;
import irs1318_2014Fall_robot.UserInterface.IJoystickComponent;
import irs1318_2014Fall_robot.UserInterface.UserJoystickComponent;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * 
 * 
 * General design comments:
 * We have two major types of objects:
 * - Components - describe the electronics of an object.  These can be:
 *   - Mechanism components
 *   - General Input components (e.g. Joystick)
 * - Controllers - define the logic that controls a mechanism given inputs/outputs.
 */
public class ChromePearlRobot extends IterativeRobot
{
    private IJoystickComponent userInterfaceComponent;
    
    private DriveTrainComponent driveTrainComponent;
    private DriveTrainController driveTrainController;
    
    private CollectorComponent collectorComponent;
    private CollectorController collectorController;
    
    private CompressorComponent compressorComponent;
    private CompressorController compressorController;
    
    private ShooterComponent shooterComponent;
    private ShooterController shooterController;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        // create mechanism components
        this.compressorComponent = new CompressorComponent();
        this.driveTrainComponent = new DriveTrainComponent();
        this.collectorComponent = new CollectorComponent();
        this.shooterComponent = new ShooterComponent();
    }

    public void disabledInit()
    {
    }

    public void autonomousInit()
    {
    }

    public void teleopInit()
    {
        // create input
        this.userInterfaceComponent = new UserJoystickComponent();

        // create controllers for each mechanism
        this.compressorController = new CompressorController(this.compressorComponent);
        this.driveTrainController = new DriveTrainController(this.userInterfaceComponent, this.driveTrainComponent, false);
        this.collectorController = new CollectorController(this.userInterfaceComponent, this.collectorComponent);
        this.shooterController = new ShooterController(this.userInterfaceComponent, this.shooterComponent);

        // we will run the compressor controller here because we should start it in advance...
        this.compressorController.run();
    }

    public void disabledPeriodic()
    {
    }

    public void autonomousPeriodic()
    {
    }

    public void teleopPeriodic()
    {
        // check if any toggles have occurred on the User Interface
        this.userInterfaceComponent.checkToggles();
        
        // run each controller
        this.compressorController.run();
        this.driveTrainController.run();
        this.collectorController.run();
        this.shooterController.run();
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