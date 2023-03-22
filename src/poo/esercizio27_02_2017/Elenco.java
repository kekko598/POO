package poo.esercizio27_02_2017;

import java.util.Comparator;

public interface Elenco<T> extends Iterable<T> {
    int size();

    void clear();

    void add( T elem );

    void remove( T elem );

    boolean contains( T elem );

    T get( T elem );

    Comparator<T> getComparator();

    void setComparatorAndSort( Comparator<T> c );
}
