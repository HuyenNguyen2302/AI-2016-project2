/**
 * Class that holds information about an action that can be operated on a state
 * @author yinglu
 *
 */
public class Action {
	/**
	 * operator
	 */
	String operatorStr = "";
	/**
	 * the value that should be operated onto current state
	 */
	double operationValue;

	public Action(String input) {
		String[] str = input.split(" ");
		this.operatorStr = str[0];
		this.operationValue = Integer.parseInt(str[1]);
	}

	public double getOperationValue() {
		return this.operationValue;
	}

	/**
	 * given a input number, calculate the result of executing the operation stored in this current node
	 * @param d the input number
	 * @return the result of the operation
	 */
	public double getOperationResult(double d){
		switch(operatorStr){
		case "+":
			return d + this.operationValue;
		case "-":
			return d - this.operationValue;
		case "*":
			return d * this.operationValue;
		case "/":
			return d / this.operationValue;
		case "^":
			return Math.pow(d, this.operationValue);
		default:
			return d;
		}
	}
	/** given a input number, calculate the result of executing the operation stored in this current node
	 * @param d the input number
	 * @return the result of the operation
	 */
	public String printOperation() {
		return operatorStr.concat(" "+Double.toString(operationValue) );
	}
}
