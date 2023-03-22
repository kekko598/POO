package poo.geometria;

public class Cilindro extends Disco implements FiguraSolida {
    private final double h;

    public Cilindro( double r, double h ) {
        super(r);
        if (h <= 0) throw new IllegalArgumentException();
        this.h = h;
    }

    public Cilindro( double X, double Y, double r, double h ) {
        super(X, Y, r);
        if (h <= 0) throw new IllegalArgumentException();
        this.h = h;
    }

    public Cilindro( Cilindro c ) {
        super(c.getX(), c.getY(), c.getR());
        this.h = c.h;
    }

    public static void main( String[] args ) {
        Cilindro c = new Cilindro(5, 10);
        Cilindro c1 = new Cilindro(5, 10);
        System.out.println(c.equals(c1));


    }

    public double getH() {
        return h;
    }

    @Override
    public double perimetro() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double area() {
        return 2 * super.area() + super.perimetro() * h;
    }

    public double areaDiBase() {
        return super.area();
    }

    public double areaLaterale() {
        return super.perimetro() * h;
    }

    public double volume() {
        return areaDiBase() * h;
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Cilindro cilindro)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.volume() == cilindro.volume();
    }

    @Override
    public String toString() {
        return "Cilindro " +
                "con disco di altezza=" + h +
                " " + super.toString();
    }
}

