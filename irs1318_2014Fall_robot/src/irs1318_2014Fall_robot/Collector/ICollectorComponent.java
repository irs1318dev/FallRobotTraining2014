package irs1318_2014Fall_robot.Collector;

public interface ICollectorComponent
{
    /**
     * Read the limit switch
     * @return the current value of the collector limit switch
     */
    public boolean readLimitSwitch();

    /**
     * set the collector's settings
     * @param extend the collector
     * @param retract the collector
     * @param collectorPower the power level to set the collector
     */
    public void setCollector(boolean extend, boolean retract, double collectorPower);
}