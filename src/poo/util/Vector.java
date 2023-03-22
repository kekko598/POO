package poo.util;

import java.util.Iterator;

public interface Vector<T> extends Iterable<T>, Cloneable {
    default int size() {
        int c = 0;
        for (T x : this) c++;
        return c;
    }

    ;

    //dimensione effettiva
    default int indexOf( T elem ) {
        int i = 0;
        for (T x : this) {
            if (x.equals(elem))
                return i;
            i++;
        }

        return -1;
    }

    ;

    default boolean contains( T elem ) {
        for (T e : this) {
            if (e.equals(elem)) return true;
        }
        return false;
    }

    ;

    //ritorna l'indice della prima occ di elem o -1
//ritorna true se elem è presente
    default T get( int indice ) {
        if (indice < 0 || indice >= size())
            throw new IndexOutOfBoundsException();
        int i = 0;
        for (T e : this) {
            if (i == indice) return e;
            i++;
        }
        return null;
    }

    ;

    //ritorna l'oggetto in posizione indice
    T set( int indice, T elem ); //cambia l'oggetto in pos indice e ritorna il prec

    void add( T elem );

    //aggiunge elem alla fine
    void add( int indice, T elem );

    //aggiunge elem in indice (non sovrascrittura)
    default void remove( T elem ) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (elem.equals(it.next())) {
                it.remove();
                break;
            }
        }
    }

    ;

    default T remove( int indice ) {
        int i = 0;
        T x = null;
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            x = it.next();
            if (i == indice) {
                it.remove();
                break;
            }
            i++;
        }
        return x;
    }

    ;

    default void clear() {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    ;

    //rimuove la prima occorrenza di elem, se c'è
//rimuove e ritorna l'oggetto in pos indice
//svuota il vector
    default boolean isEmpty() {
        return size() == 0;
    }

    ;

    //ritorna true se il vector è vuoto
    Vector<T> subVector( int da, int a );
    //ritorna un subvector con gli elementi dalla pos «da» alla posizione «a», a escluso

}
