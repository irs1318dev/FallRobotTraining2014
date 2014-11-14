package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class ArcadeDrive extends RobotComponentBase {

	private final static int LEFT_PORT = 1;
	private final static int RIGHT_PORT = 2;
	private Joystick controller;
	private Talon talonL;
	private Talon talonR;
	private final static float deadZone = 0.35f;
	private final static float speedCoef = 0.4f;
	private float speed;
	private MotorHandleManager manager;
	private float angle;

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
			angle = (float) Math.toDegrees(atan2(x, y));
			manager.update(angle);
			talonR.set(manager.getRight() * speed * speedCoef);
			talonL.set(manager.getLeft() * speed * speedCoef);
		} else {
			talonL.set(0);
			talonR.set(0);
		}
	}

	private float atan2(float x, float y) {
		float coeff_1 = (float) (Math.PI / 4f);
		float coeff_2 = 3f * coeff_1;
		float abs_x = Math.abs(x);
		float angle;
		if (y >= 0d) {
			float r = (y - abs_x) / (y + abs_x);
			angle = (float) (coeff_1 - coeff_1 * r);
		} else {
			float r = (y + abs_x) / (abs_x - y);
			angle = (float) (coeff_2 - coeff_1 * r);
		}
		return x < 0f ? -angle : angle;
	}

	public void robotInit() {
		talonR = new Talon(RIGHT_PORT, 1);
		talonL = new Talon(LEFT_PORT, 2);

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
