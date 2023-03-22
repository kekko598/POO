package poo.razionali;

import poo.util.Mat;

public class Razionale implements Comparable<Razionale> {
    private static int conta = 0;
    private final int NUM, DEN;

    public Razionale( int nUM, int dEN ) throws Exception {
        if (dEN == 0)
            throw new Exception("Denominatore nullo!");
        if (dEN < 0) {
            nUM = (-1) * nUM;
            dEN = (-1) * dEN;
        }
        int num = Math.abs(nUM);
        int den = Math.abs(dEN);
        if (num != 0) {
            int MCD = Mat.mcd(num, den);
            //semplifico direttamente
            num = num / MCD;
            den = den / MCD;
        }
        NUM = num;
        DEN = den;
        conta++;
    }

    public Razionale( Razionale r ) {
        NUM = r.NUM;
        DEN = r.DEN;
        conta++;

    }

    public static int RazionaliEsistenti() {
        return conta;
    }

    public static void comparaRazionali( Razionale r1, Razionale r2 ) {
        if (r1.compareTo(r2) > 0) System.out.println(r1 + " è maggiore di " + r2);
        else if (r1.compareTo(r2) < 0) System.out.println(r1 + " è minore di " + r2);
        else System.out.println(r1 + " è uguale di " + r2);
    }

    public static void main( String[] args ) throws Exception {
/*
        System.out.print("inserisci quanti razionali inserire->");
        Razionale[] razionali = new Razionale[new Scanner(System.in).nextInt()];
        int i = 0;
        System.out.println("Saranno aggiunti in memoria " + razionali.length + " razionali");

        loop:
        while (i < razionali.length)
            try {
                {
                    System.out.print("inserisci num->");
                    int n = new Scanner(System.in).nextInt();
                    System.out.print("inserisci den->");
                    int d = new Scanner(System.in).nextInt();
                    razionali[i] = new Razionale(n, d);
                    i++;
                }
            } catch (Exception e) {
                System.out.println("metti den > 0 !!!!");
                continue loop;
            }

        System.out.println(Arrays.toString(razionali));


 */

        Razionale r1 = new Razionale(5, 3);
        Razionale r2 = new Razionale(4, 7);
        comparaRazionali(r1, r2);
    }

    protected void finalize() {
        conta--;
    }

    public Razionale add( Razionale r ) throws Exception {
        int mcm = Mat.mcm(DEN, r.DEN);
        int num = (mcm / DEN) * NUM + (mcm / r.DEN) * r.NUM;
        int den = mcm;
        return new Razionale(num, den);
    }

    public Razionale mul( Razionale r ) throws Exception {
        return new Razionale(NUM * r.NUM, DEN * r.DEN);
    }

    public Razionale mul( int s ) throws Exception {
        return new Razionale(NUM * s, DEN);
    }

    public Razionale div( Razionale r ) throws Exception {
        return new Razionale(NUM * r.DEN, DEN * r.NUM);
    }

    public int getNUM() {
        return NUM;
    }

    public int getDEN() {
        return DEN;
    }

    @Override
    public String toString() {
        if (NUM == 0) return "0";
        return NUM + "/" + DEN;
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Razionale razionale)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return NUM == razionale.NUM && DEN == razionale.DEN;
    }

    @Override
    public int hashCode() {
        int result = NUM;
        result = 31 * result + DEN;
        return result;
    }

    @Override
    public int compareTo( Razionale o ) {
        int mcm = Mat.mcm(DEN, o.DEN);
        int n1 = (mcm / DEN) * NUM;
        int n2 = (mcm / o.DEN) * o.NUM;
        if (n1 > n2)
            return 1;
        if (n1 == n2)
            return 0;
        return -1;

    }


}
