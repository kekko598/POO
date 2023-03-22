package poo.app;

import poo.date.Data;
import poo.geometria.Disco;
import poo.geometria.Punto;
import poo.geometria.Triangolo;
import poo.razionali.Razionale;

import java.util.Arrays;

public class DemoObject {

    public static void main( String[] args ) throws Exception {
        Object[] v = new Object[5];
        v[4] = new Data();
        v[3] = new Triangolo(new Punto(1, 4), new Punto(4, 8), new Punto(3, 12));
        v[2] = "Java is OO";
        v[1] = new Razionale(4, 6);
        v[0] = new Punto();

        if (v[1] instanceof Razionale r) {
            System.out.println(r.add(new Razionale(12, 8)));

        }
        System.out.println();
        for (int i = 0; i < v.length; i++)
            System.out.println(v[i]);

        System.out.println();
        Object[] w = {"JAVA", new Disco(4.5), "JULIA", new Razionale(12, 20)};
        System.out.println(Arrays.toString(w));


    }
}
