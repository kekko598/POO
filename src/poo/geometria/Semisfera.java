package poo.geometria;

public class Semisfera extends Disco implements FiguraSolida {

    public Semisfera( double X, double Y, double r ) {
        super(X, Y, r);
    }

    public Semisfera( double r ) {
        super(r);
    }

    public Semisfera( Semisfera s ) {
        super(s.getX(), s.getY(), s.getR());
    }

    @Override
    public double perimetro() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double area() {
        return 2 * Math.PI * getR();
    }

    @Override
    public double volume() {
        return 4 / 3 * Math.PI * getR() * getR() * getR();
    }

    @Override
    public double areaLaterale() {
        return area() - super.area(); //area tutta semisfera - area disco
    }

    @Override
    public double areaDiBase() {
        return super.area();
    }

    @Override
    public String toString() {
        return "Semisfera di raggio R=" +
                String.format("%1.2f", getR() +
                        " con centro C=(" + getX() + "," + getX() + ")");
    }
}
