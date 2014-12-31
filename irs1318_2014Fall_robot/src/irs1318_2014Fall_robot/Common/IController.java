package irs1318_2014Fall_robot.Common;

public interface IController
{
    /**
     * calculate the various outputs to use based on the inputs and apply them to the outputs for the relevant component
     */
    public void run();

    /**
     * stop the relevant component
     */
    public void stop();
}
