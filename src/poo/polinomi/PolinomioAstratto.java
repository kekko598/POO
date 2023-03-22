package poo.polinomi;

import java.util.Iterator;

public abstract class PolinomioAstratto implements Polinomio {

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        boolean flag = true; //true indica il primo monomio
        for (Monomio m : this) {
            if (flag) flag = !flag;
            else {
                if (m.getCOEFF() > 0) sb.append('+');
            }
            sb.append(m);
        }
        return sb.toString();
    }//toString

    public boolean equals( Object x ) {
        if (!(x instanceof Polinomio)) return false;
        if (x == this) return true;
        Polinomio p = (Polinomio) x;
        if (size() != p.size()) return false;
        Iterator<Monomio> i1 = iterator(), i2 = p.iterator();
        while (i1.hasNext()) {
            Monomio m1 = i1.next();
            Monomio m2 = i2.next();
            if (m1.getCOEFF() != m2.getCOEFF() || !m1.equals(m2))
                return false;
        }
        return true;
    }//equals

    public int hashCode() {
        final int M = 83;
        int h = 0;
        for (Monomio m : this)
            h = h * M + (String.valueOf(m.getCOEFF()) + String.valueOf(m.getGRADO())).hashCode();
        return h;
    }//hashCode

}//PolinomioAstratto
