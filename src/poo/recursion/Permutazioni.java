package poo.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Permutazioni {
    private int[] a;

    public Permutazioni( int[] a ) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < a.length; i++)
            s.add(a[i]);
        if (s.size() != a.length)
            throw new IllegalArgumentException("Elementi non distinti");
        this.a = Arrays.copyOf(a, a.length);

    }

    public static void main( String[] args ) {
        new Permutazioni(new int[]{10, 20, 30}).permuta();
    }

    public void permuta() {
        permuta(0);
    }

    private void permuta( int i ) {
        //i è il prefisso
        if (i == a.length - 1)
            System.out.println(Arrays.toString(a));
        for (int j = i; j < a.length; j++) {
            //scambia a[i] con a[j]
            int park = a[i];
            a[i] = a[j];
            a[j] = park;
            permuta(i + 1);
            //rimetti a[i] e a[j] a posto con nuovo scambi
            park = a[i];
            a[i] = a[j];
            a[j] = park;
        }
    }
}
