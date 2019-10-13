/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.sudoku;

import java.io.File;

/**
 *
 * @author Daniel Fischer
 */
public class Test {
    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
        int[][] sudoku = new int[9][9];
        sudoku = ss.readSudoku(new File("sudoku_completetest.csv"));
        
        boolean check = ss.checkSudoku(sudoku);
    }
}
