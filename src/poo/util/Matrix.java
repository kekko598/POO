package poo.util;

import poo.sistema.Gauss;

public final class Matrix {
    private Matrix() {
    }

    public static int[][] add( int[][] a, int[][] b ) {
        //verifica che tutte le righe di a abbiano la stessa lunghezza
        for (int i = 1; i < a.length; ++i)
            if (a[i].length != a[0].length)
                throw new IllegalArgumentException("Prima matrice irregolare.");
        //verifica che tutte le righe di b abbiano la stessa lunghezza
        for (int i = 1; i < b.length; ++i)
            if (b[i].length != b[0].length)
                throw new IllegalArgumentException("Seconda matrici irregolare.");
        //verifica che a e b siano compatibili per l'addizione
        if (a.length != b.length || a[0].length != b[0].length)
            throw new IllegalArgumentException("Matrici incompatibili per la addizione.");

        int[][] c = new int[a.length][a[0].length]; //matrice somma

        for (int i = 0; i < a.length; ++i)
            for (int j = 0; j < a[0].length; ++j)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }//add

    public static int[][] mul( int[][] a, int[][] b ) {
        //verifica che tutte le righe di a abbiano la stessa lunghezza
        for (int i = 1; i < a.length; ++i)
            if (a[i].length != a[0].length)
                throw new IllegalArgumentException("Prima matrice irregolare.");
        //verifica che tutte le righe di b abbiano la stessa lunghezza
        for (int i = 1; i < b.length; ++i)
            if (b[i].length != b[0].length)
                throw new IllegalArgumentException("Seconda matrici irregolare.");
        //verifica che a e b siano compatibili per la moltiplicazione
        if (a[0].length != b.length)
            throw new IllegalArgumentException("Matrici incompatibili per la moltiplicazione.");

        int[][] c = new int[a.length][b[0].length]; //matrice prodotto

        for (int i = 0; i < a.length; ++i) // fissano riga
            for (int j = 0; j < b[0].length; ++j) { // fissano colonna
                int ps = 0;
                //fai prodotto scalare tra riga i di a e colonna j di b
                for (int k = 0; k < a[0].length; ++k)
                    ps = ps + a[i][k] * b[k][j];
                c[i][j] = ps;
            }
        return c;
    }//mul

    public static double[][] add( double[][] a, double[][] b ) {
        //verifica che tutte le righe di a abbiano la stessa lunghezza
        for (int i = 1; i < a.length; ++i)
            if (a[i].length != a[0].length)
                throw new IllegalArgumentException("Prima matrice irregolare.");
        //verifica che tutte le righe di b abbiano la stessa lunghezza
        for (int i = 1; i < b.length; ++i)
            if (b[i].length != b[0].length)
                throw new IllegalArgumentException("Seconda matrici irregolare.");
        //verifica che a e b siano compatibili per l'addizione
        if (a.length != b.length || a[0].length != b[0].length)
            throw new IllegalArgumentException("Matrici incompatibili per la addizione.");

        double[][] c = new double[a.length][a[0].length];

        for (int i = 0; i < a.length; ++i)
            for (int j = 0; j < a[0].length; ++j)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }//add

    public static double[][] mul( double[][] a, double[][] b ) {
        //verifica che tutte le righe di a abbiano la stessa lunghezza
        for (int i = 1; i < a.length; ++i)
            if (a[i].length != a[0].length)
                throw new IllegalArgumentException("Prima matrice irregolare.");
        //verifica che tutte le righe di b abbiano la stessa lunghezza
        for (int i = 1; i < b.length; ++i)
            if (b[i].length != b[0].length)
                throw new IllegalArgumentException("Seconda matrici irregolare.");
        //verifica che a e b siano compatibili per la moltiplicazione
        if (a[0].length != b.length)
            throw new IllegalArgumentException("Matrici incompatibili per la moltiplicazione.");

        double[][] c = new double[a.length][b[0].length]; //matrice prodotto

        for (int i = 0; i < a.length; ++i) // fissano riga
            for (int j = 0; j < b[0].length; ++j) { // fissano colonna
                double ps = 0;
                //fai prodotto scalare tra riga i di a e colonna j di b
                for (int k = 0; k < a[0].length; ++k)
                    ps = ps + a[i][k] * b[k][j];
                c[i][j] = ps;
            }
        return c;
    }//mul

