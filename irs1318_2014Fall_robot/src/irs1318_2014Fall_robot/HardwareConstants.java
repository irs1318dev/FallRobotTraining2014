package irs1318_2014Fall_robot;

/**
 * All constants describing the physical structure of the robot (distances and sizes of things).
 * 
 * @author Will
 * 
 */
public class HardwareConstants
{
    public static final double DRIVETRAIN_LEFT_ENCODER_PULSES_PER_REVOLUTION = 1.0; // TODO: validate this number
    public static final double DRIVETRAIN_LEFT_WHEEL_DIAMETER = 6.0 * 2.54; // (in centimeters)
    public static final double DRIVETRAIN_LEFT_PULSE_DISTANCE = Math.PI * HardwareConstants.DRIVETRAIN_LEFT_WHEEL_DIAMETER / HardwareConstants.DRIVETRAIN_LEFT_ENCODER_PULSES_PER_REVOLUTION;
    
    public static final double DRIVETRAIN_RIGHT_ENCODER_PULSES_PER_REVOLUTION = 1.0; // TODO: validate this number
    public static final double DRIVETRAIN_RIGHT_WHEEL_DIAMETER = 6.0 * 2.54; // (in centimeters)
    public static final double DRIVETRAIN_RIGHT_PULSE_DISTANCE = Math.PI * HardwareConstants.DRIVETRAIN_LEFT_WHEEL_DIAMETER / HardwareConstants.DRIVETRAIN_RIGHT_ENCODER_PULSES_PER_REVOLUTION;
    
    public static final double DRIVETRAIN_WHEEL_SEPARATION_DISTANCE = 16.33 * 2.54; // (in centimeters)
}
