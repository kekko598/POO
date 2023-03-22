package poo.esercizioRoulette;

import poo.backtracking.Backtracking;

import java.util.*;

public class Permutazioni extends Backtracking<Integer, Integer> {
    private final Integer[] a;
    private final Integer[] b;

    private int c;

    public Permutazioni( Integer[] a ) {
        this.a = new Integer[a.length];
        this.b = new Integer[a.length];
        System.arraycopy(a, 0, this.a, 0, a.length);
        Set<Integer> s = new HashSet<>();
        for (int x : a)
            s.add(x);
        if (a.length != s.size())
            throw new IllegalArgumentException("Ripetizioni non ammesse!");


    }

    public static void main( String[] args ) {
        Integer[] x = new Integer[5];
        int min = 1;
        int max = 100;
        Random r = new Random();
        for (int i = 0; i < x.length; i++)
            x[i] = r.nextInt(max - min + 1) + min;
        System.out.println(Arrays.toString(x));
        new Permutazioni(x).risolvi();

    }

    @Override
    protected boolean assegnabile( Integer integer, Integer integer2 ) {
        for (int i = 0; i < integer; i++)
            if (a[i] == b[integer2]) return false;
        return true;
    }

    @Override
    protected void assegna( Integer ps, Integer integer ) {
        b[ps] = a[integer];
    }

    @Override
    protected void deassegna( Integer riga, Integer colonna ) {
    }

    @Override
    protected void scriviSoluzione( Integer integer ) {
        c++;
        System.out.println("Soluzione:" + c + "-" + Arrays.toString(b));
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < b.length; i++) {
            ls.add(i);
        }
        return ls;
    }

    @Override
    protected boolean esisteSoluzione( Integer integer ) {
        return integer == b.length - 1;
    }

    @Override
    protected Collection<Integer> scelte( Integer integer ) {
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            ls.add(i);
        }
        return ls;
    }

    @Override
    public void risolvi() {
        super.risolvi();
    }
}