    public static double[][] minore( double[][] a, int i, int j ) {
        //a matrice quadrata o rettangolare
        //i deve essere un indice di riga ammissibile di a
        //j deve essere un indice di colonna ammissibile di a
        //Obiettivo: creare e restituire la sotto matrice che
        //si ottiene da a eliminando la riga i e la colonna j
        if (a.length != a[0].length)
            throw new IllegalArgumentException("Matrice non quadrata");
        double[][] minore = new double[a.length - 1][a.length - 1];
        int x = 0, y = 0; //scorriamo con indici ammissibili per minore
        for (int r = 0; r < a.length; r++) {
            if (r == i)
                continue;
            for (int c = 0; c < a[r].length; c++) {
                if (c == j)
                    continue;
                minore[x][y] = a[r][c];
                y++;
            }
            x++;
            y = 0;
        }


        return minore;
    }//minore

    public static double[][] matriceInversa( double[][] a ) {
        if (detGauss(a) == 0)
            throw new IllegalArgumentException("Matrice non invertibile");
        int n = a.length;
        double[][] mI = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mI[j][j] = 1;
        double[][] matriceEstesa = new double[n][2 * n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                matriceEstesa[i][j] = a[i][j];//prima sotto meta n*n
                matriceEstesa[i][j + n] = mI[i][j];//seconda sotto meta n*2n
            }
        triangolarizza(matriceEstesa);
        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                inversa[i][j] = matriceEstesa[i][j];

        return inversa;
    }

    private static void triangolarizza( double[][] a ) {
        int n = a.length;
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
    }

    public static void print( int[][] m ) {
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j)
                System.out.printf("%7d", m[i][j]);
            System.out.println();
        }
    }//print

    public static void print( double[][] m ) {
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j)
                //occupa almeno 7 posti prima della virgola
                //e 2 dopo la virgola
                System.out.print(m[i][j] + "       ");
            System.out.println();
        }
    }//print

    public static void print( boolean[][] m ) {
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j)
                System.out.print(m[i][j]);
            System.out.println();
        }
    }//print

    public static int detGauss( double[][] mat ) {
        for (int i = 1; i < mat.length; ++i)
            if (mat[i].length != mat[0].length)
                throw new IllegalArgumentException("matrice irregolare.");
        return new Gauss(mat, riempiconTerminiNoti(mat)).determinante();
    }

    public static int detLaplace( int[][] mat ) {
        for (int i = 1; i < mat.length; ++i)
            if (mat[i].length != mat[0].length)
                throw new IllegalArgumentException("matrice irregolare.");
        return determinanteP(mat, mat.length);
    }

    private static double[] riempiconTerminiNoti( double[][] m ) {
        return new double[m.length];
    }

    private static int[] riempiconTerminiNoti( int[][] m ) {
        return new int[m.length];
    }

    private static int determinanteP( int[][] mat, int n ) {
        int D = 0; // Initialize result

        // Base case : if matrix contains single
        // element
        if (n == 1)
            return mat[0][0];

        // To store cofactors
        int[][] temp = new int[mat.length][mat.length];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < n; f++) {
            // Getting Cofactor of mat[0][f]
            getCofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f]
                    * determinanteP(temp, n - 1);

            // terms are to be added with
            // alternate sign
            sign = -sign;
        }

        return D;
    }

    private static void getCofactor( int[][] mat, int[][] temp, int p, int q, int n ) {
        int i = 0, j = 0;

        // Looping for each element of
        // the matrix
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    // Row is filled, so increase
                    // row index and reset col
                    // index
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }


    public static void main( String[] args ) {
        double[][] m1 = {
                {1, -2, 2},
                {1, 1, 3},
                {1, -3, 600}
        };
        int[][] m2 = {
                {1, -2, 2},
                {1, 1, 3},
                {1, -3, 600}
        };

        System.out.println(detGauss(m1));
        System.out.println(detLaplace(m2));

    }//main

    public static void print( Character[][] m ) {
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }


    }

    public static void print( long[][] m ) {
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
    }
}//Matrix
