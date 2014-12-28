package irs1318_2014Fall_robot.Collector;

public interface ICollectorComponent
{
    public boolean readLimitSwitch();
    public void setCollector(boolean extend, boolean retract, double collectorPower);
}
