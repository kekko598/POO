package poo.agendina;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class AgendinaMap extends AgendinaAstratta {
    private Map<Nominativo, Nominativo> tabella = new TreeMap<>();//uso cognome nome come chiave

    @Override
    public void aggiungi( Nominativo n ) {
        //tabella.remove(n);
        tabella.put(n, n);
        //se c'è un nominativo presente fa un rimpiazzo ossia update su value, ma non sulla chiave per prefisso e telefono!
        //se usiamo la remove, in caso, serve per aggiornare prefisso e telefono su value e refreshare la key per non rendere dati inconsisenti
    }

    @Override
    public Iterator<Nominativo> iterator() {
        //meglio l'iteratore sui valori, perché abbiamo prefisso e telefono sempre aggiornati data un eventuale refresh sui values!
        //inoltre, l'ordinamento delle chiavi implica l'ordinamento dei valori
        return tabella.values().iterator();
    }

    @Override
    public int size() {
        return tabella.size();
    }

    @Override
    public Nominativo cerca( Nominativo n ) {
        return tabella.get(n);
    }

    @Override
    public void rimuovi( Nominativo n ) {
        tabella.remove(n);
    }
}

