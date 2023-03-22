package poo.esercizio19_02_2019;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class AlberoBinarioRicerca<T extends Comparable<? super T>> implements Iterable<T> {
    private Nodo<T> radice = null;

    public static void main( String[] args ) {
        AlberoBinarioRicerca<Integer> a = new AlberoBinarioRicerca<>();
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(0);
        Iterator<Integer> it = a.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new AlberoIterator();
    }

    public void add( T x ) {
        // if (contains(x)) return;
        radice = add(radice, x);
    }

    private Nodo<T> add( Nodo<T> radice, T x ) {
        if (radice == null) {
            Nodo<T> n = new Nodo<>();
            n.info = x;
            n.Fsx = n.Fdx = null;
            return n;
        }
        if (radice.info.compareTo(x) >= 0) {
            radice.Fsx = add(radice.Fsx, x);
            return radice;
        }
        radice.Fdx = add(radice.Fdx, x);
        return radice;
    }

    class Nodo<E> {
        E info;
        Nodo<E> Fsx, Fdx;
    }

    private class AlberoIterator implements Iterator<T> {
        private Stack<Nodo<T>> stack = new Stack<>();
        private Nodo<T> elemCorrente = null;

        public AlberoIterator() {
            add(radice);
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty())
                return false;
            return true;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            elemCorrente = stack.pop();
            add(elemCorrente.Fdx);
            return elemCorrente.info;
        }

        private void add( Nodo<T> radice ) {
            Nodo<T> n = radice;
            while (n != null) {
                stack.push(n);
                n = n.Fsx;
            }
        }
    }
}
