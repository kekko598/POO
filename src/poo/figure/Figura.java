package poo.figure;

import poo.geometria.FiguraPiana;

public abstract class Figura implements FiguraPiana {
    private final double dim;

    protected Figura( double dim ) {
        if (dim <= 0) throw new IllegalArgumentException();
        this.dim = dim;
    }

    public abstract double perimetro();

    public abstract double area();

    protected double getDim() {
        return dim;
    }

}

