package poo.agendina;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class AgendinaLL extends AgendinaAstratta {
    private LinkedList<Nominativo> tabella = new LinkedList<>();


    @Override
    public void aggiungi( Nominativo n ) {
        ListIterator<Nominativo> lit = tabella.listIterator();
        while (lit.hasNext()) {
            Nominativo y = lit.next();
            if (y.compareTo(n) > 0) {//elemento più grande, faccio paragone per mettere n dietro y
                lit.previous();
                lit.add(n);// inserisco n dietro y !
                return;
            } else if (y.compareTo(n) == 0) { //sostituisco elemento corrente
                lit.set(n);
                return;
            }
            lit.add(y); //aggiungo alla fine
        }
    }

    @Override
    public Iterator<Nominativo> iterator() {
        return tabella.iterator();
    }

    @Override
    public int size() {
        return tabella.size();
    }
}
