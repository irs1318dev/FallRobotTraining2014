package irs1318_2014Fall_robot;

public class ShooterController implements IController
{
    private IJoystickComponent userInterface;
    private ShooterComponent component;

    public ShooterController(IJoystickComponent userInterface, ShooterComponent component)
    {
        this.userInterface = userInterface;
        this.component = component;
    }

    public void run()
    {
    }
}
