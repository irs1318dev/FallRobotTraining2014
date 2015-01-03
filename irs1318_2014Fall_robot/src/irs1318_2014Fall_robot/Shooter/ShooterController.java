package irs1318_2014Fall_robot.Shooter;

import irs1318_2014Fall_robot.Common.IController;
import irs1318_2014Fall_robot.Common.IOperator;

/**
 * Shooter controller.
 * The controller defines the logic that controls a mechanism given inputs (component) and operator-requested actions, and 
 * translates those into the abstract functions that should be applied to the outputs (component).
 * 
 * @author Will
 *
 */
public class ShooterController implements IController
{
    private IOperator operator;
    private ShooterComponent component;

    /**
     * Initializes a new ShooterController
     * @param operator to use to operate the shooter
     * @param component to control
     */
    public ShooterController(IOperator operator, ShooterComponent component)
    {
        this.operator = operator;
        this.component = component;
    }

    /**
     * calculate the various outputs to use based on the inputs and apply them to the outputs for the relevant component
     */
    public void run()
    {
        this.component.setShooterAngle(this.operator.getShooterAngle());

        boolean middle = false;
        boolean innerLeft = false;
        boolean innerRight = false;
        boolean outerLeft = false;
        boolean outerRight = false;

        if (this.operator.getShooterShoot())
        {
            int shooterMode = this.operator.getShooterMode();
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

    /**
     * stop the relevant component
     */
    public void stop()
    {
        this.component.setShooterAngle(false);
        this.component.setShooterSolenoids(false, false, false, false, false);
    }
}
