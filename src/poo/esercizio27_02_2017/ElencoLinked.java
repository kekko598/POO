package poo.esercizio27_02_2017;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ElencoLinked<T> extends ElencoAstratto<T> {

    private List<T> elenco = new LinkedList<>();

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {
        elenco.clear();
    }

    @Override
    public void add( T elem ) {
        elenco.add(elem);
    }

    @Override
    public void remove( T elem ) {
        elenco.remove(elem);
    }

    @Override
    public boolean contains( T elem ) {
        return elenco.contains(elem);
    }

    @Override
    public T get( T elem ) {

        for (T e : elenco)
            if (elem.equals(e))
                return e;
        return null;
    }

    @Override
    public Comparator<T> getComparator() {
        return (Comparator<T>) Comparator.naturalOrder();
    }

    @Override
    public void setComparatorAndSort( Comparator<T> c ) {
        elenco.sort(c);
    }

    @Override
    public Iterator<T> iterator() {
        return elenco.iterator();
    }


}
