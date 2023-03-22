package poo.recursion;

import java.math.BigInteger;

public class Ricorsione {
    public static BigInteger pow( int a, int n ) {
        if (n < 0)
            throw new IllegalArgumentException();
        return pot(a, n);

    }

    public static BigInteger pot( int a, int n ) {
        if (n == 0)
            return BigInteger.valueOf(1);
        return BigInteger.valueOf(a).multiply(pow(a, n - 1));
    }

    public static BigInteger factorial( int a ) {
        if (a < 0)
            throw new IllegalArgumentException();
        return factorialPrivate(a);

    }

    private static BigInteger factorialPrivate( int a ) {
        if (a == 0)
            return BigInteger.valueOf(1);
        return BigInteger.valueOf(a).multiply(factorial(a - 1));

    }

    public static boolean palindroma( String s ) {
        if (s.length() <= 1)
            return true;
        if (s.charAt(0) != s.charAt(s.length() - 1))
            return false; // confronto il primo con l'ultimo carattere

        return palindroma(s.substring(1, s.length() - 1));
    }

    public static boolean palindromaITE( String s ) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static int max( int[] a ) {
        if (a.length == 0) throw new IllegalArgumentException();
        return maxPrivate(a, 0, a.length - 1);
    }

    public static int min( int[] a ) {
        if (a.length == 0) throw new IllegalArgumentException();
        return minPrivate(a, 0, a.length - 1);
    }

    private static int maxPrivate( int[] a, int inf, int sup ) {
        if (inf == sup) return a[sup];
        int med = (inf + sup) / 2;
        int m1 = maxPrivate(a, inf, med);
        int m2 = maxPrivate(a, med + 1, sup);
        return Math.max(m1, m2);

    }

    private static int minPrivate( int[] a, int inf, int sup ) {
        if (inf == sup) return a[sup];
        int med = (inf + sup) / 2;
        int m1 = minPrivate(a, inf, med);
        int m2 = minPrivate(a, med + 1, sup);
        return Math.min(m1, m2);
    }

    private static int somma( int[] v, int dimArray ) {
        if (dimArray == 0)
            return 0;
        return v[dimArray - 1] + somma(v, dimArray - 1);
    }

    public static int somma( int[] v ) {
        return somma(v, v.length);
    }

    private static int prodotto( int[] v, int dimArray ) {
        if (dimArray == 0)
            return 1;
        return v[dimArray - 1] * prodotto(v, dimArray - 1);
    }

    public static int prodotto( int[] v ) {
        return prodotto(v, v.length);
    }

    public static void main( String[] args ) {
        /*
        System.out.println(pow(2, 10));
        System.out.println(factorial(10));
        System.out.println(palindroma("osso"));
        System.out.println(max(new int[]{1, 2, 3, 4, 5, 6, 70, 10}));
         */
        System.out.println(somma(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }
}
