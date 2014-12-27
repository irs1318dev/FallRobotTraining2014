package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.Timer;

/**
 * This class is a PID handler with a feed-forward handler that uses the
 * setpoint as input.
 * 
 * To use PID control:
 *      set the setpoint
 *      feed input values regularly
 *      use output values 
 * 
 * for reference:
 *      http://en.wikipedia.org/wiki/PID_controller
 *      http://en.wikipedia.org/wiki/Feed_forward_(control)
 * 
 * @author WRall (adapted from old code)
 */

public class PIDHandler
{
    // constants
    private static final double MinTimeStep = .001;
    private static final double MinOutput = -1.0; // $TODO: implement
    private static final double MaxOutput = 1.0; // $TODO: implement

    // instance constants
    private final double kp;        // proportion for proportional
    private final double ki;        // proportion for integral
    private final double kd;        // proportion for derivative
    private final double kf;        // proportion for feed-forward
    // private double kFade;  // $TODO: figure out Fade
    // private double kScale; // $TODO: figure out Scale

    // instance variables
    private double setpoint = 0.0;      // the input, desired value for
    private double measuredValue = 0.0; // the measured value for PID 
    private double integral = 0.0;      // integral of error data in memory
    private double derivative = 0.0;    // approximate slope of input.. units in / seconds
    private double dt = .001;           // amount of time we waited since our previous measurement
    private double prevTime = 0.0;      // the timestamp of our previous measurement 
    private double error = 0.0;         // the error (difference between setpoint and measured value)
    private double prevError = 0.0;     // the error during our previous measurement
    private double curTime = 0.0;       // the current timestamp
    private double output = 0.0;        // the output we wish to set after our calculation

    // other vars
    private Timer timer;

    /**
     * This constructor initializes the object and sets constants to affect gain
     * 
     * @param kp scalar for proportional component
     * @param ki scalar for integral component
     * @param kd scalar for derivative component
     * @param kf scalar for feed-forward control
     */
    public PIDHandler(double kp, double ki, double kd, double kf)
    {
        this.ki = ki;
        this.kd = kd;
        this.kp = kp;
        this.kf = kf;
        
        this.timer = new Timer();
        this.timer.start();
        this.prevTime = this.timer.get();
    }

    /**
     * measuredValue should be in the same unit as the setpoint.  this method should be
     * called in a loop and fed feedback data and setpoint changes
     * 
     * @param setpoint describes the goal value
     * @param measuredValue describes the measured value
     */
    public void calculate(double setpoint, double measuredValue)
    {
        this.setpoint = setpoint;
        this.measuredValue = measuredValue;

        // update dt
        this.curTime = this.timer.get();  
        this.dt = this.curTime - this.prevTime;

        // To prevent division by zero and over-aggressive measurement, output updates at a max of 1kHz
        if (this.dt >= PIDHandler.MinTimeStep)
        {
            this.prevTime = this.curTime;

            // calculate error
            // this.error = this.setpoint * this.kScale - this.measuredValue;
            this.error = this.setpoint - this.measuredValue;

            // calculate integral
            // this.integral *= this.kFade;
            this.integral += this.error * this.dt;

            // calculate derivative
            this.derivative = (this.error - this.prevError) / this.dt;

            // store error
            this.prevError = this.error;

            this.output =
                    this.kp * this.error +      // proportional
                    this.ki * this.integral +   // integral
                    this.kd * this.derivative + // derivative
                    this.kf * this.setpoint;    // feed-forward
        }
    }

    /**
     * this returns the output of the PID controller.  The goal of PID is for this value to eventually reach the setpoint
     * 
     * @return output value to be used
     */
    public double getOutput()
    {
        return this.output;
    }
}