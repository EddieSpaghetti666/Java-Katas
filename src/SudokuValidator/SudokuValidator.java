package SudokuValidator;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {

    private static Set<Integer> previouslySeenDigits = new HashSet<>();

    public static boolean check(int[][] sudoku) {
        for(int[] row : sudoku){
            previouslySeenDigits.clear();
            if(!validateRow(row)) return false;
        }
        for(int i = 0; i < sudoku.length; i++){
            if(!validateCol(sudoku, i)) return false;
            int boxRowStart = i % 3 * 3;
            int boxColStart=  i / 3 * 3;
            if(!validateBox(sudoku, boxRowStart, boxColStart)) return false;
        }
        return true;
    }

    private static boolean validPlacement(int i){
        if(i > 9 || i < 1) return false;
        else if(previouslySeenDigits.contains(i)) return false;
        else {
            previouslySeenDigits.add(i);
            return true;
        }
    }
    private static boolean validateRow(int[] row){
        for(int i : row) {
            if(!validPlacement(i)) return false;
        }
        previouslySeenDigits.clear();
        return true;
    }
    private static boolean validateCol(int[][] sudoku, int col){
        for(int i = 0; i < sudoku.length; i++){
            int digitInCol = sudoku[i][col];
            if(!validPlacement(digitInCol)) return false;
        }
        previouslySeenDigits.clear();
        return true;
    }
    private static boolean validateBox(int[][] sudoku, int rowStart, int colStart){
        for(int i = rowStart; i < rowStart + 3; ++i){
            for(int j = colStart; j < colStart + 3; j++){
                int digitInBox = sudoku[i][j];
                if(!validPlacement(digitInBox)) return false;
            }
        }
        previouslySeenDigits.clear();
        return true;
    }

}