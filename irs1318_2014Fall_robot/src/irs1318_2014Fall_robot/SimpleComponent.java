package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class SimpleComponent extends RobotComponentBase {
	private Talon talon;
	private Joystick joystick;
	
	public void robotInit() {
		talon = new Talon(1, 1);
		joystick = new Joystick(2);
	}
	
	public void teleopPeriodic() {
		if (joystick.getX() > 0.75) {
			talon.set(0.2);
		} else {
			talon.set(0);
		}
	}
}
