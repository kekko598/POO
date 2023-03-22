package poo.geometria;

public class Triangolo implements FiguraPiana {
    private final Punto p1;
    private final Punto p2;
    private final Punto p3;
    private double a, b, c;//lati triangolo

    public Triangolo( Punto p1, Punto p2, Punto p3 ) {
        a = p1.distanza(p2);
        b = p2.distanza(p3);
        c = p3.distanza(p1);

        if (a >= (b + c) || b >= (a + c) || c >= (a + b)) //esistenza triangolo
        {
            // System.out.println("Triangolo inesistente.");
            // System.exit(-1);
            throw new IllegalArgumentException("Triangolo inesistente");
            //eccezzione è un oggetto siccome deriva da una classe

        } else {// sono in aliasing, quindi soggetto a perdita dei punti del triangolo
            //this.p1 = p1;
            //this.p2 = p2;
            //this.p3 = p3;

            //non in aliasing
            this.p1 = new Punto(p1);
            this.p2 = new Punto(p2);
            this.p3 = new Punto(p3);


        }


    }

    public Triangolo( Triangolo t ) { // sono in aliasing
        //  p1=t.p1;
        //  p2=t.p2;
        //  p3=t.p3;
        // non sono in aliasing
        p1 = new Punto(t.p1);
        p2 = new Punto(t.p2);
        p3 = new Punto(t.p3);

    }

    public static void main( String[] args ) {
        Punto p1 = new Punto();
        Punto p2 = new Punto(7, -4);
        Punto p3 = new Punto(12, 10);
        Punto p = p1;
        Punto q = p2;
        Punto r = p3;
        Triangolo t = new Triangolo(p, q, r);
        System.out.println(t);
        System.out.println("Area " + t.area());
        System.out.println("Perimetro " + t.perimetro());
        System.out.println("Il triangolo è del tipo " + t.tipo());
    }

    public Punto getP1() {
        return p1;
    }


    public Punto getP2() {
        return p2;
    }


    public Punto getP3() {
        return p3;
    }

    public Punto[] getVeritici() {
        Punto[] v = new Punto[3];
        v[0] = new Punto(p1);
        v[1] = new Punto(p2);
        v[2] = new Punto(p3);
        return v;

    }

    public double perimetro() {
        return a + b + c;
    }

    public double area() {
        double s = perimetro() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public Tipo tipo() {

        if (a == b && b == c && a == c) return Tipo.EQUILATERO;
        if (a == b || b == c || a == c) return Tipo.ISOSCELE;
        return Tipo.SCALENO;

    }

    @Override
    public String toString() {
        return "Triangolo -> (p1," + p1 + ",p2" + p2 + ",p3" + p3 + ")";
    }

    public enum Tipo {ISOSCELE, SCALENO, EQUILATERO}

}

