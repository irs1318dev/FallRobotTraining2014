package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class ArcadeDriveHandler extends RobotComponentBase { //borrows from component base structure
	
	private static final int J_PORT = 1; //joystick port
	//right motor variables
	private static final int R_MOTOR_PORT = 1; //right motor port
	private static final int R_MOTOR_CHANNEL = 2; //right motor channel
	//left motor variables
	private static final int L_MOTOR_PORT = 1; //left motor port
	private static final int L_MOTOR_CHANNEL = 1; //left motor channel
	//speed and checking variables
	private static final double DEAD_ZONE = 0.35; //number that is used to limit control zone
	private static final double SPEED_COEFFICIENT = 0.75; //coefficient to prohibit full speed
	
	private DoubleSolenoid solenoid;

	
	private Talon intakeTalon;
	private Talon rTalon; //right motor / talon
	private Talon lTalon; //left motor / talon
	private Joystick controller; //joystick for control : controlled by user
	
	public void robotInit(){ /*sets up joystick and motors for use with the black box through 
							their previously specified channels and ports
							(basically the constructor for the robot)*/
		solenoid = new DoubleSolenoid(0, 0);
		controller = new Joystick(J_PORT);
		//initializes talons using predefined ports and channels
		intakeTalon = new Talon(1, 3);
		rTalon = new Talon(R_MOTOR_PORT, R_MOTOR_CHANNEL);
		lTalon = new Talon(L_MOTOR_PORT, L_MOTOR_CHANNEL);
	}
	public void teleopPeriodic(){//infinite loop
		double hyp = Math.sqrt((controller.getX() * controller.getX()) + (controller.getY() * controller.getY())); //finds hypotenuse for later calculations
		int y_sign = (int) (controller.getY() / Math.abs(controller.getY()));/*finds the sign of the y coordinate, 
																			-1 or 1, depending if on top or bottom half*/
		
		
		int x_sign = (int) (controller.getX() / Math.abs(controller.getX()));/*finds the sign of the x coordinate,
																			-1 or 1, depending if on left or right half*/
		
		if(controller.getRawButton(5) == true){
			intakeTalon.set(-1);
		}
		else if(controller.getRawButton(3) == true){
			intakeTalon.set(1);
		}	
		else
		{
			intakeTalon.set(0);
		}
		if(controller.getRawButton(1) == true){
			solenoid.set(Value.kForward);
		}
		else{
			solenoid.set(Value.kReverse);
			
		}
		if(Math.abs(hyp) <= DEAD_ZONE){//checks for dead zone and sets speed to zero if within dead zone
			rTalon.set(0);
			lTalon.set(0);
		}
		else{//sets speed accordingly, based off of hypotenuse length
			hyp *= SPEED_COEFFICIENT;

			if(x_sign > 0){// sets talon speeds if controller is in the right half 
				rTalon.set((y_sign * Math.abs(hyp - Math.abs(controller.getX()))));
				lTalon.set(-(y_sign * hyp));
			}
			else{ // sets talon speeds if controller is in the left half
				rTalon.set((y_sign * hyp));
				lTalon.set(-(y_sign * Math.abs(hyp - Math.abs(controller.getX()))));
			}
		}
	}
}
