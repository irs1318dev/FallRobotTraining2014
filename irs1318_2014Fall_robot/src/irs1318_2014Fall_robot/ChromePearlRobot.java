package irs1318_2014Fall_robot;

import irs1318_2014Fall_robot.Autonomous.AutonomousOperator;
import irs1318_2014Fall_robot.Collector.CollectorComponent;
import irs1318_2014Fall_robot.Collector.CollectorController;
import irs1318_2014Fall_robot.Common.IOperatorComponent;
import irs1318_2014Fall_robot.Compressor.CompressorComponent;
import irs1318_2014Fall_robot.Compressor.CompressorController;
import irs1318_2014Fall_robot.DriveTrain.DriveTrainComponent;
import irs1318_2014Fall_robot.DriveTrain.DriveTrainController;
import irs1318_2014Fall_robot.Shooter.ShooterComponent;
import irs1318_2014Fall_robot.Shooter.ShooterController;
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
    private IOperatorComponent operatorComponent;
    
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
        if (this.operatorComponent != null)
        {
            this.operatorComponent.stop();
            this.operatorComponent = null;
        }

        if (this.compressorController != null)
        {
            this.compressorController.stop();
            this.compressorController = null;
        }

        if (this.driveTrainController != null)
        {
            this.driveTrainController.stop();
            this.driveTrainController = null;
        }

        if (this.collectorController != null)
        {
            this.collectorController.stop();
            this.collectorController = null;
        }

        if (this.shooterController != null)
        {
            this.shooterController.stop();
            this.shooterController = null;
        }
    }

    public void autonomousInit()
    {
        // create autonomous operator
        this.operatorComponent = new AutonomousOperator();
        
        this.generalInit();
    }

    public void teleopInit()
    {
        // create input for user's joystick
        this.operatorComponent = new UserJoystickComponent();
        
        this.generalInit();
    }

    public void generalInit()
    {
        // create controllers for each mechanism
        this.compressorController = new CompressorController(this.compressorComponent);
        this.driveTrainController = new DriveTrainController(this.operatorComponent, this.driveTrainComponent, false);
        this.collectorController = new CollectorController(this.operatorComponent, this.collectorComponent);
        this.shooterController = new ShooterController(this.operatorComponent, this.shooterComponent);

        // we will run the compressor controller here because we should start it in advance...
        this.compressorController.run();
    }

    public void disabledPeriodic()
    {
    }

    public void autonomousPeriodic()
    {
        this.generalPeriodic();
    }

    public void teleopPeriodic()
    {
        this.generalPeriodic();
    }

    public void generalPeriodic()
    {
        this.operatorComponent.tick();

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