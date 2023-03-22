package poo.backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NRegine extends Backtracking<Integer, Integer> {
    /*
     problema risolvibile con backtracking, cioè su tentativi
     */
    private int n, numSol;
    private boolean[][] scacchiera;


    public NRegine( int n ) {
        if (n < 4) throw new IllegalArgumentException();
        this.n = n;
        scacchiera = new boolean[n][n];
        //tutti i false li mette già JAVA di default

    }

    public static void main( String[] args ) {
        new NRegine(4).risolvi();
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> ps = new ArrayList<>();
        for (int i = 0; i < n; i++)
            ps.add(i);
        return ps;
    }

    @Override
    protected Collection<Integer> scelte( Integer p ) {
        List<Integer> s = new ArrayList<>();
        for (int j = 0; j < n; j++)
            s.add(j);
        return s;
    }

    @Override
    protected boolean esisteSoluzione( Integer p ) {
        return p == n - 1;
    }

    @Override
    protected boolean ultimaSoluzione( Integer p ) {
        return p == n - 1;
    }

    public void risolvi() {
        collocaRegina(0); //lo 0 indica la prima riga
    }

    @Override
    protected void assegna( Integer riga, Integer colonna ) {
        scacchiera[riga][colonna] = true;

    }


    private void collocaRegina( int riga ) {//fai i tentativi richiesti
        for (int colonna = 0; colonna < n; colonna++) {
            //verifico se ci sono condizioni di attacco
            if (assegnabile(riga, colonna)) {
                assegna(riga, colonna);
                if (esisteSoluzione(riga))
                //regina su ultima riga, abbiamo una soluzione!
                {
                    scriviSoluzione(riga);
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

    @Override
    protected boolean assegnabile( Integer r, Integer c ) {
        //su una riga c'è al più una regina, devo fare la verifica da colonna

        //attacco da nord
        for (int i = r - 1; i >= 0; i--)
            if (scacchiera[i][c])
                return false;
        //attacco da nord-ovest
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; j--, i--)
            if (scacchiera[i][j])
                return false;
        //attacco da nord-est
        for (int i = r - 1, j = c + 1; i >= 0 && j <= n - 1; j++, i--)
            if (scacchiera[i][j])
                return false;


        return true;
    }


    @Override
    protected void deassegna( Integer riga, Integer colonna ) {
        scacchiera[riga][colonna] = false;

    }


    @Override
    protected void scriviSoluzione( Integer riga ) {
        numSol++;
        System.out.print("Soluzione: " + numSol + "");
        for (int i = 0; i < n; i++) {
            //sulle colonne una sola regina c'è
            for (int j = 0; j < n; j++)
                if (scacchiera[i][j]) {
                    System.out.print(" sposta regina in posizione: " + "<" + i + "," + j + ">");
                    break; //appena trovo la regina esco
                }

        }
        System.out.println();
    }


}
