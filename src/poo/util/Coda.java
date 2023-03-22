package poo.util;

public interface Coda<T> extends Iterable<T> {
    int size();

    boolean isEmpty();

    boolean isFull();

    boolean contains( T x );

    void clear();

    void poll( T x );

    default void offer( T x ) {
        poll(x);
    }

    ;

    T remove(); // toglie l'elemento più vecchio e lo ritorna

    default T poll() {
        return remove();
    }

    ;

    T peek();//ritorna il primo elem senza toglierlo
}
