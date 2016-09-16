import java.util.List;
import java.util.Random;

public class GeneticAlgorithm extends Algorithm {
	public static final int CUT_POINT_INDEX = 3;
	public static final int MUTATION_PROBABILITY = 50;
	public Individual bestIndividual;
	public Population population;

	public GeneticAlgorithm(double time, double startingVal, double targetVal, List<Action> actions) {
		super(time, startingVal, targetVal, actions);
		this.bestIndividual = null;
		this.population = new Population(this.problem.actions.size());
		population.initialize();
//		population.print(this.problem.startingNum);
	}

	@Override
	public StateNodeList search() {
		return null;
	}

	public void geneticAlgorithm(){
		Population currPopulation = this.population;
		while(!fit(currPopulation) && currPopulation.getCurrentSize() > 1){
			Population new_population = new Population(this.problem.actions.size());
			for(int i = 0; i < new_population.initialSize; i++){
				Individual x = randomSelection(currPopulation);
				Individual y = randomSelection(currPopulation);
				Individual child = reproduce(x,y);
				if(mutationHappens()){
					child = mutate(child);
				}
				new_population.add(child);
			}
			currPopulation = new_population;
		}
		this.population = currPopulation;
		this.bestIndividual =  findBest(currPopulation);
		this.bestIndividual.print(this.problem.startingNum);
	}

	private Individual findBest(Population population) {
		population.print(this.problem.startingNum);
		double bestVal = 999.0;
		Individual bestIndividual = null;
		for(int i = 0; i < population.individualSet.size(); i++){
			Individual currIndividual = population.individualSet.get(i);
			double currVal = currIndividual.evaluateState(this.problem.startingNum);
			double difference = Math.abs(currVal - this.problem.targetNum);
			if(difference < bestVal){
				bestVal = currVal;
				bestIndividual = currIndividual;
			}
		}
		return bestIndividual;
	}

	private Individual mutate(Individual child) {
		Random rand = new Random();
		int mutate_index = rand.nextInt(Individual.MAX_DIGITS_LENGTH);
		child.digits[mutate_index] = rand.nextInt(this.problem.actions.size());
		return child;
	}

	private boolean fit(Population ppl) {
		for(int i = 0; i < ppl.initialSize; i++){
			if(Math.abs(ppl.individualSet.get(i).evaluateState(this.problem.startingNum) - this.problem.targetNum) < 5){
				return true;
			}
		}
		return false;
	}

	private Individual reproduce(Individual x, Individual y) {
		Individual new_child = new Individual();
		for (int i = 0; i < CUT_POINT_INDEX; i++){
			new_child.digits[i] = x.digits[i];
		}
		for (int i = CUT_POINT_INDEX; i < Individual.MAX_DIGITS_LENGTH; i++){
			new_child.digits[i] = y.digits[i];
		}
		return new_child;
	}

	private boolean mutationHappens() {
		Random rand = new Random();
		int randNum = rand.nextInt(100);
		return randNum < MUTATION_PROBABILITY;
	}

	private Individual randomSelection(Population ppl) {
		Random rand = new Random();
		int selectIndex = rand.nextInt(ppl.initialSize);
		return ppl.individualSet.get(selectIndex);
	}

	public void printBestIndividual(){
		if(this.bestIndividual == null){
			return;
		}
		double result = this.problem.startingNum;
		for(int i = 0; i < Individual.MAX_DIGITS_LENGTH; i++){
			int actionIndex = this.bestIndividual.digits[i];
			if(actionIndex > 0){
				Action currAction = this.problem.actions.get(actionIndex);
				result = currAction.getOperationResult(result);
				System.out.println(result + " " + currAction.printOperation() + " = "+result);
			}
		}
		this.error = Math.abs(result - this.problem.startingNum);
	}

}
