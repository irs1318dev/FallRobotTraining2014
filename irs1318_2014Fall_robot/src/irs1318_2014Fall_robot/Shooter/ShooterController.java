package irs1318_2014Fall_robot.Shooter;

import irs1318_2014Fall_robot.Common.IController;
import irs1318_2014Fall_robot.Common.IOperatorComponent;

public class ShooterController implements IController
{
    private IOperatorComponent operatorInterface;
    private ShooterComponent component;

    public ShooterController(IOperatorComponent operatorInterface, ShooterComponent component)
    {
        this.operatorInterface = operatorInterface;
        this.component = component;
    }

    public void run()
    {
        this.component.setShooterAngle(this.operatorInterface.getShooterAngle());

        boolean middle = false;
        boolean innerLeft = false;
        boolean innerRight = false;
        boolean outerLeft = false;
        boolean outerRight = false;

        if (this.operatorInterface.getShooterShoot())
        {
            int shooterMode = this.operatorInterface.getShooterMode();
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

    public void stop()
    {
        this.component.setShooterAngle(false);
        this.component.setShooterSolenoids(false, false, false, false, false);
    }
}
