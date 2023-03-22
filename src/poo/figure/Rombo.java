package poo.figure;

import poo.geometria.FiguraPiana;

public class Rombo extends Figura {
    private final double altezza;
    private double d1;

    public Rombo( double lato, double altezza ) {
        super(lato);
        if (altezza <= 0) throw new IllegalArgumentException();
        this.altezza = altezza;
        this.d1 = 1;
    }

    public Rombo( double lato, double altezza, double d1 ) {
        super(lato);
        if (altezza <= 0 && d1 <= 0) throw new IllegalArgumentException();
        this.altezza = altezza;
        this.d1 = d1;
    }

    public static void main( String[] args ) {
        FiguraPiana rombo = new Rombo(2, 10, 3);
        System.out.println(rombo.perimetro());

    }

    @Override
    public double perimetro() {
        return (getLato() * 4);
    }

    @Override
    public double area() {
        return getLato() * altezza;
    }

    public double getLato() {
        return getDim();
    }

    public double getD2() {
        return (2 * altezza) / d1;
    }

    public double getD1() {
        return d1;
    }

    @Override
    public String toString() {
        return "Rombo con L=" + getLato() + " H=" + altezza + " d1=" + getD1() +
                " D2=" + getD2();
    }
}

