package irs1318_2014Fall_robot.Autonomous;

import irs1318_2014Fall_robot.Common.IOperatorComponent;

public class AutonomousOperator implements IOperatorComponent
{
    private IAutonomousTask[] autonomousTasks;
    private int currentTaskPosition;
    private IAutonomousTask currentTask;

    public AutonomousOperator(IAutonomousTask[] autonomousTasks)
    {
        // switch to Queue when available to get rid of currentTaskPosition...
        this.autonomousTasks = autonomousTasks;
        this.currentTaskPosition = 0;
        this.currentTask = null;

        this.validateAutonomousTasks();
    }

    public void tick()
    {
        // check whether we should continue with the current task
        if (this.currentTask != null)
        {
            if (!this.currentTask.shouldContinue())
            {
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

        // run the current task
        this.currentTask.run();
    }

    public void stop()
    {
        this.currentTask.stop();
    }

    public boolean getCollectorExtendButton()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean getCollectorRetractButton()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean getCollectorCollectButton()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean getCollectorExpelButton()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean getShooterAngle()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public int getShooterMode()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean getShooterShoot()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public double getDriveTrainXAxis()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public double getDriveTrainYAxis()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean getDriveTrainSimpleModeButton()
    {
        // TODO Auto-generated method stub
        return false;
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
