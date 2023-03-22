package poo.util;

public final class Mat {
    private static double EPSILON = 1.0E-10;

    private Mat() {
    }

    private static int mcd2( int x, int y ) {
        if (y == 0) return x;
        return mcd2(y, x % y);
    }

    public static int mcd( int x, int y ) {
        if (x <= 0 || y <= 0) throw new IllegalArgumentException();
        return mcd2(x, y);
    }

    public static int mcm( int x, int y ) {
        if (y <= 0 || x <= 0) throw new IllegalArgumentException();
        return (x * y) / mcd(x, y);
    }

    public static void setEpsilon( final double EPS ) {
        if (EPS <= 0) throw new IllegalArgumentException();
        EPSILON = EPS;
    }

    public static double getEPSILON() {
        return EPSILON;
    }

    public static boolean sufficientementeProssimi( double x, double y ) {
        return Math.abs(x - y) <= EPSILON;
    }

    public static int rN( int x ) {
        if (x < 1)
            throw new IllegalArgumentException();
        String s = String.valueOf(x);
        int somma = sommaCifre(s, s.length());
        if (somma == 9)
            return 0;
        if (somma < 9)
            return somma;
        return rN(somma);
    }

    private static int sommaCifre( String x, int len ) {
        if (len == 0)
            return 0;
        String s = String.valueOf((x.charAt(len - 1) - '0'));
        int s1 = sommaCifre(x, len - 1);
        return Integer.parseInt(s) + s1;
    }

    public static void main( String[] args ) {
        System.out.println(rN(150));
    }
}
