package poo.esercizio12_02_2011;

import java.io.IOException;
import java.util.Iterator;

public abstract class Collezione implements Iterable<Parola> {

    @Override
    public String toString() {
        Iterator<Parola> it = iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Collezione)) return false;
        if (o == this) return true;
        Iterator<Parola> i1 = iterator();
        Iterator<Parola> i2 = ((Collezione) o).iterator();
        if (size() != ((Collezione) o).size()) return false;
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
        Iterator<Parola> i1 = iterator();
        while (i1.hasNext())
            h *= M + iterator().next().hashCode();
        return h;
    }

    public int size() {
        int c = 0;
        Iterator<Parola> it = iterator();
        while (it.hasNext())
            c++;
        return c;
    }

    protected abstract CollezioneSet factory() throws IOException;
}
