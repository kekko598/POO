package poo.sistema;

import poo.util.Mat;

public class Gauss extends Sistema implements Cloneable {
    protected double[][] a;
    protected double[] y;

    public Gauss( double[][] a, double[] y ) {
        super(a, y);
        int n = getN();
        this.a = new double[n][n + 1];
        this.y = new double[n];
        for (int i = 0; i < n; ++i) {
            System.arraycopy(a[i], 0, this.a[i], 0, n); //copio la matrice vera e propria
            this.a[i][y.length] = y[i];//copio SOLO i termini noti

        }

    }

    public static void main( String[] args ) {
        double[][] m = {{1, 2},
                {3, 4}};
        Gauss g = new Gauss(m, new double[2]);
        System.out.println(g);
        Gauss g2 = g.clone();
        System.out.println(g2);
        System.out.println(g2.determinante());
        System.out.println(g2);

    }

    protected void triangolarizza() {
        int n = getN();
        for (int j = 0; j < n; ++j) {
            if (Mat.sufficientementeProssimi(a[j][j], 0D)) {
                //operazione di pivoting
                int p = j + 1;
                for (; p < n; ++p)
                    if (!Mat.sufficientementeProssimi(a[p][j], 0D)) break;
                if (p == n) throw new RuntimeException("Sistema singolare");
                //scambia a[p] con a[j]
                double[] tmp = a[j];
                a[j] = a[p];
                a[p] = tmp;
            }

            //azzera gli elementi da a[j+1][j] sino ad a[n-1][j]
            for (int i = j + 1; i < n; ++i) {
                if (!Mat.sufficientementeProssimi(a[i][j], 0D)) {
                    //azzera a[i][j]
                    double c = a[i][j] / a[j][j];
                    //sottraimo dalla riga i la riga j moltiplicata per c
                    for (int k = j; k <= n; ++k)
                        a[i][k] = a[i][k] - c * a[j][k];
                }
            }
        }
    }//trinagolarizza

    protected double[] calcolaSoluzione() {
        int n = getN();
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; --i) {
            double secondo_membro = a[i][n];
            for (int j = i + 1; j < n; ++j)
                secondo_membro = secondo_membro - a[i][j] * x[j];
            x[i] = secondo_membro / a[i][i];
        }
        return x;
    }//calcolaSoluzione

    public double[] risolvi() {
        triangolarizza();
        double[] x = calcolaSoluzione();
        return x;
    }//risolvi

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        int n = getN();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (j == n)
                    sb.append(" | ");

                sb.append(String.format("%8.2f", a[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public double[][] getA() {
        return a;
    }

    public int determinante() {
        int n = getN();
        if (n != a.length) throw new IllegalArgumentException("Mat non quadrata");
        Gauss tmp = clone();
        tmp.triangolarizza();
        int det = 1;
        for (int j = 0; j < tmp.getN(); ++j)
            det *= tmp.a[j][j];

        return det;
    }

    @Override
    public Gauss clone() {
        try {
            Gauss g = (Gauss) super.clone();
            int n = g.getN();
            g.a = new double[n][n + 1];
            g.y = new double[n];
            for (int i = 0; i < n; ++i) {
                System.arraycopy(a[i], 0, g.a[i], 0, n); //copio la matrice vera e propria
                g.a[i][y.length] = y[i];//copio SOLO i termini noti

            }
            return g;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}//Gauss
