package irs1318_2014Fall_robot.Compressor;

import irs1318_2014Fall_robot.Common.IController;

public class CompressorController implements IController
{
    private boolean isStarted;
    private CompressorComponent component;

    /**
     * Initializes a new CompressorController
     * @param component to control
     */
    public CompressorController(CompressorComponent component)
    {
        this.component = component;        
        this.isStarted = false;
    }

    /**
     * calculate the various outputs to use based on the inputs and apply them to the outputs for the relevant component
     */
    public void run()
    {
        if (!this.isStarted)
        {
            this.component.start();
            this.isStarted = true;
        }
    }

    /**
     * stop the relevant component
     */
    public void stop()
    {
        this.component.stop();
        this.isStarted = false;
    }
}
