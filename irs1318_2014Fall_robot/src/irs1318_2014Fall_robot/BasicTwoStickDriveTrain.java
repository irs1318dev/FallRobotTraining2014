package irs1318_2014Fall_robot;
import edu.wpi.first.wpilibj.*;

public class BasicTwoStickDriveTrain extends RobotComponentBase
{
	private static final int LEFT_JOYSTICK_PORT = 1;
	private static final int RIGHT_JOYSTICK_PORT = 2;
	
	private static final double DEAD_ZONE = 0.2;
	private static final double MAX_SPEED = 0.8;
	
	private Joystick leftJoystick;
	private Joystick rightJoystick;
	
	private Talon leftTalon;
	private Talon rightTalon;
	
	public void robotInit()
	{
		this.leftJoystick = new Joystick(LEFT_JOYSTICK_PORT);
		this.rightJoystick = new Joystick(RIGHT_JOYSTICK_PORT);

		this.leftTalon = new Talon(ElectronicsConstants.SIDECAR_SLOT, ElectronicsConstants.DRIVETRAIN_LEFT_TALON_CHANNEL);
		this.rightTalon = new Talon(ElectronicsConstants.SIDECAR_SLOT, ElectronicsConstants.DRIVETRAIN_RIGHT_TALON_CHANNEL);
	}

	public void teleopPeriodic()
	{
		double leftY = this.leftJoystick.getY();
		double rightY = this.rightJoystick.getY();
		
		double leftPower = 0.0;
		double rightPower = 0.0;
		if (leftY > BasicTwoStickDriveTrain.DEAD_ZONE || leftY < -BasicTwoStickDriveTrain.DEAD_ZONE)
		{
			leftPower = BasicTwoStickDriveTrain.MAX_SPEED * leftY;
		}
		
		if (rightY > BasicTwoStickDriveTrain.DEAD_ZONE || rightY < -BasicTwoStickDriveTrain.DEAD_ZONE)
		{
			rightPower = BasicTwoStickDriveTrain.MAX_SPEED * rightY;
		}
		
		this.leftTalon.set(-leftPower);
		this.rightTalon.set(rightPower);
	}
}
