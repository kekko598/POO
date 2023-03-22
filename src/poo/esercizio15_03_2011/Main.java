package poo.esercizio15_03_2011;

import java.io.IOException;

public class Main {
    public static void main( String[] args ) throws IOException {
        OccorrenzaConcatenata m = new OccorrenzaConcatenata("/Users/francesco/Desktop/f1","/Users/francesco/Desktop/f2",3,2);
        System.out.println(m);
        System.out.println("Singola occorrenza:");
        System.out.println(m.caricoSingolomatch());
    }
}
