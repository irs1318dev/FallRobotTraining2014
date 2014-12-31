package irs1318_2014Fall_robot.Autonomous;

import irs1318_2014Fall_robot.Common.IOperatorComponent;

public class AutonomousOperator implements IOperatorComponent
{
    private IAutonomousTask[] autonomousTasks;
    private int currentTaskPosition;
    private IAutonomousTask currentTask;
    private AutonomousControlData controlData;

    /**
     * Initializes a new AutonomousOperator
     * @param autonomousTasks to execute as a part of this operator
     */
    public AutonomousOperator(IAutonomousTask[] autonomousTasks)
    {
        // switch to Queue when available to get rid of currentTaskPosition...
        this.autonomousTasks = autonomousTasks;
        this.currentTaskPosition = 0;
        this.currentTask = null;
        this.controlData = new AutonomousControlData(); 

        this.validateAutonomousTasks();
    }

    /**
     * Tell the operator component that some time has passed
     */
    public void tick()
    {
        // check whether we should continue with the current task
        if (this.currentTask != null)
        {
            if (!this.currentTask.shouldContinue())
            {
                this.currentTask.end(this.controlData);
                this.currentTask = null;
                this.currentTaskPosition++;
            }
        }

        // if there's no current task, find the next one and start it (if any)
        if (this.currentTask == null)
        {
            if (this.currentTaskPosition >= this.autonomousTasks.length)
            {
                return;
            }

            this.currentTask = this.autonomousTasks[this.currentTaskPosition];
            this.currentTask.begin();
        }

        // run the current task and apply the result to the control data
        this.currentTask.run(this.controlData);
    }

    /**
     * Tell the operator component that operation is stopping
     */
    public void stop()
    {
        this.currentTask.cancel(this.controlData);
    }

    /**
     * Get a value indicating whether we should extend the collector 
     * @return true if we should extend, otherwise false
     */
    public boolean getCollectorExtendButton()
    {
        return this.controlData.getCollectorExtend();
    }

    /**
     * Get a value indicating whether we should retract the collector 
     * @return true if we should retract, otherwise false
     */
    public boolean getCollectorRetractButton()
    {
        return this.controlData.getCollectorRetract();
    }

    /**
     * Get a value indicating whether we should collect a ball using the collector 
     * @return true if we should collect, otherwise false
     */
    public boolean getCollectorCollectButton()
    {
        return this.controlData.getCollectorCollect();
    }

    /**
     * Get a value indicating whether we should expel a ball using the collector 
     * @return true if we should expel, otherwise false
     */
    public boolean getCollectorExpelButton()
    {
        return this.controlData.getCollectorExpel();
    }

    /**
     * Get a value indicating whether we should adjust the shooter angle 
     * @return true if we should move in, otherwise false
     */
    public boolean getShooterAngle()
    {
        return this.controlData.getShooterAngle();
    }

    /**
     * Get a value indicating the shooter's current mode 
     * @return a value indicating the number of pistons to use in the shot
     */
    public int getShooterMode()
    {
        return this.controlData.getShooterMode();
    }

    /**
     * Get a value indicating whether we should attempt to shoot
     * @return true if we should be shooting, otherwise false
     */
    public boolean getShooterShoot()
    {
        return this.controlData.getShooterShoot();
    }

    /**
     * Get a value indicating the desired drive train X Velocity 
     * @return value between -1.0 and 1.0 (percentage of max right turn velocity)
     */
    public double getDriveTrainXVelocity()
    {
        return this.controlData.getDriveTrainXVelocity();
    }

    /**
     * Get a value indicating the desired drive train Y velocity (turn amount) 
     * @return value between -1.0 and 1.0 (percentage of max forward velocity)
     */
    public double getDriveTrainYVelocity()
    {
        return this.controlData.getDriveTrainYVelocity();
    }

    /**
     * Get a value indicating whether we should be using the drive train in simple mode 
     * @return true if we should be in simple mode, otherwise false
     */
    public boolean getDriveTrainSimpleModeButton()
    {
        return this.controlData.getDriveTrainSimpleMode();
    }

    /**
     * Get a value indicating the desired drive train left position for positional mode
     * @return position
     */
    public double getDriveTrainLeftPosition()
    {
        return this.controlData.getDriveTrainLeftPosition();
    }

    /**
     * Get a value indicating the desired drive train right position for positional mode
     * @return position
     */
    public double getDriveTrainRightPosition()
    {
        return this.controlData.getDriveTrainRightPosition();
    }

    /**
     * Get a value indicating whether the drive train should be in position or velocity mode
     * @return true if position mode, false if velocity mode
     */
    public boolean getDriveTrainPositionMode()
    {
        return this.controlData.getDriveTrainPositionMode();
    }

    private void validateAutonomousTasks()
    {
        if (this.autonomousTasks == null)
        {
            throw new RuntimeException("autonomous tasks are null!");
        }

        for (int i = 0; i < this.autonomousTasks.length; i++)
        {
            if (this.autonomousTasks[i] == null)
            {
                throw new RuntimeException("null entry in autonomous tasks list!");
            }
        }
    }
}
