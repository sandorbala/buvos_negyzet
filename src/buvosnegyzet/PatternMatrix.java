package buvosnegyzet;

public class PatternMatrix {
    private int[][] theMatrix = new int[4][4];
    private Integer lastX, lastY;
    private int count;
    
    public PatternMatrix() {
        this("X0000X0000X0000X");
    }

    /*
     * data : "X0001X0000X0000X"
     * 
     */
    public PatternMatrix(String data) {
        if (data.length() != 16) {
            throw new RuntimeException("16 chars is expected");
        }
        
        for (int rowIdx=0; rowIdx<4; rowIdx++) {
            for (int colIdx=0; colIdx<4; colIdx++) {
                if (colIdx==rowIdx) {
                    theMatrix[colIdx][rowIdx] =  0;
                } else {
                    int x = Integer.parseInt(data.substring(rowIdx*4+colIdx, rowIdx*4+colIdx+1));
                    theMatrix[colIdx][rowIdx] = x;
                    if (x > 0) {
                        count++;
                        lastX = colIdx;
                        lastY = rowIdx;
                    }
                }
            }
        }
    }
    
    private PatternMatrix(PatternMatrix baseMatrix, int newX, int newY) {
        for (int rowIdx=0; rowIdx<4; rowIdx++) {
            for (int colIdx=0; colIdx<4; colIdx++) {
                int x = colIdx==newX && rowIdx==newY ? 1 : baseMatrix.theMatrix[colIdx][rowIdx];
                theMatrix[colIdx][rowIdx] = x;
                if (x > 0) {
                    count++;
                    lastX = colIdx;
                    lastY = rowIdx;
                }
            }
        }
    }

    private PatternMatrix(PatternMatrix baseMatrix, int newX, int newY, int oldX, int oldY) {
        for (int rowIdx=0; rowIdx<4; rowIdx++) {
            for (int colIdx=0; colIdx<4; colIdx++) {
                int x = colIdx==newX && rowIdx==newY ? 1 : colIdx==oldX && rowIdx==oldY ? 0 : baseMatrix.theMatrix[colIdx][rowIdx];
                theMatrix[colIdx][rowIdx] = x;
                if (x > 0) {
                    count++;
                    lastX = colIdx;
                    lastY = rowIdx;
                }
            }
        }
    }
    
    public int getCount() {
        return count; 
    }
    
    public boolean isEndState() {
        return count == 6;
    }

    public boolean isCorrect() {
        if (count == 6) {
            int sum = getRowSum(0);
            if (sum == 1 || sum == 3) return false;   // 19
            sum = getRowSum(1);
            if (sum == 0 || sum == 2) return false;   // 22
            sum = getRowSum(2);
            if (sum == 0 || sum == 2) return false;   // 20
            sum = getRowSum(3);
            if (sum == 1 || sum == 3) return false;   // 17
            
            sum = getColumnSum(0);
            if (sum == 0 || sum == 2) return false;   // 20
            sum = getColumnSum(1);
            if (sum == 1 || sum == 3) return false;   // 19
            sum = getColumnSum(2);
            if (sum == 1 || sum == 3) return false;   // 17
            sum = getColumnSum(3);
            if (sum == 0 || sum == 2) return false;   // 22
        }
                
        return true;
    }

    public PatternMatrix getNextState() {
        int newX, newY;
        if (isEndState()) {
            // throw new RuntimeException("No next state possible");
            return null;
        }
        
        if (lastX == 3) {
            newX = 0;
            newY = lastY + 1;
        } else {
            newX = lastX + 1;
            newY = lastY;
        }
        
        if (newX == newY) {
            newX++;
        }
        
        if (newX > 3 || newY > 3) {
            //throw new RuntimeException("Invalid next place : "+newX+" - "+newY);
            return null;
        }
        
        // System.out.println(lastX + " " + lastY + " " + newX + " " + newY);      
        
        return new PatternMatrix(this, newX, newY, lastX, lastY);
    }
    
    public PatternMatrix getNextLevelState() {
        int newX, newY;
        if (isEndState()) {
            // throw new RuntimeException("No next state possible");
            return null;
        }
        
        if (lastX == 3) {
            newX = 0;
            newY = lastY + 1;
        } else {
            newX = lastX + 1;
            newY = lastY;
        }
        
        if (newX == newY) {
            newX++;
        }
        
        if (newX > 3 || newY > 3) {
            //throw new RuntimeException("Invalid next place : "+newX+" - "+newY);
            return null;
        }
        
        // System.out.println(lastX + " " + lastY + " " + newX + " " + newY);      
        
        return new PatternMatrix(this, newX, newY);
    }

    private int getRowSum(int rowIdx) {
        int sum = 0;
        for (int i=0; i<4; i++) {
            sum += theMatrix[i][rowIdx];
        }
        return sum;
    }

    private int getColumnSum(int columnIdx) {
        int sum = 0;
        for (int i=0; i<4; i++) {
            sum += theMatrix[columnIdx][i];
        }
        return sum;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int rowIdx=0; rowIdx<4; rowIdx++) {
            for (int colIdx=0; colIdx<4; colIdx++) {
                sb.append(rowIdx==colIdx ? "X" : theMatrix[colIdx][rowIdx]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
