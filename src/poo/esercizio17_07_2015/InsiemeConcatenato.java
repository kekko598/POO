package poo.esercizio17_07_2015;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InsiemeConcatenato<T> extends InsiemeAstratto<T> {

    private Nodo<T> testa = null;
    private int size;

    public InsiemeConcatenato( Insieme<T> i ) {
        for (T elem : i)
            add(elem);
    }

    public InsiemeConcatenato() {
    }

    public static void main( String[] args ) {
        InsiemeConcatenato<Integer> i = new InsiemeConcatenato<>();
        i.add(1);
        i.add(0);
        i.add(-1);
        i.add(10);
        Insieme<Integer> i2 = new InsiemeConcatenato<>(i);
        i2.add(5);
        System.out.println(i2);
        System.out.println("Elementi effettivi " + i2.size());
        i2.remove(1);
        i2.remove(0);
        System.out.println(i2);
        System.out.println("Elementi effettivi " + i2.size());
        System.out.println(i);
        System.out.println("Elementi effettivi " + i.size());
        i.sort(Comparator.reverseOrder());
        System.out.println(i);


    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add( T x ) {
        Nodo<T> n = new Nodo<>();
        n.info = x;
        if (testa == null)
            testa = n;
        else {
            Nodo<T> cur = testa;
            while (cur.next != null)
                cur = cur.next;
            cur.next = n;
        }
        size++;
    }

    @Override
    public void remove( T x ) {
        if (contains(x)) {
            if (testa.info.equals(x))//rimuovo il primo nodo in caso
                testa = testa.next;
            else {//se non è in testa ispeziono il resto della lista concatenata
                Nodo<T> cur = testa, pre = null;
                do {
                    pre = cur;
                    cur = cur.next;
                } while (!cur.info.equals(x));
                pre.next = cur.next;//bypass
            }
            size--;
        }


    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Nodo<T> cur = null, pre = null;
            boolean rimuovibile = false;

            @Override
            public boolean hasNext() {
                if (cur == null)
                    return testa != null;
                return cur.next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                if (cur == null)
                    cur = testa;
                else {
                    pre = cur;
                    cur = cur.next;
                }
                rimuovibile = true;
                return cur.info;
            }

            @Override
            public void remove() {
                if (!rimuovibile) throw new IllegalStateException();
                rimuovibile = false;
                InsiemeConcatenato.this.remove(cur.info);
            }
        };
    }

    public void sort( Comparator<T> comparator ) {
        boolean scambio = true;
        Nodo<T> c1;
        Nodo<T> c = null;
        while (scambio) {
            scambio = false;
            c1 = testa;
            while (c1.next != c) {
                if (comparator.compare(c1.info, c1.next.info) > 0) {
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

    class Nodo<E> {
        E info;
        Nodo<E> next;

    }
}
