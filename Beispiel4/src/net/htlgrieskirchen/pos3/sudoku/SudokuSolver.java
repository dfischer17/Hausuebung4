package net.htlgrieskirchen.pos3.sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/* Please enter here an answer to task four between the tags:
 * <answerTask4>
 *    Hier sollte die Antwort auf die Aufgabe 4 stehen.
 * </answerTask4>
 */
public class SudokuSolver implements ISodukoSolver {

    public SudokuSolver() {
        //initialize if necessary
    }

    @Override
    public final int[][] readSudoku(File file) {
        int[][] sudoku = new int[9][9];
        try {

            List<String> rows = Files.lines(file.toPath()).collect(Collectors.toList());

            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku.length; j++) {
                    String[] rowNumbers = rows.get(i).split(";");
                    sudoku[i][j] = Integer.parseInt(rowNumbers[j]);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(SudokuSolver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sudoku;
    }

    @Override
    public boolean checkSudoku(int[][] rawSudoku) {        
        // Zeilen ueberpruefen   
        for (int i = 0; i < rawSudoku.length; i++) {
            List<Integer> illegalNumbers = new ArrayList<>();
            
            for (int j = 0; j < rawSudoku.length; j++) {
                for (int illegalNumber : illegalNumbers) {
                    // Ueberpruefen ob Zahl in Reihe vorgekommen ist
                    if (rawSudoku[i][j] == illegalNumber) {
                        return false;
                    }
                }
                // In Reihe vorgekommene Zahlen sperren
                illegalNumbers.add(rawSudoku[i][j]);
            }
        }
                
        // Spalten ueberpruefen
        for (int i = 0; i < rawSudoku.length; i++) {
            List<Integer> illegalNumbers = new ArrayList<>();
            
            for (int j = 0; j < rawSudoku.length; j++) {
                for (int illegalNumber : illegalNumbers) {
                    // Ueberpruefen ob Zahl in Spalte vorgekommen ist
                    if (rawSudoku[j][i] == illegalNumber) {
                        return false;
                    }
                }
                // In Spalte vorgekommene Zahlen sperren
                illegalNumbers.add(rawSudoku[j][i]);
            }
        }
        
        // 3x3 Bloecke ueberpruefen fehlt
        List<Integer> block = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Zahl mehr als einmal im 3x3 Block
                for (int num : block) {
                    if (rawSudoku[i][j] == num) {
                        return false;
                    }
                }
                block.add(rawSudoku[i][j]);                
            }
        }
        return true;
    }

    @Override
    public int[][] solveSudoku(int[][] rawSudoku) {
        // implement this method
        return new int[0][0]; // delete this line!
    }

    @Override
    public int[][] solveSudokuParallel(int[][] rawSudoku) {
        // implement this method
        return new int[0][0]; // delete this line!
    }

    // add helper methods here if necessary
}
