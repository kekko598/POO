package poo.app;

import poo.geometria.Punto;

public class MonteCarloPi {

    public static void main( String[] args ) {
        final int n = 100000; // n tentativi
        int eventi = 0;
        Punto origine = new Punto();
        double pigreco = 0;
        for (int i = 0; i < n; i++) {
            //effettuare un esperimento casuale
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            Punto p = new Punto(x, y);
            if (origine.distanza(p) <= 1) //raggio del cerchio che è 1
                eventi += 1;
            pigreco = (double) (4 * eventi) / n;

        }
        System.out.println("Pigreco approssimato " + pigreco + " Pigreco vero " + Math.PI);

    }
}
