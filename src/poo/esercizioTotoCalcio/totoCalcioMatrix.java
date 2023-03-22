package poo.esercizioTotoCalcio;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class totoCalcioMatrix implements totoCalcio {

    private char[][] sistema;

    private Character[] colonna;
    private LinkedList<Character> puntiDiScelta = new LinkedList<>();

    public totoCalcioMatrix( char[][] sistema ) {
        this.sistema = sistema;
        int n = sistema.length;
        int m = sistema[0].length;
        /*
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < sistema.length; i++)
            for (int j = 0; j < sistema[i].length; j++) {
                System.out.print("inserisci giocata in posizione (" + i + "," + j + ") 1 o X o 2: dove non vuoi la giocata scrivi N, se invece hai finito di inserire premi invio ->");
                Character linea = sc.nextLine().toUpperCase().charAt(0);
                if (linea.equals('N')) {
                    System.out.println("Non ho inserito niente in posizione " + "(" + i + "," + j + ")");
                    continue;
                }
                if (linea.equals('X') || linea.equals('1') || linea.equals('2'))
                    sistema[i][j] = linea;

                else {
                    System.out.println("Ammetto solo 1 o X o 2");
                    System.exit(-1);
                }

            }
         */
        colonna = new Character[13];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (sistema[i][j] != ' ')
                    puntiDiScelta.add(sistema[i][j]);
            }

        }


    }

    public static void main( String[] args ) {

        char[][] sistema = new char[13][3];
        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 3; j++) {
                int val = random.nextInt(4);
                if (val == 0) {
                    sistema[i][j] = '1';
                } else if (val == 1) {
                    sistema[i][j] = '2';
                } else if (val == 2) {
                    sistema[i][j] = 'X';
                } else {
                    sistema[i][j] = ' ';
                }
            }
        }
        totoCalcioMatrix t = new totoCalcioMatrix(sistema);
        t.risolvi();
        System.out.println(t);
    }

    public void risolvi() {

        for (int i = 0; i < colonna.length; i++) {
            Collections.shuffle(puntiDiScelta);
            if (!puntiDiScelta.isEmpty())
                colonna[i] = puntiDiScelta.removeFirst();
            else
                break;
        }
        scriviSoluzione(0);
    }

    private void scriviSoluzione( int i ) {

        if (i == colonna.length - 1)
            System.out.println(Arrays.toString(colonna));

        for (int j = i; j < colonna.length; j++) {
            Character park = colonna[i];
            colonna[i] = colonna[j];
            colonna[j] = park;
            scriviSoluzione(i + 1);
            park = colonna[i];
            colonna[i] = colonna[j];
            colonna[j] = park;

        }


    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sistema.length; ++i) {
            for (int j = 0; j < sistema[i].length; ++j)
                sb.append(sistema[i][j]).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }
}
