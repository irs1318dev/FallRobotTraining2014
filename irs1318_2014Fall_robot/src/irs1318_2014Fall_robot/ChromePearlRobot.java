package irs1318_2014Fall_robot;

import irs1318_2014Fall_robot.Autonomous.AutonomousOperator;
import irs1318_2014Fall_robot.Autonomous.IAutonomousTask;
import irs1318_2014Fall_robot.Autonomous.Tasks.DriveAutonomousTask;
import irs1318_2014Fall_robot.Autonomous.Tasks.TurnAutonomousTask;
import irs1318_2014Fall_robot.Autonomous.Tasks.WaitAutonomousTask;
import irs1318_2014Fall_robot.Collector.CollectorComponent;
import irs1318_2014Fall_robot.Collector.CollectorController;
import irs1318_2014Fall_robot.Common.IOperator;
import irs1318_2014Fall_robot.Compressor.CompressorComponent;
import irs1318_2014Fall_robot.Compressor.CompressorController;
import irs1318_2014Fall_robot.DriveTrain.DriveTrainComponent;
import irs1318_2014Fall_robot.DriveTrain.DriveTrainController;
import irs1318_2014Fall_robot.Shooter.ShooterComponent;
import irs1318_2014Fall_robot.Shooter.ShooterController;
import irs1318_2014Fall_robot.UserInterface.UserOperator;
import irs1318_2014Fall_robot.Common.SmartDashboardLogger;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * Main class for the FRC 2014 Robot for IRS1318 - The Chrome Pearl
 * 
 * 
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package, you
 * must also update the manifest file in the resource directory.
 * 
 * 
 * General design comments:
 * We have three types of objects:
 * - Operator - describes the operator ("autonomous" or "user")
 * - Components - describe the electronics of an mechanism and defines the abstract way to control those electronics.
 * - Controllers - define the logic that controls a mechanism given inputs/outputs.
 * 
 * @author Will
 */
public class ChromePearlRobot extends IterativeRobot
{
    // logging constants
    private static final String ROBOT_STATE_LOG_KEY = "r.s";

    // Operator (e.g. joystick, autonomous)
    private IOperator operator;

    // Compressor
    private CompressorComponent compressorComponent;
    private CompressorController compressorController;

    // DriveTrain
    private DriveTrainComponent driveTrainComponent;
    private DriveTrainController driveTrainController;

    // Collector
    private CollectorComponent collectorComponent;
    private CollectorController collectorController;

    // Shooter
    private ShooterComponent shooterComponent;
    private ShooterController shooterController;

    /**
     * Robot-wide initialization code should go here.
     * This default Robot-wide initialization code will be called when 
     * the robot is first powered on.  It will be called exactly 1 time.
     */
    public void robotInit()
    {
        // create mechanism components
        this.compressorComponent = new CompressorComponent();
        this.driveTrainComponent = new DriveTrainComponent();
        this.collectorComponent = new CollectorComponent();
        this.shooterComponent = new ShooterComponent();

        SmartDashboardLogger.putString(ChromePearlRobot.ROBOT_STATE_LOG_KEY, "Init");
    }

    /**
     * Initialization code for disabled mode should go here.
     * This code will be called each time the robot enters disabled mode.
     */
    public void disabledInit()
    {
        if (this.operator != null)
        {
            this.operator.stop();
            this.operator = null;
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

        SmartDashboardLogger.putString(ChromePearlRobot.ROBOT_STATE_LOG_KEY, "Disabled");
    }

    /**
     * Initialization code for autonomous mode should go here.
     * This code will be called each time the robot enters autonomous mode.
     */
    public void autonomousInit()
    {
        // create autonomous operator
        this.operator = new AutonomousOperator(
            // drive in a square:
            new IAutonomousTask[]
            {
                new DriveAutonomousTask(600, this.driveTrainComponent),
                new WaitAutonomousTask(5),
                new TurnAutonomousTask(90, this.driveTrainComponent),
                new DriveAutonomousTask(600, this.driveTrainComponent),
                new WaitAutonomousTask(5),
                new TurnAutonomousTask(90, this.driveTrainComponent),
                new DriveAutonomousTask(600, this.driveTrainComponent),
                new WaitAutonomousTask(5),
                new TurnAutonomousTask(90, this.driveTrainComponent),
                new DriveAutonomousTask(600, this.driveTrainComponent),
                new WaitAutonomousTask(5),
                new TurnAutonomousTask(90, this.driveTrainComponent),
            });

        this.generalInit();

        SmartDashboardLogger.putString(ChromePearlRobot.ROBOT_STATE_LOG_KEY, "Autonomous");
    }

    /**
     * Initialization code for teleop mode should go here.
     * This code will be called each time the robot enters teleop mode.
     */
    public void teleopInit()
    {
        // create operator for user's joystick
        this.operator = new UserOperator();

        this.generalInit();

        SmartDashboardLogger.putString(ChromePearlRobot.ROBOT_STATE_LOG_KEY, "Teleop");
    }

    /**
     * General initialization code for teleop/autonomous mode should go here.
     */
    public void generalInit()
    {
        // create controllers for each mechanism
        this.compressorController = new CompressorController(this.compressorComponent);
        this.driveTrainController = new DriveTrainController(this.operator, this.driveTrainComponent, false);
        this.collectorController = new CollectorController(this.operator, this.collectorComponent);
        this.shooterController = new ShooterController(this.operator, this.shooterComponent);

        // we will run the compressor controller here because we should start it in advance...
        this.compressorController.run();
    }

    /**
     * Periodic code for disabled mode should go here.
     * This code will be called periodically at a regular rate while the robot is in disabled mode.
     */
    public void disabledPeriodic()
    {
    }

    /**
     * Periodic code for autonomous mode should go here.
     * This code will be called periodically at a regular rate while the robot is in autonomous mode.
     */
    public void autonomousPeriodic()
    {
        this.generalPeriodic();
    }

    /**
     * Periodic code for teleop mode should go here.
     * This code will be called periodically at a regular rate while the robot is in teleop mode.
     */
    public void teleopPeriodic()
    {
        this.generalPeriodic();
    }

    /**
     * General periodic code for teleop/autonomous mode should go here.
     */
    public void generalPeriodic()
    {
        this.operator.tick();

        // run each controller
        this.compressorController.run();
        this.driveTrainController.run();
        this.collectorController.run();
        this.shooterController.run();
    }
}
