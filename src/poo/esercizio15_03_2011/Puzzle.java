package poo.esercizio15_03_2011;

public class Puzzle {
    private char [][] puzzle;
    public Puzzle(int n,int m) {
        if(n<2 || m<2)
            throw new IllegalArgumentException();
        puzzle=new char[n][m];
    }
    public void add(int i,int j,char val){
        if(i<0 || j<0)
            throw new IllegalArgumentException();
        puzzle[i][j]=val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] chars : puzzle) {
            for (char aChar : chars) {
                sb.append(aChar).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public char[][] getPuzzle() {
        return puzzle;
    }

    public static boolean checkNord(char[][] puzzle, int x, int y, Parola word) {
        if (x - word.length() < 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (puzzle[x - i][y] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNordEst(char[][] puzzle, int x, int y, Parola word) {
        if (x - word.length() < 0 || y + word.length() > puzzle[0].length) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (puzzle[x - i][y + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkEst(char[][] puzzle, int x, int y, Parola word) {
        if (y + word.length() > puzzle[0].length) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (puzzle[x][y + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSudEst(char[][] puzzle, int x, int y, Parola word) {
        if (x + word.length() > puzzle.length || y + word.length() > puzzle[0].length) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (puzzle[x + i][y + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkSud(char[][] puzzle, int x, int y, Parola word) {
        if (x + word.length() > puzzle.length) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (puzzle[x + i][y] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkSudOvest(char[][] puzzle, int x, int y, Parola word) {
        if (x + word.length() > puzzle.length || y - word.length() < 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (puzzle[x + i][y - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkOvest(char[][] puzzle, int x, int y, Parola word) {
        if (y - word.length() < 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (puzzle[x][y - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNorOvest(char[][] puzzle, int x, int y, Parola word) {
        if (x - word.length() < 0 || y - word.length() < 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (puzzle[x - i][y - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
