package poo.figure;

public class Quadrato extends Figura {
    public Quadrato( double lato ) {
        super(lato);
    }

    @Override
    public double perimetro() {
        return getLato() * 4;
    }

    @Override
    public double area() {
        return getLato() * getLato();
    }

    public double getLato() {
        return getDim();
    }

    @Override
    public String toString() {
        return "Quadrato con L=" + getLato();
    }


}
