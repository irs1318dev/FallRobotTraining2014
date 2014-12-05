package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Simplecompnet extends RobotComponentBase {
	private Talon rtalon;
	private Joystick rjoystick;
	private Talon ltalon;
	private Joystick ljoystick;



	public void teleopperint() {
			rtalon = new Talon(1,1);
			rjoystick =new Joystick(2);
			ltalon = new Talon(1,1);
			ljoystick = new Joystick(2);
	}
					
					
	public void TeleopPeriodice() {
		if (rjoystick.getY()>0.5 && rjoystick.getY()<0.75){
			rtalon.set(.5);
		} else if(rjoystick.getY()>=0.75) {	
			rtalon.set(.75);
		} else if (rjoystick.getY()<-0.5 && rjoystick.getY()>-0.75){
			rtalon.set(-.5);
		} else if(rjoystick.getY()<=-0.75) {	
			rtalon.set(-.75);
		} else {
			rtalon.set(0);
		}
		if (ljoystick.getY()>0.5 && ljoystick.getY()<0.75){
			ltalon.set(.5);
		} else if(ljoystick.getY()>=0.75) {	
			ltalon.set(.75);
		} else if (ljoystick.getY()<-0.5 && ljoystick.getY()>-0.75){
			ltalon.set(-.5);
		} else if(ljoystick.getY()<=-0.75) {	
			ltalon.set(-.75);
		} else {
			ltalon.set(0);
		}
		if (rjoystick.getY() > .5)
		{
			rtalon.set(.5);
			ltalon.set(.5);
		}
		else if (rjoystick.getY() < -.5)
		{
			rtalon.set(-.5);
			ltalon.set(-.5);
		}
		else if (rjoystick.getX() < -.5)
		{
			rtalon.set(-.5);
			ltalon.set(.5);
		}
		else if ( rjoystick.getX() > .5)
		{
			rtalon.set(.5);
			ltalon.set(-.5);
		}
		else 
		{
			rtalon.set(0);
			ltalon.set(0);
		}
		rjoystick.getRawButton(60);
	}	
}