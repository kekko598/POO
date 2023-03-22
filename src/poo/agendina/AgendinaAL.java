package poo.agendina;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.binarySearch;

public class AgendinaAL extends AgendinaAstratta {
    List<Nominativo> tabella;

    public AgendinaAL( int n ) {
        if (n <= 0) throw new IllegalArgumentException();
        this.tabella = new ArrayList<>(n);
    }

    public AgendinaAL() {
        this(17);
    }

    @Override
    public void aggiungi( Nominativo n ) {
        if (!tabella.contains(n))
            tabella.add(n);
        int i = binarySearch(tabella, n);
        if (i >= 0 && tabella.contains(n))
            tabella.set(i, n);

    }

    @Override
    public Iterator iterator() {
        return tabella.iterator();
    }

    @Override
    public int size() {
        return tabella.size();
    }
}
