import java.util.List;
/**
 * A class that contains information about the problem
 * 
 * Goal of the problem:
 * Start with an initial number, use the smallest number of 
 * arithmetic operations to reach the target numeric value.  
 * 
 * @author yinglu
 *
 */
public class Problem {
	/**
	 * initial state
	 */
	double startingNum;
	/**
	 * target state
	 */
	double targetNum;
	/**
	 * A list of possible actions that can be performed at states
	 */
	List<Action> actions;


	public Problem(double startingVal, double targetVal, List<Action> actions){
		this.startingNum = startingVal;
		this.targetNum = targetVal;
		this.actions = actions;

	}

	/**
	 * check if is in goal state
	 * 
	 * @param numAtCurrentState
	 *            the number at current state
	 * @return true if in goal state
	 */
	public boolean reachGoal(int numAtCurrentState){
		return numAtCurrentState == targetNum;
	}
}
