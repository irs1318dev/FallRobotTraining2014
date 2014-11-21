package irs1318_2014Fall_robot;

public class MotorHandleManager {
	private ArcadeDriveMotorHandle handle;
	private float leftMotor;
	private float rightMotor;

	public MotorHandleManager() {
		handle = new MotorHandleRightF();
	}

	public void update(float x, float y, float speed) {
		if (handle.change(x, y)) {
			handle = handle.change();
		}
		leftMotor = handle.leftMotor(x, y, speed);
		rightMotor = handle.rightMotor(x, y, speed);
	}

	public float getRight() {
		return rightMotor;
	}

	public float getLeft() {
		return leftMotor;
	}
}
