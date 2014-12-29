package irs1318_2014Fall_robot.Autonomous;

import irs1318_2014Fall_robot.Common.IOperatorComponent;

public class AutonomousOperator implements IOperatorComponent
{
    private IAutonomousTask[] autonomousTasks;
    private int currentTaskPosition;
    private IAutonomousTask currentTask;
    private AutonomousControlData controlData;

    public AutonomousOperator(IAutonomousTask[] autonomousTasks)
    {
        // switch to Queue when available to get rid of currentTaskPosition...
        this.autonomousTasks = autonomousTasks;
        this.currentTaskPosition = 0;
        this.currentTask = null;
        this.controlData = new AutonomousControlData(); 

        this.validateAutonomousTasks();
    }

    public void tick()
    {
        // check whether we should continue with the current task
        if (this.currentTask != null)
        {
            if (!this.currentTask.shouldContinue())
            {
                this.currentTask.apply(this.controlData);
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
            this.currentTask.start();
        }

        // run the current task, and then apply the result to the control data
        this.currentTask.run();
        this.currentTask.apply(this.controlData);
    }

    public void stop()
    {
        this.currentTask.stop();
        this.currentTask.apply(this.controlData);
    }

    public boolean getCollectorExtendButton()
    {
        return this.controlData.getCollectorExtend();
    }

    public boolean getCollectorRetractButton()
    {
        return this.controlData.getCollectorRetract();
    }

    public boolean getCollectorCollectButton()
    {
        return this.controlData.getCollectorCollect();
    }

    public boolean getCollectorExpelButton()
    {
        return this.controlData.getCollectorExpel();
    }

    public boolean getShooterAngle()
    {
        return this.controlData.getShooterAngle();
    }

    public int getShooterMode()
    {
        return this.controlData.getShooterMode();
    }

    public boolean getShooterShoot()
    {
        return this.controlData.getShooterShoot();
    }

    public double getDriveTrainXVelocity()
    {
        return this.controlData.getDriveTrainXVelocity();
    }

    public double getDriveTrainYVelocity()
    {
        return this.controlData.getDriveTrainYVelocity();
    }

    public boolean getDriveTrainSimpleModeButton()
    {
        return this.controlData.getDriveTrainSimpleMode();
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
