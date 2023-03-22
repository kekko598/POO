package poo.esercizio17_07_2015;

import java.util.Iterator;

public abstract class InsiemeAstratto<T> implements Insieme<T> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int c = 0;
        for (T x : this) {
            sb.append(x);
            if (c < size() - 1)
                sb.append(" ,");
            c++;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Insieme<?>)) return false;
        if (o == this) return true;
        if(size()!=((Insieme<?>) o).size()) return false;
        Iterator<T> i1 = iterator();
        Iterator<T> i2 = (Iterator<T>) ((Insieme<?>) o).iterator();
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
        for (T x : this)
            h *= M + x.hashCode();
        return h;
    }

}
