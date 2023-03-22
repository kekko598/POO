package poo.agendina;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AgendinaSet extends AgendinaAstratta {
    private Set<Nominativo> tabella = new TreeSet<>();

    @Override
    public void aggiungi( Nominativo n ) {
        tabella.remove(n);
        tabella.add(n);
        //faccio una remove prima perché sennò va in errore l'add se esiste il nominativo. In altre parole,
        //in questo modo l'add si comporta pure come un set in automatico !
    }

    @Override
    public Iterator<Nominativo> iterator() {
        return tabella.iterator();
    }

    @Override
    public int size() {
        return tabella.size();
    }

    @Override
    public Nominativo cerca( Nominativo n ) {
        if (!(tabella.contains(n))) return null;
        Iterator<Nominativo> it = tabella.iterator();
        while (it.hasNext()) {
            Nominativo x = it.next();
            if (x.equals(n)) return x;
            if (x.compareTo(n) > 0) return null;
        }
        return null;
    }

    @Override
    public void svuota() {
        tabella.clear();
    }
}
