package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * Class that provides stub implementations for the RobotComponent interface.
 * This is meant to help serve as a buffer between the interface and any changes
 * that may occur in the WPILib. Extend this class to build a robot component.
 * 
 * @author violette
 * 
 */
public class SimpleComponent implements RobotComponent {

	private Talon talonLeft;
	private Talon talonRight;
	private Joystick joystickLeft;
	private Joystick joystickRight;
	private final static int LEFTPORT = 1;
	private final static int RIGHTPORT = 2;

	public void robotInit() {
		talonLeft = new Talon(LEFTPORT, 1);
		talonRight = new Talon(RIGHTPORT, 1);
		joystickLeft = new Joystick(1);
		joystickRight = new Joystick(2);
	}

	public void disabledInit() {

	}

	public void autonomousInit() {

	}

	public void teleopInit() {

	}

	public void disabledPeriodic() {

	}

	public void autonomousPeriodic() {
		teleopPeriodic();
	}

	/**
	 * The main method that should be overridden. NOTE:
	 * <code>autonomousPeriodic</code> defaults to calling this method. If your
	 * class should not be active in autonomous mode, include the following line
	 * in your class:<br/>
	 * <br/>
	 * <code>public void autonomousPeridodic(){} </code>
	 */
	public void teleopPeriodic() {
		if (!(joystickLeft.getY() < 0.35 && joystickLeft.getY() > -0.35)) {

			talonLeft.set(joystickLeft.getY() / 2);
		} else {

			talonLeft.set(0);

		}
		if (!(joystickRight.getY() < 0.35 && joystickRight.getY() > -0.35)) {
			talonRight.set(joystickRight.getY() / 2);

		} else {
			talonRight.set(0);
		}
	}

	public void disabledContinuous() {

	}

	public void autonomousContinuous() {

	}

	public void teleopContinuous() {

	}

}