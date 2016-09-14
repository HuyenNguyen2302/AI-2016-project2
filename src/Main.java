import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Main {

	public static void main(String[] args) {

		String filePath = args[0]; //get file path from command line
		//initialize
		String algorithmType = "", line;
		double startingVal = 0, targetVal = 0;
		double time = 0;
		List<Action> actions = new ArrayList<Action>(1);
		Action defaultAction = new Action("+ 0");
		actions.add(defaultAction);
		
		//read and store
		try{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			algorithmType = br.readLine();
			startingVal = Double.parseDouble(br.readLine());
			targetVal = Double.parseDouble(br.readLine());
			time = Double.parseDouble(br.readLine());
			while((line = br.readLine()) != null){
				actions.add(new Action(line));
			}
		}
		catch (IOException e){
			System.out.println("Error reading input file: "+e.getMessage());
		}

		//print for testing purpose
		//		printInputVariables(filePath, algorithmType, startingVal, targetVal, time);

		//select different search method based on input file
		//		Algorithm sm = null;
		switch(algorithmType){
		case "greedy":
			GreedyBestFirstSearch sm = new GreedyBestFirstSearch(time, startingVal, targetVal, actions);
			ItrDpStateNodeStack searchResult = sm.search();
			searchResult.printReverseList();
			printSearchResult(sm);
			break;
		case "genetic":
			GeneticAlgorithm ga = new GeneticAlgorithm(time, startingVal, targetVal, actions);
			ga.geneticSearch();		
			ga.printBestIndividual();
			break;
		}
		//		ItrDpStateNodeStack searchResult = sm.search();
		//
		//		System.out.println("search result is as follows");
		//		switch (algorithmType) {
		//			case "iterative":
		//				searchResult.printList();
		//				break;
		//			case "greedy" :
		//				searchResult.printReverseList();
		//		}
		//searchResult.printList();
		//		printSearchResult(sm);

	}

	private static void printInputVariables(String filePath, String algorithmType, int startingVal, int targetVal, double time) {
		System.out.println(filePath);
		System.out.println("algorithm type: " + algorithmType);
		System.out.println("starting value: " + startingVal);
		System.out.println("target value: " + targetVal);
		System.out.println("time limit: " + time);
	}

	private static void printSearchResult(Algorithm sm){
		System.out.println("Error: " + sm.error);
		System.out.println("Number of steps required: "+ sm.getNumberOfSteps());
		System.out.println("Nodes expanded: " + sm.getNumOfNodesExpanded());
		System.out.println("Search depth reached in this search: " + sm.getSearchDepth());
		System.out.println("Time spent in this search: " + sm.getTimeSpent() + " ms");
	}
}
