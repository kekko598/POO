package poo.figure;

public class Rettangolo extends Figura {
    private final double altezza;

    public Rettangolo( double base, double altezza ) {
        super(base);
        if (altezza <= 0) throw new IllegalArgumentException();
        this.altezza = altezza;
    }

    public double getBase() {
        return getDim();
    }

    public double getAltezza() {
        return altezza;
    }

    @Override
    public double perimetro() {
        return getBase() * 2 + altezza * 2;
    }

    @Override
    public double area() {
        return getBase() * getAltezza();
    }

    @Override
    public String toString() {
        return "Rettangolo con altezza H=" + altezza +
                " e Base B=" + getBase();
    }
}
