package poo.lambda;

public class sum {
    public static void main( String[] args ) {
        int s = 0;
        int n = 10000;
        for (int i = 0; i < n; i++)
            s += i;
        System.out.println((s * (s + 1)) / 2);
    }
}
