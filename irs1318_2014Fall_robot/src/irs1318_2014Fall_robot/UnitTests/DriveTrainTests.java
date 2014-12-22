package irs1318_2014Fall_robot.UnitTests;

import static org.junit.Assert.*;
import irs1318_2014Fall_robot.IDriveTrainComponent;
import irs1318_2014Fall_robot.IJoystick;
import irs1318_2014Fall_robot.DriveTrainController;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DriveTrainTests
{
    private class MockDriveTrainJoystick implements IJoystick
    {
        private double x;
        private double y;
        private boolean simpleModeEnabled;
        
        public MockDriveTrainJoystick(double x, double y, boolean simpleModeEnabled)
        {
            this.x = x;
            this.y = y;
            this.simpleModeEnabled = simpleModeEnabled;
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
        
        private static final double delta = 0.0;

        public MockDriveTrainComponent(double expectedLeftPower, double expectedRightPower)
        {
            this.expectedLeftPower = expectedLeftPower;
            this.expectedRightPower = expectedRightPower;
        }
        
        public void setDriveTrainPower(double leftPower, double rightPower)
        {
            Assert.assertEquals(this.expectedLeftPower, leftPower, 0.0);
            Assert.assertEquals(this.expectedRightPower, rightPower, 0.0);
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
            new MockDriveTrainJoystick(0.0, 0.0, false),
            new MockDriveTrainComponent(0.0, 0.0));
        
        controller.run();
    }

    @Test
    public void testForward()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(0.0, 1.0, false),
            new MockDriveTrainComponent(DriveTrainController.MAX_SPEED, DriveTrainController.MAX_SPEED));
        
        controller.run();
    }

    @Test
    public void testBackward()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(0.0, -1.0, false),
            new MockDriveTrainComponent(-DriveTrainController.MAX_SPEED, -DriveTrainController.MAX_SPEED));
        
        controller.run();
    }

    @Test
    public void testUpRight()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(1.0, 1.0, false),
            new MockDriveTrainComponent(DriveTrainController.MAX_SPEED, DriveTrainController.A * DriveTrainController.MAX_SPEED));
        
        controller.run();
    }

    @Test
    public void testUpLeft()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(-1.0, 1.0, false),
            new MockDriveTrainComponent(DriveTrainController.A * DriveTrainController.MAX_SPEED, DriveTrainController.MAX_SPEED));
        
        controller.run();
    }

    @Test
    public void testBackRight()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(1.0, -1.0, false),
            new MockDriveTrainComponent(-DriveTrainController.MAX_SPEED, -DriveTrainController.A));
        
        controller.run();
    }

    @Test
    public void testBackLeft()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(-1.0, -1.0, false),
            new MockDriveTrainComponent(-DriveTrainController.A * DriveTrainController.MAX_SPEED, -DriveTrainController.MAX_SPEED));
        
        controller.run();
    }

    @Test
    public void testInPlaceRight()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(1.0, 0.0, false),
            new MockDriveTrainComponent(DriveTrainController.B * DriveTrainController.MAX_SPEED, -DriveTrainController.B * DriveTrainController.MAX_SPEED));
        
        controller.run();
    }

    @Test
    public void testInPlaceLeft()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(-1.0, 0.0, false),
            new MockDriveTrainComponent(-DriveTrainController.B * DriveTrainController.MAX_SPEED, DriveTrainController.B * DriveTrainController.MAX_SPEED));
        
        controller.run();
    }
    
    @Test
    public void testSimpleForward()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(0.2, 1.0, true),
            new MockDriveTrainComponent(DriveTrainController.MAX_SPEED, DriveTrainController.MAX_SPEED));
        
        controller.run();
    }
    
    @Test
    public void testSimpleBackward()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(0.2, -1.0, true),
            new MockDriveTrainComponent(-DriveTrainController.MAX_SPEED, -DriveTrainController.MAX_SPEED));
        
        controller.run();
    }
    
    @Test
    public void testSimpleRight()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(1.0, 0.2, true),
            new MockDriveTrainComponent(DriveTrainController.MAX_SPEED, -DriveTrainController.MAX_SPEED));
        
        controller.run();
    }
    
    @Test
    public void testSimpleLeft()
    {
        DriveTrainController controller = new DriveTrainController(
            new MockDriveTrainJoystick(-1.0, 0.2, true),
            new MockDriveTrainComponent(-DriveTrainController.MAX_SPEED, DriveTrainController.MAX_SPEED));
        
        controller.run();
    }

}
