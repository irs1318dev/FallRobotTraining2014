package irs1318_2014Fall_robot.Autonomous;

public interface IAutonomousTask
{
    /**
     * Begin the current task
     */
    public void begin();

    /**
     * Run an iteration of the current task and apply any control changes 
     * @param data to which we should apply updated settings
     */
    public void run(AutonomousControlData data);

    /**
     * Cancel the current task and clear control changes
     * @param data to which we should clear any updated control settings
     */
    public void cancel(AutonomousControlData data);

    /**
     * End the current task and reset control changes appropriately
     * @param data to which we should apply updated settings
     */
    public void end(AutonomousControlData data);

    /**
     * Checks whether we should continue processing this task or whether it should end
     * @return true if we should continue, otherwise false
     */
    public boolean shouldContinue();
}
