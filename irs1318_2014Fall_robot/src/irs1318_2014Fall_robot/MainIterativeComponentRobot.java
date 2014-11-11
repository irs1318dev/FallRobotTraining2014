package irs1318_2014Fall_robot;

public class MainIterativeComponentRobot extends IterativeComponentRobot {

	public BotVector currentRobotComponents() {
		BotVector v = new BotVector();
		v.add(new BasicTwoStickDriveTrainComponent());
		return v;
	}
	
}

