package poo.sistema;

import poo.util.Mat;

public class GaussDiagonale extends Gauss {

    public GaussDiagonale( double[][] a, double[] y ) {
        super(a, y);
    }

    @Override
    protected void triangolarizza() {
        int n = getN();
        for (int j = 0; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (!Mat.sufficientementeProssimi(a[i][j], 0D)) {
                    double c = a[i][j] / a[j][j];
                    for (int k = j; k <= n; k++)
                        a[i][k] = a[i][k] - a[j][k] * c;
                }
            }
        }
    }

    @Override
    protected double[] calcolaSoluzione() {
        int n = getN();
        double[] x = new double[n];
        for (int i = 0; i < n; i++)
            x[i] = a[i][n] / a[i][i];
        return x;
    }
}
