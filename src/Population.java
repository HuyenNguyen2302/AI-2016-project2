import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Population{
	public static final int MAX_POPULATION_SIZE = 10;
	
	ArrayList<Individual> individualSet;
	int numOfAvailableActions;
	int initialSize = 5;
	
	public Population(int numOfAvailableActions){
		this.numOfAvailableActions = numOfAvailableActions;
		
		Random rand = new Random(); 
//		this.initialSize = rand.nextInt(MAX_POPULATION_SIZE)+1;
		this.initialSize = 5;
		individualSet = new ArrayList<Individual>(this.initialSize);
	}

	public int getInitialSize() {
		return this.initialSize;
	}

	public int getCurrentSize(){
		return this.individualSet.size();
	}

	public ArrayList<Individual> getIndividualSet() {
		return this.individualSet;
	}

	public void initialize(){
		for(int i = 0; i < this.initialSize; i++){
			Individual temp_individual = new Individual();
			temp_individual.generate(this.numOfAvailableActions);
			individualSet.add(temp_individual);
		}
	}

	public void add(Individual child) {
		this.individualSet.add(child);	
	}

	public void kill(Individual individual) {
		this.individualSet.remove(individual);
	}

	
	public void print(double startingNum){
		Iterator<Individual> individualItr = this.individualSet.iterator();
		while(individualItr.hasNext()){
			Individual currIndividual = individualItr.next();
			currIndividual.print(startingNum);
		}
	}

}
