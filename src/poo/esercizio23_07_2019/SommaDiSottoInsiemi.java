package poo.esercizio23_07_2019;

import poo.backtracking.Backtracking;

import java.util.*;

public class SommaDiSottoInsiemi extends Backtracking<Integer, Integer> {

    private int[] a;
    private int[] b;
    private int m;

    private int sol;

    public SommaDiSottoInsiemi( int[] a, int m ) {
        if (a.length < 2)
            throw new IllegalArgumentException("Inserisci almeno 2 elementi!");
        Set<Integer> tmp = new HashSet<>();
        for (int e : a)
            tmp.add(e);
        if (tmp.size() != a.length)
            throw new IllegalArgumentException("Elementi non distinti!");
        this.a = a;
        this.m = m;
        b = new int[a.length];
    }

    public static void main( String[] args ) {
        SommaDiSottoInsiemi s = new SommaDiSottoInsiemi(new int[]{1, 2, 3, 4, 5, 10, 20, 50, 60, 80, 100, 7, 8, 9, 11, 12, 13, 14, 15}, 70);
        s.risolvi();
    }

    @Override
    protected boolean assegnabile( Integer integer, Integer integer2 ) {
        for (int i = 0; i < integer; i++)
            if (a[i] == b[integer2])
                return false;
        return true;
    }

    @Override
    protected void assegna( Integer ps, Integer integer ) {
        b[ps] = a[integer];
    }

    @Override
    protected boolean esisteSoluzione( Integer integer ) {
        int s = 0;
        for (int j : b) s += j;
        return s == m;
    }

    @Override
    protected void deassegna( Integer ps, Integer integer ) {
        return;
    }

    @Override
    protected void scriviSoluzione( Integer integer ) {
        sol++;
        System.out.println("Numero soluzione " + sol + ": il vettore che da la somma uguale a " + m + " è " + Arrays.toString(b));
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < a.length; i++)
            ls.add(i);
        return ls;
    }

    @Override
    protected Collection<Integer> scelte( Integer integer ) {
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < a.length; i++)
            ls.add(i);
        return ls;
    }

    @Override
    public void risolvi() {
        super.risolvi();
    }
}
