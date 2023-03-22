package poo.eratostene;


import java.util.LinkedList;
import java.util.ListIterator;

public class CrivelloLL extends CrivelloAstratto {
    private LinkedList<Integer> crivello = new LinkedList<>();
    private long n;

    public CrivelloLL( long n ) {
        if (n <= 1) throw new IllegalArgumentException();
        this.n = n;
        for (int i = 2; i <= n; i++)
            crivello.addLast(i);
    }

    public static void main( String[] args ) {
        Crivello c = new CrivelloLL(1000);
        c.filtra();
        System.out.println(c);


    }

    @Override
    public void filtra() {
        for (int x = 2; x <= n / 2; x++) { //se arrivo a n/2 non ci sono più multipli di un numero
            if (contiene(x)) {// x è sicuramente primo
                int multiplo = x * 2;
                while (multiplo <= n) {//elimino tutti i multipli di x
                    rimuovi(multiplo);
                    multiplo += x; //ottengo il prossimo multiplo su m che è di x
                }
            }
        }
    }

    private void rimuovi( int elem ) {
        ListIterator it = crivello.listIterator();
        while (it.hasNext()) {
            int x = (int) it.next();
            if (x == elem) {
                it.remove();
                return;
            }
        }
    }

    private boolean contiene( int elem ) {
        ListIterator it = crivello.listIterator();
        while (it.hasNext()) {
            int x = (int) it.next();
            if (x == elem) return true;
            if (x > n) return false;

        }
        return false;
    }

    @Override
    public ListIterator<Integer> iterator() {
        return crivello.listIterator();
    }
}
