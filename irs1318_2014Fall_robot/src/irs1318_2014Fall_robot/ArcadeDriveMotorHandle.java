package irs1318_2014Fall_robot;

public class ArcadeDriveMotorHandle {
	public float rightMotor(float angle) {
		return angle;
	}

	public float leftMotor(float angle) {
		return angle;

	}

	public boolean change(float angle) {
		return true;
	}

	public ArcadeDriveMotorHandle change() {
		return new ArcadeDriveMotorHandle();

	}
}
