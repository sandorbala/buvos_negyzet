package buvosnegyzet;

import java.util.HashSet;
import java.util.Set;

public class Solver {
	static int solutionCounter = 1;
	static Set<String> odditySet = new HashSet<String>();

	public static void main(String[] args) {
    	Matrix matrix = new Matrix();
        
        findSolutions(matrix);
        
        printOdditySet();
        
        System.out.println("End");
    }

	private static void findSolutions(Matrix currentState) {        
		if (currentState == null) {
		    return;
		}
        
        if (currentState.isEndState() && currentState.isCorrect()) {
        	System.out.println("Solution #" + solutionCounter);
            System.out.println(currentState.toString());
            odditySet.add(currentState.getOddityString());
            solutionCounter++;
            return;
        }
        
        Matrix nextMatrixState = currentState;
        while (nextMatrixState != null) {
            // System.out.println(nextMatrixState);
        	if (nextMatrixState.isCorrect()) {
        		findSolutions(nextMatrixState.getNextLevelState());
        	}
            nextMatrixState = nextMatrixState.getNextState();
        }
    }
	

    private static void printOdditySet() {
		// TODO
		
	}

}
