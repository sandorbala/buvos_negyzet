package buvosnegyzet;

public class PatternFinder {
    static int callCount = 0;

    public static void main(String[] args) {
        
        PatternMatrix patternMatrix = new PatternMatrix("X1000X0000X0000X");
        
        findSolutions(patternMatrix);
        
        System.out.println("End");
        
    }

    private static void findSolutions(PatternMatrix currentState) {
        callCount++;
        
        if (currentState == null) {
            return;
        }
        
        if (currentState.isEndState() && currentState.isCorrect()) {
            System.out.println(currentState.toString());
            return;
        }
        
        PatternMatrix nextMatrixState = currentState;
        while (nextMatrixState != null) {
            findSolutions(nextMatrixState.getNextLevelState());
            nextMatrixState = nextMatrixState.getNextState();
        }
        
    }

}
