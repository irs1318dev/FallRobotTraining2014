package irs1318_2014Fall_robot.Shooter;

import irs1318_2014Fall_robot.Common.IController;
import irs1318_2014Fall_robot.UserInterface.IJoystickComponent;
import irs1318_2014Fall_robot.Compressor.CompressorComponent;

public class ShooterController implements IController
{
    private IJoystickComponent userInterface;
    private ShooterComponent component;
    private CompressorComponent compressor;

    public ShooterController(IJoystickComponent userInterface, ShooterComponent component, CompressorComponent compressor)
    {
        this.userInterface = userInterface;
        this.component = component;
        this.compressor = compressor;
    }

    public void run()
    {
        this.component.setShooterAngle(this.userInterface.getShooterAngle());

        boolean middle = false;
        boolean innerLeft = false;
        boolean innerRight = false;
        boolean outerLeft = false;
        boolean outerRight = false;

        if (this.userInterface.getShooterShoot())
        {
            int shooterMode = this.userInterface.getShooterMode();
            switch (shooterMode)
            {
                case 3:
                    middle = true;
                    outerLeft = true;
                    outerRight = true;
                    break;

                case 4:
                    innerLeft = true;
                    innerRight = true;
                    outerLeft = true;
                    outerRight = true;
                    break;

                case 5:
                    middle = true;
                    innerLeft = true;
                    innerRight = true;
                    outerLeft = true;
                    outerRight = true;
                    break;
            }
        }

        this.component.setShooterSolenoids(middle, innerLeft, innerRight, outerLeft, outerRight);
    }
}
