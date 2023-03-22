package poo.esercizio10_11_2017;


import java.util.LinkedList;


public class MatriceSparsaLinked<T> extends MatriceSparsaAstratta<T> {

    private LinkedList<ElementoRiga<T>> righe;

    private int n;

    public MatriceSparsaLinked( int n ) {
        super(n);
        if (n < 1)
            throw new IllegalArgumentException();
        this.n = n;
        righe = new LinkedList<>();


    }

    public static void main( String[] args ) {
        MatriceSparsaLinked<Integer> m = new MatriceSparsaLinked<>(3);
        m.set(0, 0, 3);
        m.set(0, 1, 2);
        m.set(0, 2, 1);

        m.set(1, 0, 2);
        m.set(1, 1, 4);
        m.set(1, 2, 0);

        m.set(2, 0, 1);
        m.set(2, 1, 0);
        m.set(2, 2, 5);
        System.out.println(m);//System.out.println(m.simmetrica());

    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public void clear() {
        righe.clear();
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
        for (ElementoRiga r : righe)
            if (r.getI() == i) {
                for (ElementoColonna c : new LinkedList<ElementoColonna<T>>(r.getRigaGenericaSuColonnaJ()))
                    if (c.getJ() == j) {
                        return (T) c.getVal();
                    }
            }

        return null;
    }

    @Override
    public void set( int i, int j, T v ) {
        if (i >= n || j >= n || i < 0 || j < 0)
            throw new IndexOutOfBoundsException();
        for (ElementoRiga r : righe)
            if (r.getI() == i) {
                for (ElementoColonna c : new LinkedList<ElementoColonna<T>>(r.getRigaGenericaSuColonnaJ()))
                    if (c.getJ() == j) {
                        c.setVal(v);
                        return;//sovraccarico val se i e j è verificata
                    }
                //sovraccarico val se i è verificata ma non j
                r.getRigaGenericaSuColonnaJ().add(new ElementoColonna<>(j, v));
                return;
            }

        // se è vuota la creo la riga e colonna data da fuori
        ElementoColonna<T> eC = new ElementoColonna<>(j, v);
        ElementoRiga<T> eR = new ElementoRiga<>(i);
        eR.getRigaGenericaSuColonnaJ().add(eC);// metto colonna su riga
        righe.add(eR);//aggiungo riga con colonna in matrice

    }

    @Override
    public MatriceSparsa creaMatriceVuota() {
        return new MatriceSparsaLinked<T>(n);
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
