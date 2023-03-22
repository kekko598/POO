package poo.sistema;

public abstract class Sistema {
    private final int n;

    public Sistema( double[][] a, double[] y ) {
        this.n = a.length;
        for (int i = 0; i < n; i++)
            if (a[i].length != n)
                throw new IllegalArgumentException("mat non quadrata");
        if (y.length != n)
            throw new IllegalArgumentException("mat non consistente");

    }

    public int getN() {
        return n;
    }

    public abstract double[] risolvi();
}
