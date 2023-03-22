package poo.esercizio10_11_2017;

import java.util.Map;
import java.util.TreeMap;

public class MatriceSparsaMap<T> extends MatriceSparsaAstratta<T> {

    private Map<Integer, T> matrice;
    private int n;

    public MatriceSparsaMap( int n ) {
        super(n);
        this.n = n;
        matrice = new TreeMap<>();
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public void clear() {
        matrice.clear();
    }

    @Override
    public double grado() {
        int nonZeroElementi = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (get(i, j) != null)
                    nonZeroElementi++;
            }

        return (double) nonZeroElementi / (n * n) * 100;
    }

    @Override
    public T get( int i, int j ) {
        if (i >= n || j >= n || i < 0 || j < 0)
            throw new IndexOutOfBoundsException();
        int key = (i * n) + j;
        return matrice.get(key);
    }

    @Override
    public void set( int i, int j, T v ) {
        if (i >= n || j >= n || i < 0 || j < 0)
            throw new IndexOutOfBoundsException();
        int key = (i * n) + j;
        if (!matrice.containsKey(key))
            matrice.put(key, v);
        else
            matrice.replace(key, v);
    }

    @Override
    public MatriceSparsa creaMatriceVuota() {
        return new MatriceSparsaMap(n);
    }

    @Override
    public MatriceSparsa<Integer> addInt( MatriceSparsa m ) {
        if (m.getN() != getN())
            throw new IllegalArgumentException();
        MatriceSparsa<Integer> tmp = creaMatriceVuota();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int v = Math.addExact((Integer) get(i, j), (Integer) m.get(i, j));
                tmp.set(i, j, v);
            }

        return tmp;
    }


    @Override
    public MatriceSparsa<Double> addDouble( MatriceSparsa m ) {
        if (m.getN() != getN())
            throw new IllegalArgumentException();
        MatriceSparsa<Double> tmp = creaMatriceVuota();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                double v = (double) get(i, j) + (double) m.get(i, j);
                tmp.set(i, j, v);
            }

        return tmp;
    }

    @Override
    public MatriceSparsa<Integer> mulInt( MatriceSparsa m ) {
        if (m.getN() != getN())
            throw new IllegalArgumentException();
        MatriceSparsa<Integer> tmp = creaMatriceVuota();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int ps = 0;
                for (int k = 0; k < n; ++k)
                    ps = ps + (int) get(i, k) * (int) m.get(k, j);
                tmp.set(i, j, ps);
            }
        return tmp;
    }

    @Override
    public MatriceSparsa<Double> mulDouble( MatriceSparsa m ) {
        if (m.getN() != getN())
            throw new IllegalArgumentException();
        MatriceSparsa<Double> tmp = creaMatriceVuota();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                double ps = 0;
                for (int k = 0; k < n; ++k)
                    ps = ps + (double) get(i, k) * (double) m.get(k, j);
                tmp.set(i, j, ps);
            }
        return tmp;
    }


    @Override
    public boolean simmetrica() {
        return equals(trasposta());
    }

    public MatriceSparsa trasposta() {
        MatriceSparsa<T> tmp = creaMatriceVuota();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tmp.set(j, i, get(i, j));
        return tmp;
    }

}
