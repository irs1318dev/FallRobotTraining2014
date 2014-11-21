package irs1318_2014Fall_robot;

public class MotorHandleLeftB extends ArcadeDriveMotorHandle {
	public MotorHandleLeftB() {
		// TODO Auto-generated constructor stub
	}

	public float rightMotor(float x, float y, float speed) {
		// TODO Auto-generated method stub
		return -speed;
	}

	public float leftMotor(float x, float y, float speed) {
		// TODO Auto-generated method stub
		return -(speed - Math.abs(x));
	}

	public boolean change(float x, float y) {
		// TODO Auto-generated method stub
		if (x > 0) {
			if (y > 0) {
				handle = new MotorHandleRightF();
			}
			if (y < 0) {
				handle = new MotorHandleRightB();
			}
			return true;
		} else if (x < 0 && y > 0) {
			handle = new MotorHandleLeftF();
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
