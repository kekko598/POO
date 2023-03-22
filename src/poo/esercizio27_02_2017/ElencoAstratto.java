package poo.esercizio27_02_2017;

import java.util.Iterator;

public abstract class ElencoAstratto<T> implements Elenco<T> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T t : this) {
            sb.append(t).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Elenco<?>)) return false;
        if (o == this) return true;
        Iterator<? extends T> i1 = iterator();
        Iterator<T> i2 = ((Elenco<T>) o).iterator();
        while (i1.hasNext()) {
            if (!i1.next().equals(i2.next()))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int M = 83;
        int h = 0;
        Iterator<T> i1 = iterator();
        while (i1.hasNext())
            h *= M + iterator().next().hashCode();
        return h;
    }
}
