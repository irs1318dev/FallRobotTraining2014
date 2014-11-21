package irs1318_2014Fall_robot;

public class ArcadeDriveMotorHandle {
	protected ArcadeDriveMotorHandle handle;

	public float rightMotor(float x, float y, float speed) {
		return y;
	}

	public float leftMotor(float x, float y, float speed) {
		return x;

	}

	public boolean change(float x, float y) {
		return true;
	}

	public ArcadeDriveMotorHandle change() {
		return new ArcadeDriveMotorHandle();

	}
}
