package poo.util;

import java.util.Iterator;

public interface CollezioneOrdinata<T extends Comparable<? super T>> extends Iterable<T> {
    default int size() {
        int c = 0;
        for (T e : this) c++;
        return c;
    }//size

    default boolean contains( T x ) {
        for (T y : this) {
            if (y.equals(x)) return true;
            if (y.compareTo(x) > 0) return false;
        }
        return false;
    }//contains

    default void clear() {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }//clear

    default boolean isEmpty() {
        return !iterator().hasNext();
    }//isEmpty

    void add( T x );

    default void remove( T x ) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T y = it.next();
            if (y.equals(x)) {
                it.remove();
                return;
            }
            if (y.compareTo(x) > 0) return;
        }
    }//remove

    default T get( T x ) {
        for (T y : this) {
            if (y.equals(x)) return y;
            if (y.compareTo(x) > 0) return null;
        }
        return null;
    }//get
}//CollezioneOrdinata
