package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CodaConcatenata<T> extends CodaAstratta<T> {


    private Nodo<T> inizio = null, fine = null;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean contains( T x ) {
        Nodo<T> cor = inizio;
        while (cor != null) {
            if (cor.info.equals(x))
                return true;
            cor = cor.next;
        }
        return false;
    }

    @Override
    public void clear() {
        inizio = fine = null;
        size = 0;
    }

    @Override
    public void poll( T x ) {
        Nodo<T> n = new Nodo<>();
        n.info = x;
        n.next = null;
        if (inizio == null)
            inizio = n;
        else fine.next = n;
        fine = n;
        size++;
    }

    @Override
    public T remove() {
        if (size == 0) throw new NoSuchElementException();
        T x = inizio.info;
        inizio = inizio.next;
        if (inizio == null) fine = null;
        size--;
        return x;
    }

    @Override
    public T peek() {
        if (size == 0) throw new NoSuchElementException();
        T x = inizio.info;
        return x;
    }

    @Override
    public Iterator iterator() {
        return new CodaConcatenataIterator();
    }

    private static class Nodo<E> {
        E info;
        Nodo<E> next;
    }

    private class CodaConcatenataIterator implements Iterator {
        private Nodo<T> pre = null, cor = null;

        @Override
        public boolean hasNext() {
            if (cor == null) return inizio != null;
            return cor.next != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (cor == null) cor = inizio;
            else {
                pre = cor;
                cor = cor.next;
            }
            return cor.info;
        }

        @Override
        public void remove() {
            if (pre == cor) throw new IllegalStateException();
            if (cor == inizio) {//rimozione in testa
                inizio = inizio.next;
                if (inizio == null) fine = null;
            } else if (cor == fine) { //rimuovo l'ultimo, perché ce ne sono almeno 2 in lista!
                pre.next = null;
                fine = pre;
            } else {
                pre.next = cor.next;//bypass
            }
            size--;
            cor = pre;
        }
    }
}
