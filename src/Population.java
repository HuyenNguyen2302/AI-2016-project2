import java.util.ArrayList;
import java.util.Random;

public class Population{
	public static final int MAX_POPULATION_SIZE = 10;
	
	ArrayList<Individual> individualSet;
	int numOfAvailableActions;
	int initialSize;
	
	public Population(int numOfAvailableActions){
		this.numOfAvailableActions = numOfAvailableActions;
		
		Random rand = new Random(); 
		this.initialSize = rand.nextInt(MAX_POPULATION_SIZE)+1; 
		individualSet = new ArrayList<Individual>(this.initialSize);
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
	
	public int getCurrentSize(){
		return this.individualSet.size();
	}

}
