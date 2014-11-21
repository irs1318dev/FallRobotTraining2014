package irs1318_2014Fall_robot;

public class MotorHandleRightF extends ArcadeDriveMotorHandle {
	public MotorHandleRightF() {
		// TODO Auto-generated constructor stub
	}

	public float rightMotor(float x, float y, float speed) {
		// TODO Auto-generated method stub
		return (speed - x);
	}

	public float leftMotor(float x, float y, float speed) {
		// TODO Auto-generated method stub
		return speed;
	}

	public boolean change(float x, float y) {
		// TODO Auto-generated method stub
		if (x < 0) {
			if (y > 0) {
				handle = new MotorHandleLeftF();
			}
			if (y < 0) {
				handle = new MotorHandleLeftB();
			}
			return true;
		} else if (x > 0 && y < 0) {
			handle = new MotorHandleRightB();
			return true;
		} else {
			return false;
		}
	}

	public ArcadeDriveMotorHandle change() {
		// TODO Auto-generated method stub
		return handle;
	}
}
