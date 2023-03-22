package poo.app;

import poo.figure.Cerchio;
import poo.figure.Quadrato;
import poo.figure.Rettangolo;
import poo.geometria.FiguraPiana;
import poo.geometria.Punto;
import poo.geometria.Triangolo;

public class TestFigure {
    public static void main( String[] args ) {

        FiguraPiana[] v = {
                new Cerchio(5.4),
                new Quadrato(4.893),
                new Rettangolo(10, 7.8),
                new Cerchio(4.9),
                new Triangolo(new Punto(), new Punto(2, 3), new Punto(5, 10))
        };
        areaMassima(v);


    }

    static void areaMassima( FiguraPiana... f ) {
        double am = f[0].area();
        FiguraPiana fam = f[0];
        for (FiguraPiana F : f) {
            if (F.area() > am) {
                fam = F;
                am = F.area();
            }
        }
        System.out.println("La figura è " + fam + " di area massima=" + am);
    }
}