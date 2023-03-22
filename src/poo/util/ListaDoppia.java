package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoppia<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
    private int size = 0;
    private Nodo<T> testa = null;

    public static void main( String[] args ) {
        ListaDoppia<Integer> list = new ListaDoppia<>();
        list.add(1);
        list.add(2);
        list.add(-10);
        list.add(100);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
            System.out.println(list);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add( T x ) {
        Nodo<T> n = new Nodo<>();
        n.info = x;
        if (testa == null || testa.info.compareTo(x) > 0) { //lista vuota
            n.next = testa;
            n.prior = null;
            if (testa != null) //se la testa ha un elemento, la vecchia testa avrà come prior proprio n
                testa.prior = n;
            testa = n;//la lista deve partire proprio da testa con n assegnato!
        } else {
            //lista non vuota, l'elemento va inserito sicuramente dopo il primo, diciamo anche alla fine in alcuni casi
            Nodo<T> pre = testa, cor = testa.next;
            while (cor != null && cor.info.compareTo(x) < 0) {
                pre = cor;
                cor = cor.next;
            }
            //inseriamo n tra pre e cor
            n.next = cor;
            if (cor != null)
                cor.prior = n;
            n.prior = pre;
            pre.next = n;

        }
        size++;
    }


    @Override
    public void remove( T x ) {
        Nodo<T> cor = testa;
        while (cor != null && cor.info.compareTo(x) < 0) {
            cor = cor.next;
        }
        if (cor != null && cor.info.equals(x)) {
            if (cor == testa) {
                testa = testa.next;
                if (testa != null)
                    testa.prior = null;
            } else {
                // cor.prior vado in dietro e poi faccio uno step avanti
                cor.prior.next = cor.next;
                if (cor.next != null) //vero dal secondo fino al penultimo
                    // cor.next vado in avanti e poi faccio uno step indietro
                    cor.next.prior = cor.prior;
            }
            size--;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LDiteratore();
    }

    private class LDiteratore implements Iterator<T> {

        private Nodo<T> cur = null, pre = null;

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
                cur = testa; // sono in testa
            else {
                pre = cur;
                cur = cur.next;
            }
            return cur.info;
        }

        @Override
        public void remove() {
            if (pre == cur) throw new IllegalStateException();
            if (cur == testa) {
                testa = testa.next;
                if (testa != null)
                    testa.prior = null;
            } else {
                //Bypass
                pre.next = cur.next;
                if (cur.next != null)
                    cur.next.prior = pre;
                cur = pre; //arretriamo cur
                size--;

            }

        }
    }
}
