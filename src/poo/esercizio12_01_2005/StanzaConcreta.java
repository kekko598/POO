package poo.esercizio12_01_2005;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class StanzaConcreta extends Stanza{
    LinkedList<Persona> impiegati = new LinkedList<>();
    public StanzaConcreta(String telefono) {
        super(telefono);
    }

    @Override
    protected void aggiungiPersona( Persona p ) {
        impiegati.add(p);
        Collections.sort(impiegati);
    }

    @Override
    public Iterator<Persona> iterator() {
        return impiegati.iterator();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof StanzaConcreta personas)) return false;
        return impiegati.equals(personas.impiegati);
    }

    @Override
    public int hashCode() {
        return impiegati.hashCode();
    }
}
