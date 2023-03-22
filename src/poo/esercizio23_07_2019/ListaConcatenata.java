package poo.esercizio23_07_2019;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaConcatenata<T> implements Iterable<T> {
    private Nodo<T> testa = null;
    private int size;

    public static void main( String[] args ) {
        ListaConcatenata<Integer> ls = new ListaConcatenata<>();
        ls.add(10);
        ls.add(2);
        ls.add(1);
        ls.add(60);
        ls.add(50);
        System.out.println(ls);
        System.out.println(ls);

    }

    public static <T extends Comparable<? super T>> void bubbleSort( ListaConcatenata<T> v ) {
        boolean scambio = true;
        ListaConcatenata<T>.Nodo<T> c1;
        ListaConcatenata<T>.Nodo<T> c = null;
        while (scambio) {
            scambio = false;
            c1 = v.testa;
            while (c1.next != c) {
                if (c1.info.compareTo(c1.next.info) > 0) {
                    T tmp = c1.info;
                    c1.info = c1.next.info;
                    c1.next.info = tmp;
                    scambio = true;
                }
                c1 = c1.next;
            }
            c = c1;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Nodo<T> cur = null, pre = null;

            @Override
            public boolean hasNext() {
                return (cur == null) ? testa != null : cur.next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                if (cur == null)
                    cur = testa; // sono in testa
                else {
                    pre = cur;
                    cur = cur.next;
                }
                return cur.info;
            }

            @Override
            public void remove() {
                ListaConcatenata.this.remove(cur.info);
            }
        };
    }

    public int size() {
        return size;
    }

    public void add( T x ) {
        Nodo<T> n = new Nodo<>();
        n.info = x;
        if (testa == null)
            testa = n;
        else {
            Nodo<T> c = testa;
            while (c.next != null)
                c = c.next;
            c.next = n;
        }
        size++;
    }

    public void remove( T x ) {
        if (!contains(x)) throw new NoSuchElementException();
        if (testa.info.equals(x))//rimuovo il primo nodo in caso
            testa = testa.next;
        else {
            Nodo<T> cur = testa, pre = null;
            do {
                pre = cur;
                cur = cur.next;
            } while (!cur.info.equals(x));
            pre.next = cur.next;
        }
        size--;
    }

    public int[] elementi() {
        Nodo<T> cur = testa;
        int[] tmp = new int[size];
        for (int i = 0; i < size; i++) {
            tmp[i] = (int) cur.info;
            cur = cur.next;
        }
        return tmp;
    }

    public boolean contains( T x ) {
        Nodo<T> cor = testa;
        while (cor != null) {
            if (cor.info.equals(x))
                return true;
            cor = cor.next;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        Nodo<T> cor = testa;
        sb.append("{");
        while (cor != null) {
            sb.append(cor.info);
            if (cor.next != null)
                sb.append(", ");
            cor = cor.next;
        }
        sb.append("}");
        return sb.toString();
    }

    private class Nodo<E> {
        E info;
        Nodo<E> next;
    }
}
