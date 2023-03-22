package poo.recursion;

public class NQueens {
    /*
     problema risolvibile con backtracking, cioè su tentativi
     */
    private int n, numSol;
    private boolean[][] scacchiera;

    public NQueens( int n ) {
        if (n < 4) throw new IllegalArgumentException();
        this.n = n;
        scacchiera = new boolean[n][n];
        //tutti i false li mette già JAVA di default

    }

    public static void main( String[] args ) {
        new NQueens(8).risolvi();
    }

    public void risolvi() {
        collocaRegina(0); //lo 0 indica la prima riga
    }

    private void collocaRegina( int riga ) {//fai i tentativi richiesti
        for (int colonna = 0; colonna < n; colonna++) {
            //verifico se ci sono condizioni di attacco
            if (assegnabile(riga, colonna)) {
                assegna(riga, colonna);
                if (riga == n - 1)
                //regina su ultima riga, abbiamo una soluzione!
                {
                    scriviSoluzione();
                    //sol parziale: sono stato in grado di piazzare le regine su certe righe

                } else {
                    collocaRegina(riga + 1);
                    //vai avanti a trovare altre soluzioni
                    //ogni riga nuova ha sempre una chiamata ricorsiva

                }
                //se non trovo che non ho nessuna posizione che mi vada bene
                //devo tornare indietro sulla precedente riga, vedendo se c'è qualche altra soluzione e cosi via, tornando sempre indietro
                deassegna(riga, colonna);
            }
        }
    }

    private void deassegna( int riga, int colonna ) {
        scacchiera[riga][colonna] = false;

    }

    private void scriviSoluzione() {
        numSol++;
        System.out.print("Soluzione: " + numSol + "");
        for (int i = 0; i < n; i++) {
            //sulle collonne una sola regina c'è
            for (int j = 0; j < n; j++)
                if (scacchiera[i][j] == true) {
                    System.out.print(" sposta regina in posizione: " + "<" + i + "," + j + ">");
                    break; //appena trovo la regina esco
                }

        }
        System.out.println();
    }

    private void assegna( int riga, int colonna ) {
        scacchiera[riga][colonna] = true;
    }

    private boolean assegnabile( int r, int c ) {
        //su una riga c'è al più una regina, devo fare la verifica da colonna

        //attacco da nord
        for (int i = r - 1; i >= 0; i--)
            if (scacchiera[i][c] == true)
                return false;
        //attacco da nord-ovest
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; j--, i--)
            if (scacchiera[i][j] == true)
                return false;
        //attacco da nord-est
        for (int i = r - 1, j = c + 1; i >= 0 && j <= n - 1; j++, i--)
            if (scacchiera[i][j] == true)
                return false;


        return true;
    }
}
