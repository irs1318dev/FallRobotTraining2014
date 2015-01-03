package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class CollectorMotorComponent extends RobotComponentBase {
	public static final int COLLECTOR_TALON_PORT = 3;
	public static final int JOYSTICK_PORT = 1;
	
	public static final int COLLECTOR_IN_BUTTON = 3;
	public static final int COLLECTOR_OUT_BUTTON = 5;
	
	Joystick stick;
	Talon collectorTalon;
	
	public void robotInit() {
		stick = new Joystick(JOYSTICK_PORT);
		collectorTalon = new Talon(1, COLLECTOR_TALON_PORT);
	}
	
	public void teleopPeriodic() {
		if (stick.getRawButton(COLLECTOR_IN_BUTTON)) {
			collectorTalon.set(1);
		} else if (stick.getRawButton(COLLECTOR_IN_BUTTON)) {
			collectorTalon.set(-1);
		} else {
			collectorTalon.set(0);
		}
	}
}
