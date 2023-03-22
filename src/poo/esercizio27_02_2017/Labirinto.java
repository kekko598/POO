package poo.esercizio27_02_2017;

import poo.util.Matrix;

import java.util.LinkedList;

public class Labirinto {

    private int portaUscita;
    private int[][] labirinto;
    private boolean[] stanzeVisitate;
    private int numeroStanze;
    private LinkedList<String> percorso = new LinkedList<>();

    public Labirinto( int grandezzaLabirinto, int portaUscita ) {
        if (grandezzaLabirinto > 9999 || portaUscita < 1 || grandezzaLabirinto < 1)
            throw new IllegalArgumentException();
        this.portaUscita = portaUscita;
        labirinto = new int[grandezzaLabirinto][4];
        stanzeVisitate = new boolean[9999];
    }

    public static void main( String[] args ) {
        Labirinto l = new Labirinto(4, 9999);
        l.setStanzeAdiacenti(0, 2, 3, 4, 1);
        l.setStanzeAdiacenti(1, 3, 2, 1, 1);
        l.setStanzeAdiacenti(2, 0, 2, 0, 2);
        l.setStanzeAdiacenti(3, 3, 2, 0, 9999);

        l.stampa();
        l.risolvi();


    }

    public void stampa() {
        Matrix.print(labirinto);

    }

    public void setStanzeAdiacenti( int stanza, int i1, int i2, int i3, int i4 ) {
        this.numeroStanze = stanza;
        this.labirinto[stanza][0] = i1;
        this.labirinto[stanza][1] = i2;
        this.labirinto[stanza][2] = i3;
        this.labirinto[stanza][3] = i4;
    }

    public void risolvi() {
        if (trovaUscita(0))
            System.out.println("Uscita: " + percorso);
        else
            System.out.println("Non c'è via d'uscita");
    }

    private boolean trovaUscita( int stanzaAttuale ) {
        if (stanzaAttuale == portaUscita)//ultimo punto di scelta
        {
            //percorso.add(String.valueOf(portaUscita));
            return true;
        }
        if (stanzeVisitate[stanzaAttuale])//stanza già visitata
            return false;
        stanzeVisitate[stanzaAttuale] = true;//marco visitata la stanza
        for (int a = 0; a < 4; a++) {
            int stanzaAdiacente = labirinto[stanzaAttuale][a];
            if (adiacentiDiversiDaZero(stanzaAttuale, a, labirinto) && stanzaAdiacente != 0) {//porta aperta
                percorso.add(String.valueOf(stanzaAdiacente));
                if (trovaUscita(stanzaAdiacente))// se trovo uscita dai true
                    return true;
                //percorso.remove(percorso.size()-1);//se non trovo uscita rimuovo la stanza
            }

        }
        return false;
    }

    private boolean adiacentiDiversiDaZero( int x, int y, int[][] matrix ) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int row = x + i;
                int col = y + j;
                if (row >= 0 && row < rows && col >= 0 && col < cols && matrix[row][col] != 0) {
                    return true;
                }
            }
        }

        return false;
    }

}
