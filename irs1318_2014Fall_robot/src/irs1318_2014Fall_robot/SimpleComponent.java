package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class SimpleComponent extends RobotComponentBase {
	private static final double DEADZONE = 0.5;
	private Talon talon1;  
	private Talon talon2;
	private Joystick joystick1; 
//	private Joystick joystick2;
	
	public void robotInit(){
		talon2 = new Talon(2, 2);
		talon1 = new Talon(2, 1);
		joystick1 = new Joystick(1);
//		joystick2 = new Joystick(4);
	}
	
	public void teleopPeriodic(){
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
		
		
		if (Math.abs(joystick1.getY()) > DEADZONE && Math.abs(joystick1.getX()) < DEADZONE) {
			talon1.set(joystick1.getY() / 2);
			talon2.set(joystick1.getY() / 2);
		} else if (Math.abs(joystick1.getY()) < DEADZONE && Math.abs(joystick1.getX()) > DEADZONE) {
			talon1.set(joystick1.getX() / 2);
			talon2.set(joystick1.getX() / -2);
		//Put some actual math here, not this. Maybe trig
		} else if (Math.abs(joystick1.getY()) > DEADZONE && Math.abs(joystick1.getX()) > DEADZONE) {
			if (joystick1.getX() > 0) {
				talon1.set(joystick1.getY() / 2);
					talon2.set((joystick1.getY() / (2 * Math.abs(joystick1.getX()))) / 4);
//				talon2.set(Math.atan(joystick1.getY() / joystick1.getX()));
					
			} else {
				talon1.set((joystick1.getY() / (2 * Math.abs(joystick1.getX()))) / 4);
//				talon1.set(Math.atan(joystick1.getY() / joystick1.getX()));
				talon2.set(joystick1.getY() / 2);
			}
		} else {
			talon1.set(0);
			talon2.set(0);
		}
	
		/* jared and will r nerds
		if (joystick1.getY() > 0.50) {
			talon1.set(joystick1.getY() - 0.5);
			talon2.set(joystick1.getY() - 0.5);
		} else if (joystick1.getY() < -0.50) {
			talon1.set(joystick1.getY() + 0.5);
			talon2.set(joystick1.getY() + 0.5);
		} else {
			talon1.set(0.0);
			talon2.set(0.0);
		}
		
		if (joystick1.getX() > 0.50) {
			talon1.set(joystick1.getX() - 0.5); 
			talon2.set((joystick1.getX() - 0.5) / 2);
		} else if (joystick1.getX() < -0.50) {
			talon1.set(joystick1.getX() + 0.5);
			talon2.set((joystick1.getX() + 0.5) / 2);
		} else {
			talon1.set(0.0);
			talon2.set(0.0);
		}
		
		*/
	}
		
		
	
}

