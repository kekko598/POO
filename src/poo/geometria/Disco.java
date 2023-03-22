package poo.geometria;

public class Disco extends Punto implements FiguraPiana {
    private double r;

    public Disco() {
        super();//invoca il costrutture di default di punto
        if (r <= 0) throw new IllegalArgumentException("raggio non positivo");
        this.r = r;
    }

    public Disco( double X, double Y, double r ) {
        super(X, Y);
        if (r <= 0) throw new IllegalArgumentException("raggio non positivo");
        this.r = r;
    }

    public Disco( Punto p, double r ) {
        super(p);
        if (r <= 0) throw new IllegalArgumentException("raggio non positivo");
        this.r = r;
    }

    public Disco( Disco d ) { //costruttore di copia
        super(d.getX(), d.getY());
        this.r = d.r;
    }

    public Disco( double r ) {
        if (r <= 0) throw new IllegalArgumentException("raggio non positivo");
        this.r = r;
    }

    public static void main( String[] args ) {
        Punto p = new Punto(3, 5);
        Disco d = new Disco(7); // raggio 7 e x,y=0,0
        Disco d1 = new Disco(7);
        System.out.println(d.equals(d1));
        //   System.out.println(d.distanza(p));
    }

    public double getR() {
        return r;
    }

    @Override
    public String toString() {
        return "Disco con raggio " +
                "r=" + r +
                " e centro=" + super.toString();
    }

    @Override
    public double perimetro() {
        return 2 * Math.PI * r;
    }


    @Override
    public double area() {
        return Math.PI * r * r;
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Disco d)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return distanza(d, this) == 0 && this.r == d.r;
    }
}
