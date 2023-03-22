package poo.agendina;

import poo.util.ArrayVector;
import poo.util.Vector;

import java.util.Iterator;

import static poo.util.Array.ricercaBinaria;

public class AgendinaVector extends AgendinaAstratta<Nominativo> implements Agendina {
    private Vector<Nominativo> tabella;

    public AgendinaVector( int n ) {
        if (n <= 0) throw new IllegalStateException();
        tabella = new ArrayVector<>(n);
    }

    public AgendinaVector() {
        this(17);
    }

    ;

    @Override
    public int size() {
        return tabella.size();
    }

    @Override
    public void svuota() {
        tabella.clear();
    }

    @Override
    public void aggiungi( Nominativo n ) {
        int i = ricercaBinaria(tabella, n);
        if (i >= 0) tabella.set(i, n);
        else {
            i = 0;
            boolean flag = false;
            while (i < tabella.size() && !flag) {
                Nominativo x = tabella.get(i);
                if (x.compareTo(n) > 0) { //aggiungo in mezzo o al massimo dall'inizio
                    tabella.add(i, n);
                    flag = true;
                } else i++;
            }
            if (!flag)
                tabella.add(n); //aggiungo alla fine
        }
    }

    @Override
    public void rimuovi( Nominativo n ) {
        int i = ricercaBinaria(tabella, n);
        if (i == -1) return;
        tabella.remove(i);
    }

    @Override
    public Nominativo cerca( Nominativo n ) {
        int i = ricercaBinaria(tabella, n);
        if (i < 0) return null;
        return tabella.get(i);
    }

    @Override
    public Nominativo cerca( String prefisso, String telefono ) {
        for (Nominativo n : tabella)
            if (n.getPrefisso().equals(prefisso) &&
                    n.getTelefono().equals(telefono)) return n;

        return null;
    }


    @Override
    public Iterator<Nominativo> iterator() {
        return tabella.iterator();
    }
}
