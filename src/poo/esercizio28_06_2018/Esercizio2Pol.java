package poo.esercizio28_06_2018;

import poo.esercizio23_07_2019.ListaConcatenata;
import poo.polinomi.Monomio;
import poo.polinomi.Polinomio;
import poo.polinomi.PolinomioAstratto;

import java.util.Iterator;

public class Esercizio2Pol extends PolinomioAstratto {
    ListaConcatenata<Monomio> polinomio = new ListaConcatenata<>();

    public static void main( String[] args ) {
        Polinomio p = new Esercizio2Pol();
        Polinomio.parse("x^2-4", p);
        Polinomio p2 = new Esercizio2Pol();
        Polinomio.parse("2x+2", p2);
        System.out.println(p.add(p2));
        System.out.println(p.mul(p2));

    }

    @Override
    public Polinomio factory() {
        return new Esercizio2Pol();
    }

    @Override
    public void add( Monomio n ) {
        if (n.getCOEFF() == 0) return;
        Iterator<Monomio> it = polinomio.iterator();
        boolean flag = false;
        while (it.hasNext() && !flag) {
            Monomio mc = it.next();
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
