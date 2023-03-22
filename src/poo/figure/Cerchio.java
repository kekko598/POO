package poo.figure;

public class Cerchio extends Figura {

    public Cerchio( double r ) {
        super(r);
    }

    public double getR() {
        return getDim();
    }

    @Override
    public double perimetro() {
        return 2 * getR() * Math.PI;
    }

    @Override
    public double area() {
        return getR() * getR() * Math.PI;

    }

    @Override
    public String toString() {
        return "Cerchio con r=" + getR();
    }
}
