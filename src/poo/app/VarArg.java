package poo.app;

public class VarArg {
    public static void main( String... args ) {
        int m1 = max(10, 12, 30);
        int m2 = max(5, 7, 20, 1, 2, 4, 10);
        System.out.println("M1=" + m1);
        System.out.println("M2=" + m2);
    }

    public static int max( int... x ) {
        int m = x[0];
        for (int a : x)
            if (a > m)
                m = a;
        return m;
    }

}
