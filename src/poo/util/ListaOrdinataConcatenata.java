package poo.util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaOrdinataConcatenata<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {

    private Nodo<T> testa = null;
    private int size = 0;
    private int contaModifiche = 0;

    public static void main( String[] args ) {
        CollezioneOrdinata<Integer> list = new ListaOrdinataConcatenata<>();
        list.add(1);
        list.add(2);
        list.add(-10);
        list.add(-9);
        System.out.println(list);
        list.remove(-10);
        System.out.println(list);

    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains( T x ) {
        Nodo<T> cor = testa;
        while (cor != null) {
            if (cor.info.equals(x))
                return true;
            if (cor.info.compareTo(x) > 0)
                return false;
            cor = cor.next;
        }
        return false;
    }

    @Override
    public void clear() {
        testa = null;
        size = 0;
        contaModifiche++;

    }

    @Override
    public boolean isEmpty() {
        return size == 0 && testa == null;
    }

    @Override
    public void add( T x ) {

        Nodo<T> n = new Nodo<>();
        n.info = x;
        n.next = null;
        if (testa == null || testa.info.compareTo(x) > 0) //capolista inserisco in testa
        {
            //aggiungi in lista vuota con null successore e sia puntato dalla testa
            //l'inserimento in lista vuota è simile all'inserimento in testa qualora l'elemento va messo prima di un altro elemento

            n.next = testa; //se la lista non vuota allora sposto la testa dopo n
            testa = n; //con lista vuota testa = null, quindi diventa n
        } else {
            //lista certamente non vuota
            //inserisco l'elemento dopo il primo ma non certamente l'ultimo, diciamo nel mezzo più o meno

            Nodo<T> pre = testa, cor = testa.next;
            while (cor != null && cor.info.compareTo(x) < 0) {
                //il nuovo nodo ha come successore cor
                //e deve essere puntato da pre
                pre = cor;
                cor = cor.next;
            }
            //inserisco nodo tra pre e cor
            n.next = cor;
            pre.next = n;
        }
        size++;
        contaModifiche++;
    }

    @Override
    public void remove( T x ) {
        Nodo<T> cor = testa, pre = null;
        while (cor != null && cor.info.compareTo(x) < 0) {
            pre = cor;
            cor = cor.next;
        }
        if (cor != null && cor.info.equals(x)) {
            //nodo da rimuovere in testa
            if (cor == testa) {
                testa = cor.next;
            } else {
                //dal secondo in poi, facciamo il bypass
                pre.next = cor.next;
            }
            size--;
            contaModifiche++;
        }
    }

    @Override
    public T get( T x ) {
        Nodo<T> cor = testa;
        while (cor != null) {
            if (cor.info.equals(x)) return cor.info;
            if (cor.info.compareTo(x) > 0) return null;
            cor = cor.next;
        }
        return null;
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

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaOrdinataConcatenata<T> that = (ListaOrdinataConcatenata<T>) o;
        if (size != that.size) return false;
        Nodo<T> cor1 = testa;
        Nodo<T> cor2 = that.testa;
        while (cor1 != null) {
            if (!cor1.info.equals(cor2.info))
                return false;
            cor1 = cor1.next;
            cor2 = cor2.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int P = 83;
        int h = 0;
        Nodo<T> cor = testa;
        while (cor != null) {
            h *= P + cor.info.hashCode();
            cor = cor.next;
        }
        return h;
    }

    @Override
    public Iterator<T> iterator() {
        return new LOCIIteratore();
    }

    private static class Nodo<E> {
        E info;
        Nodo<E> next;
        //la faccio static perché non uso size e quindi posso fare a meno di uso implicito di inner e outer class
    }

    private class LOCIIteratore implements Iterator<T> {

        private Nodo<T> cur = null, pre = null;
        private int contatoreIteratore = contaModifiche;

        @Override
        public boolean hasNext() {
            if (cur == null)
                return testa != null;
            return cur.next != null;
        }

        @Override
        public T next() {
            if (contaModifiche != contatoreIteratore) throw new ConcurrentModificationException();
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
            if (contaModifiche != contatoreIteratore) throw new ConcurrentModificationException();
            if (pre == cur) throw new IllegalStateException();
            if (cur == testa) {
                testa = testa.next;
            } else {
                //Bypass
                pre.next = cur.next;
                //cur è isolato attualmente, allora lo porto indietro
                //in modo che evito di fare subito la remove subito dopo

                cur = pre;
                size--;
                contaModifiche++;
                contatoreIteratore++;
            }

        }
    }

}
