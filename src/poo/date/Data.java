package poo.date;

import java.util.GregorianCalendar;

public class Data implements Comparable<Data> {
    private final int G, M, A;

    public Data() {
        GregorianCalendar gc = new GregorianCalendar();
        G = gc.get(GregorianCalendar.DAY_OF_MONTH);
        M = gc.get(GregorianCalendar.MONTH) + 1;
        A = gc.get(GregorianCalendar.YEAR);
    }

    public Data( int g, int m, int a ) {
        if (g < 1 || g > durata(m, a) || m < 1 || m > 12 || a < 0)
            throw new IllegalArgumentException();
        G = g;
        M = m;
        A = a;
    }

    public Data( Data d ) {
        G = d.G;
        M = d.M;
        A = d.A;
    }

    public static boolean bisestile( int a ) {
        if (a < 0) throw new IllegalArgumentException();
        if (a % 4 != 0) return false;
        return a % 100 != 0 || a % 400 == 0;
    }//bisestile

    public static int durata( int m, int a ) {
        if (m < 1 || m > 12 || a < 0) throw new IllegalArgumentException();
        return switch (m) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> bisestile(a) ? 29 : 28;
            default -> 30;
        };
    }//durata

    public static void main( String[] args ) {
        Data oggi = new Data();
        System.out.println("Giorno odierno=" + oggi.get(Cosa.GIORNO));
        Data domani = oggi.giornoDopo();
        System.out.println("Oggi e' il " + oggi);
        System.out.println("Domani e' il " + domani);
        //System.out.println(oggi.equals(domani));
        System.out.println(oggi.distanza(domani));

    }//main

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Data data)) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        return G == data.G && M == data.M && A == data.A;
    }

    @Override
    public int hashCode() {
        final int C = 83;
        int h = 0;
        h *= C + G;
        h *= C + M;
        h *= C + A;
        return h;
    }

    public int get( Cosa cosa ) {
        return switch (cosa) {
            case GIORNO -> G;
            case MESE -> M;
            default -> A;
        };
    }//get

    public Data giornoDopo() {
        int g = 0, m = 0, a = 0; //inizializzazioni fittizie
        if (G == durata(M, A)) {
            if (M == 12) {
                g = 1;
                m = 1;
                a = A + 1;
            } else {
                g = 1;
                m = M + 1;
                a = A;
            }
        } else {
            g = G + 1;
            m = M;
            a = A;
        }
        return new Data(g, m, a);
    }//Data

    public Data giornoPrima() {
        //TODO
        return null;
    }//giornoPrima

    public int distanza( Data d ) {
        //PRE: this Ë precedente a d
        Data d1 = this, d2 = d;
        if (d.compareTo(this) < 0) {
            d1 = d;
            d2 = this;
        }
        int cg = 0;
        while (d1.compareTo(d2) < 0) {
            cg++;
            d1 = d1.giornoDopo();
        }
        return cg;
    }//distanza

    public String toString() {
        return "" + G + "/" + M + "/" + A;
    }//toString


    @Override
    public int compareTo( Data o ) {
        Data d = (Data) o;
        if (d.A > A || d.M > M && A == d.A || M == d.M && G < d.G)
            return -1;
        if (equals(d)) return 0;
        return 1;
    }

    public enum Cosa {GIORNO, MESE, ANNO}


}//Data