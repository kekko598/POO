package poo.esercizio11_09_2019;

import poo.backtracking.Backtracking;
import poo.util.Matrix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Sudoku extends Backtracking<Pair<Integer, Integer>, Integer> {
    private int[][] sudoku;


    public Sudoku() {
        sudoku = new int[9][9];
    }

    public Sudoku( int[][] sudoku ) {
        if (sudoku.length != 9 && sudoku[0].length != 9)
            throw new IllegalArgumentException("Si accettano solo matrici 9x9!");
        for (int i = 0; i < sudoku.length; i++)
            for (int j = 0; j < sudoku[i].length; j++)
                if (sudoku[i][j] < 0 || sudoku[i][j] > 9)
                    throw new IllegalArgumentException("Matrice contiene valori non compresi tra 0 e 9!");

        for (int row = 0; row < sudoku.length; row++) {
            for (int col = 0; col < sudoku[row].length; col++) {
                int number = sudoku[row][col];
                if (number != 0) {
                    int rowCount = 0;
                    int colCount = 0;
                    for (int i = 0; i < sudoku.length; i++) {
                        if (sudoku[i][col] == number) {
                            colCount++;
                        }
                        if (sudoku[row][i] == number) {
                            rowCount++;
                        }
                    }
                    if (rowCount > 2 || colCount > 2)
                        throw new IllegalArgumentException("Numero " + number + " appare più di una volta in riga: " + row + " e colonna: " + col);
                }

            }
        }
        int i = 4;
        int j = 4;
        int value = sudoku[i][j];

        int subRow = i / 3;
        int subCol = j / 3;
        int startRow = subRow * 3;
        int startCol = subCol * 3;

        int count = 0;
        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                if (sudoku[row][col] == value && value != 0) {
                    count++;
                }
            }
        }
        if (count > 1) {
            throw new IllegalArgumentException("Numero " + value + " appare più di una volta in una sotto-matrice 3x3");
        }
        this.sudoku = sudoku;
    }


    public static void main( String[] args ) {
        {

            int[][] grid = {
                    {0, 0, 0, 0, 0, 0, 9, 0, 0},
                    {6, 0, 2, 1, 9, 5, 3, 4, 0},
                    {1, 9, 0, 3, 4, 2, 0, 0, 7},
                    {8, 5, 9, 7, 6, 1, 4, 2, 0},
                    {4, 2, 0, 0, 0, 3, 7, 9, 1},
                    {0, 0, 3, 0, 2, 4, 8, 0, 6},
                    {9, 6, 1, 5, 3, 7, 2, 8, 4},
                    {2, 8, 7, 4, 0, 9, 6, 0, 5},
                    {3, 4, 5, 2, 8, 0, 1, 7, 0}
            };

/*
final int BOARD_SIZE = 9;
              final int MIN_NUMBER = 1;
              final int MAX_NUMBER = 9;
              final int NUM_OF_BLANKS = 10;


                int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

                // Populate the board with random numbers
                Random random = new Random();
                for (int i = 0; i < BOARD_SIZE; i++) {
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        board[i][j] = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
                    }
                }

                // Replace some of the numbers with 0 (blank spaces)
                for (int i = 0; i < NUM_OF_BLANKS; i++) {
                    int row = random.nextInt(BOARD_SIZE);
                    int col = random.nextInt(BOARD_SIZE);
                    board[row][col] = 0;
                }
 */


            Sudoku s = new Sudoku(grid);
            System.out.println(s);
            s.risolvi();
        }


    }

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

    @Override
    protected boolean esisteSoluzione( Pair<Integer, Integer> ps ) {
        for (int i = 0; i < sudoku.length; i++)
            for (int j = 0; j < sudoku[i].length; j++)
                if (sudoku[i][j] == 0)
                    return false;
        return true;

    }

    @Override
    protected boolean ultimaSoluzione( Pair<Integer, Integer> integerIntegerPair ) {
        for (int i = 0; i < sudoku.length; i++)
            for (int j = 0; j < sudoku[i].length; j++)
                if (sudoku[i][j] == 0)
                    return false;
        return true;
    }

    @Override
    protected boolean assegnabile( Pair<Integer, Integer> ps, Integer val ) {
        return isValid(sudoku, ps.getFirst(), ps.getSecond(), val);
    }

    @Override
    protected void assegna( Pair<Integer, Integer> ps, Integer val ) {
        sudoku[ps.getFirst()][ps.getSecond()] = val;
    }

    @Override
    protected void deassegna( Pair<Integer, Integer> ps, Integer val ) {
        sudoku[ps.getFirst()][ps.getSecond()] = 0;
    }

    @Override
    protected void scriviSoluzione( Pair<Integer, Integer> ps ) {
        Matrix.print(sudoku);
        System.exit(0);

    }

    @Override
    protected List<Pair<Integer, Integer>> puntiDiScelta() {
        List<Pair<Integer, Integer>> ps = new ArrayList<>();
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (sudoku[i][j] == 0)
                    ps.add(new Pair<>(i, j));
            }
        }
        return ps;
    }

    @Override
    protected Collection<Integer> scelte( Pair<Integer, Integer> ps ) {
        List<Integer> s = new ArrayList<>();
        for (int i = 0; i < sudoku.length; i++)
            for (int j = 0; j < sudoku[i].length; j++)
                s.add(sudoku[i][j]);
        return s;

    }

    @Override
    public void risolvi() {
        super.risolvi();
    }

    @Override
    public String toString() {
        Matrix.print(sudoku);
        return "";
    }
}
