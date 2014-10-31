package irs1318_2014Fall_robot;
import edu.wpi.first.wpilibj.*;

public class BasicTwoStickDriveTrainComponent extends RobotComponentBase {
	public static final int LEFT_JOYSTICK_PORT = 1;
	public static final int RIGHT_JOYSTICK_PORT = 2;
	
	public static final int LEFT_TALON_PORT = 1;
	public static final int RIGHT_TALON_PORT = 2;
	
	Joystick leftStick, rightStick;
	Talon leftTalon, rightTalon;
	
	public void robotInit() {
		leftStick = new Joystick(LEFT_JOYSTICK_PORT);
		rightStick = new Joystick(RIGHT_JOYSTICK_PORT);
		
		leftTalon = new Talon(LEFT_TALON_PORT, 1);
		rightTalon = new Talon(RIGHT_TALON_PORT, 1);
	}
	
	public void teleopPeriodic() {
		double leftStickY = leftStick.getY();
		double rightStickY = rightStick.getY();

		if (leftStickY > 0.75 || leftStickY < -0.75) {
			leftTalon.set(leftStickY / 3);
		}
		if (rightStickY > .75 || rightStickY < -0.75) {
			leftTalon.set(rightStickY / 3);
		}
		
	}
}
