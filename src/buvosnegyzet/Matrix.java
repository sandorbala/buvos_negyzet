package buvosnegyzet;

import java.util.HashSet;
import java.util.Set;

public class Matrix {
    private int[][] theMatrix = new int[4][4];
    private Integer lastX = 0, lastY = 0;
    private int count;
    private Set<Integer> numbersInMatrix= new HashSet<Integer>(16); 
    private boolean correct;
    
    public Matrix() {
    	this(new int[] {0,1,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0});
    }

    public Matrix(int[] data) {
        if (data.length != 16) {
            throw new RuntimeException("16 numbers is expected");
        }
        
        for (int rowIdx=0; rowIdx<4; rowIdx++) {
            for (int colIdx=0; colIdx<4; colIdx++) {
                if (colIdx==rowIdx) {
                    theMatrix[colIdx][rowIdx] =  0;
                } else {
                    int x = data[rowIdx*4+colIdx];
                    theMatrix[colIdx][rowIdx] = x;
                    if (x > 0) {
                        count++;
                        lastX = colIdx;
                        lastY = rowIdx;
                        numbersInMatrix.add(x);
                    }
                }
            }
        }
        
        correct = getCorrectness();
    }
    
    private Matrix(Matrix baseMatrix, int newX, int newY, int newValue) {
        for (int rowIdx=0; rowIdx<4; rowIdx++) {
            for (int colIdx=0; colIdx<4; colIdx++) {
                int x = colIdx==newX && rowIdx==newY ? newValue : baseMatrix.theMatrix[colIdx][rowIdx];
                theMatrix[colIdx][rowIdx] = x;
                if (x > 0) {
                    count++;
                    lastX = colIdx;
                    lastY = rowIdx;
                    numbersInMatrix.add(x);
                }
            }
        }
        correct = getCorrectness();
    }
    
    public int getCount() {
        return count; 
    }
    
    public boolean isEndState() {
        return count == 12;
    }
    
    public boolean isCorrect() {
    	return correct;
    }

    private boolean getCorrectness() {
    	int sum = 0, count = 0;
    	
        sum = getRowSum(0);
        count = getRowCount(0);
        if (count == 3 && sum != 19 || count != 3 && sum >= 19) return false;   // 19
        sum = getRowSum(1);
        count = getRowCount(1);
        if (count == 3 && sum != 22 || count != 3 && sum >= 22) return false;   // 22
        sum = getRowSum(2);
        count = getRowCount(2);
        if (count == 3 && sum != 20 || count != 3 && sum >= 20) return false;   // 20
        sum = getRowSum(3);
        count = getRowCount(3);
        if (count == 3 && sum != 17 || count != 3 && sum >= 17) return false;   // 17
        
        sum = getColumnSum(0);
        count = getColumnCount(0);
        if (count == 3 && sum != 20 || count != 3 && sum >= 20) return false;   // 20
        sum = getColumnSum(1);
        count = getColumnCount(1);
        if (count == 3 && sum != 19 || count != 3 && sum >= 19) return false;   // 19
        sum = getColumnSum(2);
        count = getColumnCount(2);
        if (count == 3 && sum != 17 || count != 3 && sum >= 17) return false;   // 17
        sum = getColumnSum(3);
        count = getColumnCount(3);
        if (count == 3 && sum != 22 || count != 3 && sum >= 22) return false;   // 22
        
        return true;
    }

    public Matrix getNextState() {
        if (isEndState()) {
            return null;
        }

        int recentValue = theMatrix[lastX][lastY];
        int newValue = 0;
        
        for (int i=recentValue; i<13; i++) {
        	if (!numbersInMatrix.contains(i)) {
        		newValue = i;
        		break;
        	}
        }
        
        if (newValue == 0) {
        	return null;
        }
        
        return new Matrix(this, lastX, lastY, newValue);
    }
    
    public Matrix getNextLevelState() {
        int newX, newY;
        if (isEndState()) {
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
            return null;
        }
        
        int newValue = 0;
        for (int i=1; i<13; i++) {
        	if (!numbersInMatrix.contains(i)) {
        		newValue = i;
        		break;
        	}
        }
        
        if (newValue == 0) {
        	return null;
        }
        
        return new Matrix(this, newX, newY, newValue);
    }

    private int getRowSum(int rowIdx) {
        int sum = 0;
        for (int i=0; i<4; i++) {
            sum += theMatrix[i][rowIdx];
        }
        return sum;
    }
    
    private int getRowCount(int rowIdx) {
        int cnt = 0;
        for (int i=0; i<4; i++) {
            cnt += theMatrix[i][rowIdx] == 0 ? 0 : 1;
        }
        return cnt;
    }

    private int getColumnSum(int columnIdx) {
        int sum = 0;
        for (int i=0; i<4; i++) {
            sum += theMatrix[columnIdx][i];
        }
        return sum;
    }
    private int getColumnCount(int columnIdx) {
        int cnt = 0;
        for (int i=0; i<4; i++) {
            cnt += theMatrix[columnIdx][i] == 0 ? 0 : 1;
        }
        return cnt;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int rowIdx=0; rowIdx<4; rowIdx++) {
            for (int colIdx=0; colIdx<4; colIdx++) {
            	if (rowIdx==colIdx) {
            		sb.append("  X");
            	} else if (theMatrix[colIdx][rowIdx] > 9) {
            		sb.append(" ").append(theMatrix[colIdx][rowIdx]);            		
            	} else {
            		sb.append("  ").append(theMatrix[colIdx][rowIdx]);
            	}
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
