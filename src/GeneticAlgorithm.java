import java.util.List;
import java.util.Random;

public class GeneticAlgorithm extends Algorithm {
	public static final int CUT_POINT_INDEX = 3;
	public static final int MUTATION_PROBABILITY = 50;
	public Individual bestIndividual;

	public GeneticAlgorithm(double time, double startingVal, double targetVal, List<Action> actions) {
		super(time, startingVal, targetVal, actions);
		this.bestIndividual = null;
	}

	@Override
	public StateNodeList search() {

		return null;
	}
	public void geneticSearch(){
		Population population = new Population(this.problem.actions.size());
		population.initialize();
		this.bestIndividual = geneticAlgorithm(population);
	}

	public Individual geneticAlgorithm(Population population){
		while(!fit(population) && population.getCurrentSize() > 1){
			Population new_population = new Population(this.problem.actions.size());
			for(int i = 0; i < new_population.initialSize; i++){
				Individual x = randomSelection(population);
				Individual y = randomSelection(population);
				Individual child = reproduce(x,y);
				if(mutationHappens()){
					child = mutate(child);
				}
				new_population.add(child);
			}
			population = new_population;
		}
		return findBest(population);
	}

	private Individual findBest(Population population) {
		double bestVal = 999;
		Individual bestIndividual = null;
		for(int i = 0; i < population.initialSize; i++){
			Individual currIndividual = population.individualSet.get(i);
			double currVal = evaluateState(currIndividual);
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

	private boolean fit(Population population) {
		for(int i = 0; i < population.initialSize; i++){
			if(Math.abs(evaluateState(population.individualSet.get(i)) - this.problem.targetNum) < 5){
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

	private Individual randomSelection(Population population) {
		Random rand = new Random();
		int selectIndex = rand.nextInt(population.initialSize);
		return population.individualSet.get(selectIndex);
	}

	public double evaluateState(Individual individual){
		double result = this.problem.startingNum;
		for(int i = 0; i < Individual.MAX_DIGITS_LENGTH; i++){
			result += individual.digits[i];
		}
		return result;
	}

	public void printBestIndividual(){
		if(this.bestIndividual == null){
			return;
		}
		for(int i = 0; i < Individual.MAX_DIGITS_LENGTH; i++){
			int actionIndex = this.bestIndividual.digits[i];
			if(actionIndex > 0){
				System.out.println(this.problem.actions.get(actionIndex).printOperation());
			}
		}
		
		System.out.println(evaluateState(this.bestIndividual));
	}

}
