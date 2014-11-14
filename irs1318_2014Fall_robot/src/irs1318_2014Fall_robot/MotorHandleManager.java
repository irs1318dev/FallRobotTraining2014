package irs1318_2014Fall_robot;

public class MotorHandleManager {
	private ArcadeDriveMotorHandle handle;
	private float leftMotor;
	private float rightMotor;

	public MotorHandleManager() {
		handle = new MotorHandleRightF();
	}

	public void update(float angle) {
		if (handle.change(angle)) {
			handle = handle.change();
		}
		leftMotor = handle.leftMotor(angle);
		rightMotor = handle.rightMotor(angle);
	}

	public float getRight() {
		return rightMotor;
	}

	public float getLeft() {
		return leftMotor;
	}
}
