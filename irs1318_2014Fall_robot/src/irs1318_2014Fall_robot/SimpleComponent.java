package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class SimpleComponent extends RobotComponentBase {
	private static final double DEADZONE = 0.5;
	private Talon talon1;  
	private Talon talon2;
	private Talon talon3; 
	private Joystick joystick1; 
//	private Joystick joystick2;
	
	public void robotInit(){
		talon2 = new Talon(1, 2);
		talon1 = new Talon(1, 1);
		talon3 = new Talon(1, 3);
		joystick1 = new Joystick(1);
//		joystick2 = new Joystick(4);
		
		DoubleSolenoid solenoidArm1 = new DoubleSolenoid();
		DoubleSolenoid solenoidArm2 = new DoubleSolenoid();
		
		DoubleSolenoid solenoidLauncher1 = new DoubleSolenoid();
		DoubleSolenoid solenoidLauncher2 = new DoubleSolenoid();
	}
	
	public void teleopPeriodic(){
		if (joystick1.getRawButton(13)) {
			solenoidArm1.set(DoubleSolenoid.Value.kForward);
			solenoidArm2.set(DoubleSolenoid.Value.kForward);
		}
		
		else if (joystick1.getRawButton(14)) {
			solenoidArm1.set(DoubleSolenoid.Value.kReverse);
			solenoidArm2.set(DoubleSolenoid.Value.kReverse);
		}
		
		else {
			solenoidArm1.set(DoubleSolenoid.Value.kOff);
			solenoidArm2.set(DoubleSolenoid.Value.kOff);
		}
		
		if (joystick1.getRawButton(345)) {
			solenoidLauncher1.set(DoubleSolenoid.Value.kForward);
			solenoidLauncher2.set(DoubleSolenoid.Value.kForward);
		}
		
		else if (joystick1.getRawButton(346)) {
			solenoidLauncher1.set(DoubleSolenoid.Value.kReverse);
			solenoidLauncher2.set(DoubleSolenoid.Value.kReverse);
		}
		
		else {
			solenoidLauncher1.set(DoubleSolenoid.Value.kOff);
			solenoidLauncher2.set(DoubleSolenoid.Value.kOff);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		//Left Joystick
//		if(joystick1.getY() > 0.50){
//			talon1.set(joystick1.getY() - 0.5);
//		} else if(joystick1.getY() < -0.50){
//			talon1.set(joystick1.getY() + 0.5);
//		} else{
//		talon1.set(0); 
//		}
//		
//		//Right Joystick
//		if(joystick2.getY() > 0.50){
//			talon2.set(joystick2.getY() - 0.5);
//		} else if(joystick2.getY() < -0.50){
//			talon2.set(joystick2.getY() + 0.5);
//		} else{
//			talon2.set(0);
//		}
		
		//Arcade Joystick		
		if (Math.abs(joystick1.getY()) > DEADZONE && Math.abs(joystick1.getX()) < DEADZONE) {
			talon1.set(-joystick1.getY() / 2);
			talon2.set(joystick1.getY() / 2);
		} else if (Math.abs(joystick1.getY()) < DEADZONE && Math.abs(joystick1.getX()) > DEADZONE) {
			talon1.set(-joystick1.getX() / 2);
			talon2.set(joystick1.getX() / -2);
		//Put some actual math here, not this. Maybe trig
		
		} else if (Math.abs(joystick1.getY()) > DEADZONE && Math.abs(joystick1.getX()) > DEADZONE) {
			if (joystick1.getX() > 0) {
				talon1.set(-joystick1.getY() / 2);
					talon2.set((joystick1.getY() / (2 * Math.abs(joystick1.getX()))) / 4);
//				talon2.set(Math.atan(joystick1.getY() / joystick1.getX()));
					
			} else {
				talon1.set(-(joystick1.getY() / (2 * Math.abs(joystick1.getX()))) / 4);
//				talon1.set(Math.atan(joystick1.getY() / joystick1.getX()));
				talon2.set(joystick1.getY() / 2);
			}
		/*
		} else {
			double theta = Math.arctan(joystick1.getY(), joystick1.getX());
			double BASELINE = .25;
			double MAX = .5
			
			//1st quadrant
			if (theta > 0 && theta <= Math.PI/2) {
				talon1.set(MAX);
				talon2.set(BASELINE+(MAX-BASELINE)*Math.sin(theta);
			}
			//2nd quadrant
			else if(theta > Math.PI/2 && theta < Math.PI) {
				talon2.set(MAX);
				talon1.set(BASELINE+(MAX-BASELINE)*Math.sin(theta);
			}
			//3rd quadrant is opposite to 2nd quadrant, R>L but moving backwards
			else if(theta > Math.PI && theta <= Math.PI*3/2) {
				talon2.set(-MAX);
				talon1.set(-BASELINE+(MAX-BASELINE)*Math.sin(theta);
			}
			//4th quadrant is opposite to 1nd quadrant, L>R but moving backwards (theta > Math.PI*3/2 && theta < Math.PI*2)			
			else {
				talon1.set(-MAX);
				talon1.set(-BASELINE+(MAX-BASELINE)*Math.sin(theta);
			}			
			}
			
			*/

			
		} else {
			talon1.set(0);
			talon2.set(0);
		}
	
		//Buttons
		if(joystick1.getRawButton(3)) {
			talon3.set(0.5);
		} else if (joystick1.getRawButton(5)){
			talon3.set(-0.5);
		}else{
			talon3.set(0);
		}
		
	}
		
		
	
}

