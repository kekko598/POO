package poo.geometria;

import poo.util.Mat;

public class Punto implements Cloneable{

    private double x, y; //variabili di istanza o campi. Rappresentano lo stato di un punto

    public Punto() {
        x = 0;
        y = 0;

    }  //costruttore di default mette il punto nell'origine, se non vengono inseriti parametri x,y JAVA userà in automatico questo costruttore di default

    public Punto( double X, double Y ) {
        x = X;
        y = Y;
    } //costruttore tradizionale

    public Punto( Punto p ) {
        x = p.x;
        y = p.y;
    } //costruttore di copia. Copio un oggetto partendone da uno già esistente, in questo caso un punto!

    //overloading tipi di parametri diversi
    public static double distanza( Punto p1, Punto p2 ) {
        return Math.sqrt(((p1.getX() - p2.getX()) * (p1.getX() - p2.getX())) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
    }

    public static Punto puntoMedio( Punto p1, Punto p2 ) {
        return new Punto((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public static void main( String[] args ) {
        /*
        Punto p1 = new Punto(5, 7);
        Punto p2 = new Punto(10, 2);
        Punto p3 = new Punto(p1);
        System.out.println(p2.getX());
        System.out.println(p1.getY());
        System.out.println("La distanza tra " + p1 + " e " + p2 + " ,è " + p1.distanza(p2));
        System.out.println("La distanza tra " + p1 + " e " + p2 + " ,è " + distanza(p1, p2));
        System.out.println(p1.equals(p3));
         */
        Punto p1 = new Punto();
        Punto p2 = p1.clone();
        System.out.println(p2.equals(p1));

    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Punto punto)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //return punto.x == this.x && punto.y == this.y;
        return Mat.sufficientementeProssimi(x, punto.x) && Mat.sufficientementeProssimi(y, punto.y);
    }

    @Override
    public int hashCode() {
        final int M = 83;
        int h = Double.valueOf(x).hashCode() * M + Double.valueOf(y).hashCode() * M;
        return h;
    }

    //i metodi accessori saranno i getter e i setter
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //metodo mutatore
    public void muovi( double _x, double _y ) {
        x = _x;
        y = _y;
    }

    //metodo accessore perché non ha cambiato lo stato dell'oggetto
    public double distanza( Punto p ) {
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));

    }

    public String toString() {
        return "(" + x + "," + y + ")";

    }


    @Override
    public Punto clone() {
        try {
            Punto clone = (Punto) super.clone();
            clone.x=x;
            clone.y=y;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
} //Punto

