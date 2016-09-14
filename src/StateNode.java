/**
 * A class to denote states as nodes
 * 
 * @author yinglu
 *
 */
public class StateNode {
	
	/**
	 * current state value
	 */
	double currentState;

	/**
	 * the action performed at this state after expanded
	 */
	Action action;


	public StateNode(double d, Action action) {
		this.currentState = d;
		this.action = action;
	}
	
	/**
	 * get current state
	 * @return current state value
	 */
	public double getCurrentState(){
		return this.currentState;
	}
	
	/**
	 * return the resulting state from operating this state with this action
	 * @return operation result
	 */
	public double getChildState(){
		return action.getOperationResult(currentState);
	}

	/**
	 * print the operation performed in this node
	 * @return String of the operation
	 */
	public String printNode(){
		if(action == null){
			return Double.toString(this.currentState);
		}
		return Double.toString(this.currentState) + " " + action.operatorStr + " " + Double.toString(action.operationValue) + " = " + action.getOperationResult(this.currentState);
	}
}
