package poo.app;

import poo.polinomi.Monomio;
import poo.polinomi.Polinomio;
import poo.polinomi.PolinomioLL;

public class DemoPolinomi {
    public static void main( String[] args ) {
        Polinomio p = new PolinomioLL();
        p.add(new Monomio(5, 1));
        p.add(new Monomio(3, 3));
        p.add(new Monomio(2, 2));
        p.add(new Monomio(1, 20));
        System.out.println(p);
        System.out.println(
                p.derivata()
                        .derivata()
                        .derivata());
    }
}
