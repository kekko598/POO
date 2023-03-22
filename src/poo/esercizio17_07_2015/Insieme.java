package poo.esercizio17_07_2015;

import java.util.Comparator;
import java.util.Iterator;

public interface Insieme<T> extends Iterable<T> {
    default int size() {
        int c = 0;
        for (T x : this)
            c++;
        return c;
    }

    default void clear() {
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    default boolean contains( T x ) {
        for (T e : this)
            if (e.equals(x))
                return true;
        return false;
    }

    void add( T x );

    default void remove( T x ) {
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            T e = it.next();
            if (e.equals(x)) {
                it.remove();
                return;
            }

        }
    }

    default void addAll( Insieme<T> i ) {
        for (T e : i)
            add(e);
    }

    default void removeAll( Insieme<T> i ) {
        for (T e : i)
            remove(e);
    }

    default void retainAll( Insieme<T> i ) {
        for (T e : this) {
            if (!i.contains(e))
                remove(e);
        }
    }

    default T get( T x ) {
        for (T e : this)
            if (e.equals(x))
                return e;
        return null;
    }

    void sort( Comparator<T> c );

    default boolean isEmpty() {
        return size() == 0;
    }

}
