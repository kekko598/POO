package poo.esercizio18_01_2023.esercizio2;

import poo.backtracking.Backtracking;

import java.util.*;

public class GestoreScaffali extends Backtracking<Coppia, Integer> {

    int maxPortata, numeroSoluzioni, maxSoluzioni = 1;
    Integer[][] scaffalatura;
    List<Integer> pacchiDaSistemare;

    public GestoreScaffali(int scaffali, int slot, List<Integer> pacchiDaSistemare, int maxPortata) {
        scaffalatura = new Integer[scaffali][slot];

        // Slot vuoti sono interi = 0
        for (int i = 0; i < scaffalatura.length; i++) {
            for (int j = 0; j < scaffalatura[0].length; j++) {
                scaffalatura[i][j] = 0;
            }
        }
        this.pacchiDaSistemare = pacchiDaSistemare;
        this.maxPortata = maxPortata;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scaffalatura.length; i++) {
            sb.append(
                    Arrays.toString(scaffalatura[i])
            );
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int h = 1;
        h = prime * h + Arrays.deepHashCode(scaffalatura);
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof GestoreScaffali)) return false;

        GestoreScaffali other = (GestoreScaffali) obj;
        return Arrays.deepEquals(scaffalatura, other.scaffalatura);
    }

    @Override
    protected boolean assegnabile(Coppia c, Integer peso) {
        // non assegnabile se già assegnato o supererebbe la portata con l'aggiunta
        return !( scaffalatura[c.scaffale][c.slot] != 0 ||
                portataSuperata(pesoCorrente(c) + peso) );
    }

    @Override
    protected void assegna(Coppia c, Integer peso) {
        // assegna allo slot
        scaffalatura[c.scaffale][c.slot] = peso;

        // e rimuove dalle scelte l'intero appena assegnato
        Iterator<Integer> it = pacchiDaSistemare.iterator();
        while (it.hasNext()) {
            Integer corrente = it.next();
            if (corrente.equals(peso))
                it.remove();
            break;
        }
    }

    @Override
    protected void deassegna(Coppia c, Integer peso) {
        // riaggiunge il pacco alle scelte e libera lo slot
        this.pacchiDaSistemare.add(scaffalatura[c.scaffale][c.slot]);
        scaffalatura[c.scaffale][c.slot] = 0;
    }

    @Override
    protected boolean esisteSoluzione(Coppia p) {
        // la soluzione esiste se vengono assegnati tutti i pacchi
        if (this.pacchiDaSistemare.size() == 0) {
            numeroSoluzioni++;
            return true;
        }
        return false;
    }

    @Override
    protected boolean ultimaSoluzione(Coppia p) {
        return numeroSoluzioni == maxSoluzioni;
    }

    @Override
    protected void scriviSoluzione(Coppia c) {
        System.out.println(this.toString());
    }

    @Override
    protected List<Coppia> puntiDiScelta() {
        List<Coppia> puntiDiScelta = new ArrayList<>();
        for (int i = 0; i < scaffalatura.length; i++) {
            for (int j = 0; j < scaffalatura[0].length; j++) {
                puntiDiScelta.add(new Coppia(i, j));
            }
        }
        return puntiDiScelta;
    }

    @Override
    protected Collection<Integer> scelte( Coppia c) {
        return this.pacchiDaSistemare;
    }

    @Override
    protected void risolvi() {
        super.risolvi();
    }

    private int pesoCorrente(Coppia c) {
        int sum = 0;
        for (Integer i : scaffalatura[c.scaffale]) {
            sum += i;
        }
        return sum;
    }

    private boolean portataSuperata(int portata) {
        return portata > maxPortata;
    }

    public static void main(String[] args) {
        Integer[] arrayPacchi = {2, 1, 3, 1, 2, 1, 3, 2, 1, 2, 3, 1, 2, 3, 1};
        List<Integer> pacchiDaSistemare = Arrays.asList(arrayPacchi);
        int maxPortata = 8;
        GestoreScaffali gs = new GestoreScaffali(5, 3, pacchiDaSistemare, maxPortata);
        gs.risolvi();
        System.out.println("done");
    }

}