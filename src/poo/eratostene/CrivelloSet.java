package poo.eratostene;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class CrivelloSet extends CrivelloAstratto {
    private Set<Integer> crivello = new LinkedHashSet<>();
    private long n;

    public CrivelloSet( long n ) {
        if (n <= 1) throw new IllegalArgumentException();
        this.n = n;
        for (int i = 2; i <= n; i++)
            crivello.add(i);//popolo il crivello da 2 a n
    }

    public static void main( String[] args ) {
        Crivello c = new CrivelloSet(200);
        c.filtra();
        System.out.println(c);
    }

    @Override
    public void filtra() {
        for (int x = 2; x <= n / 2; x++) { //se arrivo a n/2 non ci sono più multipli di un numero
            if (crivello.contains(x)) {// x è sicuramente primo
                int multiplo = x * 2;
                while (multiplo <= n) {//elimino tutti i multipli di x
                    crivello.remove(multiplo);
                    multiplo += x; //ottengo il prossimo multiplo su m che è di x
                }
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return crivello.iterator();
    }
}
