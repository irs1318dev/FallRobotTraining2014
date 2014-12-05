package irs1318_2014Fall_robot;

public class MainIterativeComponentRobot extends IterativeComponentRobot {

	public BotVector currentRobotComponents() {
		BotVector vector = new BotVector();
		vector.add(new Simplecompnet());
		return vector;
	}
	
}

