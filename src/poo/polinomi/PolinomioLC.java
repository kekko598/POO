package poo.polinomi;

import poo.esercizio28_06_2018.Esercizio2Pol;
import poo.util.ListaOrdinataConcatenata;

import java.util.Iterator;


public class PolinomioLC extends PolinomioAstratto {
    private ListaOrdinataConcatenata<Monomio> polinomio = new ListaOrdinataConcatenata<>();

    @Override
    public Polinomio factory() {
        return new PolinomioLC();
    }

    @Override
    public void add( Monomio n ) {
        if (n.getCOEFF() == 0) return;
        Iterator it = polinomio.iterator();
        boolean flag = false;
        while (it.hasNext() && !flag) {
            Monomio mc = (Monomio) it.next();
            if (mc.equals(n)) {
                mc = mc.add(n);
                if (mc.getCOEFF() == 0) {
                    it.remove();
                } else {
                    polinomio.add(mc);
                }
                flag = true;
            } else if (mc.compareTo(n) > 0) {
                polinomio.add(n);
                flag = true;
            }
        }
        if (!flag) polinomio.add(n);
    }

    @Override
    public Iterator<Monomio> iterator() {

        return polinomio.iterator();
    }
}
