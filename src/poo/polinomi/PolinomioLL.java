package poo.polinomi;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class PolinomioLL extends PolinomioAstratto {
    private LinkedList<Monomio> polinomio = new LinkedList<>();

    @Override
    public Polinomio factory() {
        //covarianza del tipo di ritorno
        return new PolinomioLL();
    }

    @Override
    public void add( Monomio n ) {
        if (n.getCOEFF() == 0) return;
        ListIterator<Monomio> it = polinomio.listIterator();
        boolean flag = false;
        while (it.hasNext() && !flag) {
            Monomio mc = it.next();
            if (mc.equals(n)) {
                mc = mc.add(n);
                if (mc.getCOEFF() == 0) {
                    it.remove();
                } else {
                    it.set(mc);
                }
                flag = true;
            } else if (mc.compareTo(n) > 0) {
                it.previous();
                it.add(n);
                flag = true;
            }
        }
        if (!flag) it.add(n);
    }

    @Override
    public Iterator<Monomio> iterator() {
        return polinomio.iterator();
    }
}
