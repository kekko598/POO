package poo.esercizio11_09_2019;

import poo.util.Matrix;

public class SudokuSolver {
    public static boolean solve( int[][] grid ) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    // Prova tutti i valori possibili per la cella vuota
                    for (int k = 1; k <= 9; k++) {
                        if (isValid(grid, i, j, k)) {
                            grid[i][j] = k;

                            if (solve(grid)) {
                                return true;
                            } else {
                                // Reimposta il valore della cella per continuare a provare altre opzioni
                                grid[i][j] = 0;
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    // Verifica se il valore specificato è valido per la posizione della cella nella griglia di sudoku
    private static boolean isValid( int[][] grid, int row, int col, int value ) {
        // Verifica se il valore è già presente nella riga
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == value) {
                return false;
            }
        }

        // Verifica se il valore è già presente nella colonna
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == value) {
                return false;
            }
        }

        // Verifica se il valore è già presente nella regione 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main( String[] args ) {
        int[][] matrix = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = 0;
            }
        }

        solve(matrix);
        Matrix.print(matrix);
    }
}
