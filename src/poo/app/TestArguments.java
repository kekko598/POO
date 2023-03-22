package poo.app;

import java.util.Arrays;

public class TestArguments {
    public static void main( String[] args ) {
        if (args.length == 0) {
            System.out.println("Argomenti del main assenti");
            System.exit(-1);
        }
        int[] v = new int[args.length];
        for (int i = 0; i < v.length; i++) {
            v[i] = Integer.parseInt(args[i]);
        }
        System.out.println(Arrays.toString(v));
    }
}
