package poo.esercizio28_06_2018;


import java.math.BigInteger;

public class Esercizio3Fibonacci {
    private BigInteger[][] a = new BigInteger[][]{new BigInteger[]{BigInteger.ONE, BigInteger.ONE},
            new BigInteger[]{BigInteger.ONE, BigInteger.ZERO}};

    private static BigInteger[][] mul( BigInteger[][] a, BigInteger[][] b ) {
        BigInteger[][] c = new BigInteger[a.length][a.length];
        for (int i = 0; i < a.length; ++i) // fissano riga
            for (int j = 0; j < b[0].length; ++j) { // fissano colonna
                BigInteger ps = BigInteger.ZERO;
                //fai prodotto scalare tra riga i di a e colonna j di b
                for (int k = 0; k < a[0].length; ++k)
                    ps = ps.add(a[i][k].multiply(b[k][j]));
                c[i][j] = ps;
            }
        return c;
    }

    private static BigInteger[][] pow( BigInteger[][] a, int n ) {
        if (n == 1)
            return a;
        return mul(a, pow(a, n - 1));
    }

    private static long[][] unitary() {
        long[][] m =
                {
                        {1, 0},
                        {0, 1}};
        return m;
    }

    public static void main( String[] args ) {

        System.out.print(new Esercizio3Fibonacci().fibo(1000));

    }

    public BigInteger fibo( int n ) {
        if (n < 1)
            throw new IllegalArgumentException();
        if (n == 1 || n == 2)
            return BigInteger.ONE;
        return pow(a, n - 1)[0][0];
    }
}

