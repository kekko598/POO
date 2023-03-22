package poo.esercizio12_01_2005;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class AziendaConcreta extends Azienda{

    private LinkedList<Stanza> stanze = new LinkedList<>();
    @Override
    public void aggiungiImpiegato( Persona p, Stanza s1 ) {

        stanze.forEach(
                (stanza)->
                        stanza.forEach((
                                personaInStanza ->{
            if(personaInStanza.equals(p))
                throw new RuntimeException("Persona "+ personaInStanza+ " già assegnata in stanza "+stanza.getTelefono());

        })));

        if(!stanze.contains(s1)) stanze.add(s1);
        s1.aggiungiPersona(p);
        Collections.sort(stanze);
    }

    @Override
    public Iterator<Stanza> iterator() {
        return stanze.iterator();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof AziendaConcreta stanzas)) return false;
        return stanze.equals(stanzas.stanze);
    }

    @Override
    public int hashCode() {
        return stanze.hashCode();
    }
}
