package irs1318_2014Fall_robot;

public class MotorHandleRightF extends ArcadeDriveMotorHandle {
	public MotorHandleRightF() {
		// TODO Auto-generated constructor stub
	}

	public float leftMotor(float angle) {
		// TODO Auto-generated method stub
		return (float) Math.cos(angle);
	}

	public float rightMotor(float angle) {
		// TODO Auto-generated method stub
		return 1f;
	}

	public boolean change(float angle) {
		// TODO Auto-generated method stub
		if ((angle <= 0 && angle >= -90) || (angle >= 90 && angle <= 180)) {
			return false;

		} else {
			return true;
		}

	}

	public ArcadeDriveMotorHandle change() {
		// TODO Auto-generated method stub
		return new MotorHandleLeftF();
	}
}
