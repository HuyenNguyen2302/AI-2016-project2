/**
 * Created by yx on 9/9/16.
 */
public class GreedyNode {
    double currentState;
    Action previousAction;
    double previousState;
    int depth;

    public GreedyNode(double startingNum, Action previousAction, double startingNum2, int depth) {
        this.currentState = startingNum;
        this.previousAction = previousAction;
        this.previousState = startingNum2;
        this.depth = depth;
    }

    double getCurrentState() {
        return this.currentState;
    }

    double getPreviousState() {
        return this.previousState;
    }

    Action getPreviousAction() {
        return this.previousAction;
    }

    int getDepth() {
        return this.depth;
    }
}
