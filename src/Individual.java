import java.util.Random;

public class Individual {
	public static final int MAX_DIGITS_LENGTH = 30;
	public static final int INIT = 1;
	int[] digits;
	
	public Individual(){
		this.digits = new int[MAX_DIGITS_LENGTH];
		for(int i = 0; i < MAX_DIGITS_LENGTH; i++){
			digits[i] = 0; //default to 0
		}
	}
	
	public void generate(int operationNum){
		int digits_length = randomNum(MAX_DIGITS_LENGTH)+1;
		for(int i = 0; i < digits_length; i++){
			this.digits[i] = randomNum(operationNum);
		}
	}

	private int randomNum(int bound) {
		Random rand = new Random();
		return rand.nextInt(bound);
	}
	
	public void print(double startingNum){
		String digitString = "";
		for(int i = 0; i < MAX_DIGITS_LENGTH; i++){
			digitString = digitString.concat(Integer.toString(digits[i]));
		}
		System.out.println(digitString + Double.toString(evaluateState(startingNum)));
	}
	public double evaluateState(double startingNum){
		double result = startingNum;
		for(int i = 0; i < Individual.MAX_DIGITS_LENGTH; i++){
			result += this.digits[i];
		}
		return result;
	}
}
