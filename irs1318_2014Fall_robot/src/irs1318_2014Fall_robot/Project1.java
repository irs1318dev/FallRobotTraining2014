package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Project1 {
	Joystick joystick1;
	Joystick joystick2;
	Talon talonR;
	Talon talonL; 
	public static final int LEFT_TALON = 1;
	public static final int RIGHT_TALON = 2; 
	public void teleopInit() {
		joystick1 = new Joystick(1);
		joystick2 = new Joystick(2);
		talonR = new Talon(1,1);
		talonL = new Talon(1,1);
	}
	
public void teleopPeriodic() {
		if(joystick1.getY() > -.2 && joystick1.getY() < .2){
			talonR.set(0);
		}
		if(joystick2.getY() > -.2 && joystick2.getY() < .2){
			talonL.set(0);
		}
		if(joystick2.getY() < -.2 ){
			talonL.set(joystick2.getY()/2);
		}
		if(joystick1.getY() < -.2){
			talonL.set(joystick2.getY()/2); 
		}
		if(joystick2.getY() > .2){
			talonL.set(joystick2.getY()/2); 
		}
		if(joystick1.getY() > .2){
			talonL.set(joystick2.getY()/2); 
		}
	}


}
