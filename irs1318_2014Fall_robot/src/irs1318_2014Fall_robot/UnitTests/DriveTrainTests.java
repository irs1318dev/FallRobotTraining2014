package irs1318_2014Fall_robot.UnitTests;

import irs1318_2014Fall_robot.TuningConstants;
import irs1318_2014Fall_robot.Common.IOperatorComponent;
import irs1318_2014Fall_robot.DriveTrain.DriveTrainController;
import irs1318_2014Fall_robot.DriveTrain.IDriveTrainComponent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DriveTrainTests
{
    private class MockOperator implements IOperatorComponent
    {
        private double x;
        private double y;
        private boolean simpleModeEnabled;
        
        public MockOperator(double x, double y, boolean simpleModeEnabled)
        {
            this.x = x;
            this.y = y;
            this.simpleModeEnabled = simpleModeEnabled;
        }

        public void tick()
        {
        }

        public void stop()
        {
        }

        public boolean getCollectorExtendButton()
        {
            return false;
        }

        public boolean getCollectorRetractButton()
        {
            return false;
        }

        public boolean getCollectorCollectButton()
        {
            return false;
        }

        public boolean getCollectorExpelButton()
        {
            return false;
        }

        public boolean getShooterAngle()
        {
            return false;
        }

        public int getShooterMode()
        {
            return 0;
        }

        public boolean getShooterShoot()
        {
            return false;
        }

        public double getDriveTrainXAxis()
        {
            return this.x;
        }

        public double getDriveTrainYAxis()
        {
            return this.y;
        }

        public boolean getDriveTrainSimpleModeButton()
        {
            return this.simpleModeEnabled;
        }
    }
    
    private class MockDriveTrainComponent implements IDriveTrainComponent
    {
        private double expectedLeftPower;
        private double expectedRightPower;
        
        private static final double acceptableDelta = 0.0001;

        public MockDriveTrainComponent(double expectedLeftPower, double expectedRightPower)
        {
            this.expectedLeftPower = expectedLeftPower;
            this.expectedRightPower = expectedRightPower;
        }
        
        public void setDriveTrainPower(double leftPower, double rightPower)
        {
            Assert.assertEquals(this.expectedLeftPower, leftPower, acceptableDelta);
            Assert.assertEquals(this.expectedRightPower, rightPower, acceptableDelta);
        }

        public double getLeftEncoderVelocity()
        {
            return 0.0;
        }

        public double getRightEncoderVelocity()
        {
            return 0.0;
        }
    }
    
    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void testSteady()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(0.0, 0.0, false),
            new MockDriveTrainComponent(0.0, 0.0),
            false);
        
        controller.run();
    }

    @Test
    public void testForward()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(0.0, 1.0, false),
            new MockDriveTrainComponent(TuningConstants.DRIVETRAIN_MAX_SPEED, TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }

    @Test
    public void testBackward()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(0.0, -1.0, false),
            new MockDriveTrainComponent(-TuningConstants.DRIVETRAIN_MAX_SPEED, -TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }

    @Test
    public void testUpRight()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(1.0, 1.0, false),
            new MockDriveTrainComponent(TuningConstants.DRIVETRAIN_MAX_SPEED, TuningConstants.DRIVETRAIN_A * TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }

    @Test
    public void testUpLeft()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(-1.0, 1.0, false),
            new MockDriveTrainComponent(TuningConstants.DRIVETRAIN_A * TuningConstants.DRIVETRAIN_MAX_SPEED, TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }

    @Test
    public void testBackRight()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(1.0, -1.0, false),
            new MockDriveTrainComponent(-TuningConstants.DRIVETRAIN_MAX_SPEED, -TuningConstants.DRIVETRAIN_A * TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }

    @Test
    public void testBackLeft()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(-1.0, -1.0, false),
            new MockDriveTrainComponent(-TuningConstants.DRIVETRAIN_A * TuningConstants.DRIVETRAIN_MAX_SPEED, -TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }

    @Test
    public void testInPlaceRight()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(1.0, 0.0, false),
            new MockDriveTrainComponent(TuningConstants.DRIVETRAIN_B * TuningConstants.DRIVETRAIN_MAX_SPEED, -TuningConstants.DRIVETRAIN_B * TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }

    @Test
    public void testInPlaceLeft()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(-1.0, 0.0, false),
            new MockDriveTrainComponent(-TuningConstants.DRIVETRAIN_B * TuningConstants.DRIVETRAIN_MAX_SPEED, TuningConstants.DRIVETRAIN_B * TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }
    
    @Test
    public void testSimpleForward()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(0.2, 1.0, true),
            new MockDriveTrainComponent(TuningConstants.DRIVETRAIN_MAX_SPEED, TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }
    
    @Test
    public void testSimpleBackward()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(0.2, -1.0, true),
            new MockDriveTrainComponent(-TuningConstants.DRIVETRAIN_MAX_SPEED, -TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }
    
    @Test
    public void testSimpleRight()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(1.0, 0.2, true),
            new MockDriveTrainComponent(TuningConstants.DRIVETRAIN_MAX_SPEED, -TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }
    
    @Test
    public void testSimpleLeft()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockOperator(-1.0, 0.2, true),
            new MockDriveTrainComponent(-TuningConstants.DRIVETRAIN_MAX_SPEED, TuningConstants.DRIVETRAIN_MAX_SPEED),
            false);
        
        controller.run();
    }

}
