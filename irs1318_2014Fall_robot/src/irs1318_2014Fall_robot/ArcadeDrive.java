package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class ArcadeDrive extends RobotComponentBase {

	private final static int LEFT_PORT = 1;
	private final static int RIGHT_PORT = 2;
	private final static int COLLECTOR_PORT = 3;
	private Joystick controller;
	private Talon talonL, talonR, talonC;
	private final static float deadZone = 0.35f;
	private final static float speedCoef = 0.4f;
	private float speed;
	private MotorHandleManager manager;

	/**
	 * The main method that should be overridden. NOTE:
	 * <code>autonomousPeriodic</code> defaults to calling this method. If your
	 * class should not be active in autonomous mode, include the following line
	 * in your class:<br/>
	 * <br/>
	 * <code>public void autonomousPeridodic(){} </code>
	 */
	public void teleopPeriodic() {
		float x = (float) controller.getX();
		float y = (float) controller.getY();
		speed = (float) Math.sqrt(x * x + y * y);
		if ((float) Math.abs((double) speed) > deadZone) {

			manager.update(x, y, speed);
			talonR.set(manager.getRight() * speedCoef);
			talonL.set(-manager.getLeft() * speedCoef);
		} else {
			talonL.set(0);
			talonR.set(0);
		}
		if (controller.getRawButton(3)) {
			talonC.set(-1);
		} else if (controller.getRawButton(5)) {
			talonC.set(1);
		} else {

			talonC.set(0);
		}
	}

	public void robotInit() {
		talonR = new Talon(1, RIGHT_PORT);
		talonL = new Talon(1, LEFT_PORT);
		talonC = new Talon(1, COLLECTOR_PORT);
	}

	public void disabledInit() {

	}

	public void autonomousInit() {

	}

	public void teleopInit() {
		controller = new Joystick(2);
		manager = new MotorHandleManager();
	}

	public void disabledPeriodic() {

	}

	public void autonomousPeriodic() {
		teleopPeriodic();
	}

}
