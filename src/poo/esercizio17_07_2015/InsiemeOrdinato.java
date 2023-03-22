package poo.esercizio17_07_2015;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface InsiemeOrdinato<T extends Comparable<? super T>> extends Insieme<T> {
    default T first() {
        return iterator().next();
    }

    default T last() {
        Iterator<T> it = this.iterator();
        T elem = null;
        while (it.hasNext())
            elem = it.next();
        if (elem == null) throw new NoSuchElementException();
        return elem;
    }

    InsiemeOrdinato<T> factory();


}
