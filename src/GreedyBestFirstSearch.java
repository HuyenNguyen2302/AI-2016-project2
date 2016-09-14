import java.util.ArrayList;
import java.util.List;

public class GreedyBestFirstSearch extends Algorithm {

	double time;
	double startingNum;
	double targetNum;
	List<Action> actions;
	List<Double> visited;
	int nSteps = 0;

	long startTime;

	GreedyNodeLinkedList nodeList = new GreedyNodeLinkedList();

	public GreedyBestFirstSearch(double time, double startingVal, double targetVal, List<Action> actions) {
		super(time, startingVal, targetVal, actions);

		this.time = time;
		this.startingNum = startingVal;
		this.targetNum = targetVal;
		this.actions = actions;

		visited = new ArrayList();
	}

	@Override
	public ItrDpStateNodeStack search() {
		startTime = System.currentTimeMillis();

		double bestHeuristic;
		Action startAction = new Action("+ 0");
		nodeList.add(new GreedyNode(startingNum, startAction, startingNum, 0 ));

		ItrDpStateNodeStack result = new ItrDpStateNodeStack();

		GreedyNode frontier = nodeList.get(0);
		while ((frontier.getCurrentState() != targetNum) &&
				((System.currentTimeMillis() - startTime) < (time * 1000))){
			frontier = nodeList.get(0);

			bestHeuristic = Integer.MAX_VALUE;
			//Find the one with the best heuristic
			for (int i = 0; i < nodeList.size(); i++) {

				if ((computeHeuristic(nodeList.get(i).getCurrentState()) < bestHeuristic)
						&& (!visited.contains(nodeList.get(i).getCurrentState()))){
					bestHeuristic = (computeHeuristic(nodeList.get(i).getCurrentState()));
					frontier = nodeList.get(i);
				}
			}
			ExpandNodeList(frontier);
		}

		timeSpent = System.currentTimeMillis() - startTime;
		error = Math.abs(frontier.getCurrentState() - targetNum);

		result.push(new StateNode(frontier.getCurrentState(),null));
		while (frontier.getDepth()>0) {
			double p = frontier.getPreviousState();
			Action a = null;
			for (int i = 0;i < actions.size();i++) {
				if (actions.get(i).getOperationResult(p) == frontier.getCurrentState()) {
					a = actions.get(i);
				}
			}

			StateNode s = new StateNode(frontier.getPreviousState(),a);
			result.push(s);


			for (int i =0;i<nodeList.size();i++){
				if (nodeList.get(i).getCurrentState() == frontier.getPreviousState()) {
					frontier = nodeList.get(i);
					break;
				}
			}

		}
		nSteps = result.size();
		return result;

	}

	public void ExpandNodeList( GreedyNode frontier) {

		//Find the one with the best heuristic
		visited.add(frontier.getCurrentState());
		for (int i = 0; i < actions.size();i++) {
			double result = actions.get(i).getOperationResult(frontier.getCurrentState());
			nodeList.add(new GreedyNode(result,actions.get(i),frontier.getCurrentState(),frontier.getDepth()+1));
			this.numOfNodesExpanded++;
			if (frontier.getDepth() > this.searchDepth) {
				this.searchDepth = frontier.getDepth();
			}
		}
	}

	private double computeHeuristic(double d) {
		return Math.abs(d - targetNum);
	}

	@Override
	public int getNumberOfSteps() {
		return nSteps - 1;
	}

}
