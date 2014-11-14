package irs1318_2014Fall_robot;

public class MotorHandleLeftF extends ArcadeDriveMotorHandle {
	public MotorHandleLeftF() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public float rightMotor(float angle) {
		// TODO Auto-generated method stub
		return (float) Math.cos(angle);
	}

	@Override
	public float leftMotor(float angle) {
		// TODO Auto-generated method stub
		return 1f;

	}

	@Override
	public boolean change(float angle) {
		// TODO Auto-generated method stub
		if ((angle < 0 && angle > -90) || (angle > 90 && angle < 180)) {
			return true;

		} else {
			return false;
		}

	}

	@Override
	public ArcadeDriveMotorHandle change() {
		// TODO Auto-generated method stub
		return new MotorHandleRightF();
	}
}
