package buvosnegyzet;

public class Solver {
    static int callCount = 0;

    public static void main(String[] args) {
        
        Matrix matrix = new Matrix();
        
        findSolutions(matrix);
        
        System.out.println("End");
        
    }

    private static void findSolutions(Matrix currentState) {
        callCount++;
        
        if (callCount > 10) return;
        
        if (currentState == null) {
            return;
        }
        
        System.out.println(currentState + " - level " + currentState.getCount());
        
        
        if (currentState.isEndState() && currentState.isCorrect()) {
            System.out.println(currentState.toString());
            return;
        }
        
        Matrix nextMatrixState = currentState;
        while (nextMatrixState != null) {
            findSolutions(nextMatrixState.getNextLevelState());
            nextMatrixState = nextMatrixState.getNextState();
        }
        
    }
    
    private static void testNextState() {
        PatternMatrix patternMatrix = new PatternMatrix("X1110X1100X0000X");
        System.out.println(patternMatrix);
        System.out.println(patternMatrix.getNextState());
        System.out.println(patternMatrix.getNextLevelState());
    }

}
