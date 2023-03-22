package poo.agendina;

import java.util.Iterator;

public abstract class AgendinaAstratta<T> implements Agendina {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(10);
        for (Nominativo n : this)
            sb.append(n + "\n");

        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Agendina)) return false;
        if (o == this) return true;
        Agendina v = (Agendina) o;
        Iterator<Nominativo> i1 = this.iterator();
        Iterator<Nominativo> i2 = v.iterator();
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
        for (Nominativo x : this)
            h *= M + x.hashCode();
        return h;
    }
}
