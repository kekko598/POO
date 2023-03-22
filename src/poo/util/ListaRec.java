package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaRec<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {

    private Nodo<T> testa = null;
    private int size = 0;

    public static void main( String[] args ) {
        ListaRec<Integer> a = new ListaRec<>();
        a.add(1);
        a.add(2);
        a.add(-10);
        a.add(2);
        a.add(2);
        a.add(2);
        System.out.println("Dim: " + a.size() + " Lista: " + a);
       // a.removeAllAccurrence(2);
       // System.out.println("Dim: " + a.size() + " Lista: " + a);
        ListaRec<Integer> a2 = a.copy();
        System.out.println(a2);
        System.out.println(a.equals(a2));
        ListaRec<Integer> a3 = new ListaRec<>();
        a3.copyRec(a);
        System.out.println(a3);


    }

    public ListaRec<T> copy(){
        ListaRec<T> t = new ListaRec<>();
        for (T value : this) t.add(value);
        return t;
    }
    @Override
    public int size() {
        return size(testa);
    }
    public void copyRec(ListaRec<T> l){
        testa=copyRec(l.testa);
    }

    private Nodo<T> copyRec( Nodo<T> lista ) {
        if(lista==null)
            return null;
        Nodo<T> n = new Nodo<>();
        n.info=lista.info;
        n.next=copyRec(lista.next);
        return n;
    }


    private int size( Nodo<T> lista ) {
        if (lista == null) return 0;
        return 1 + size(lista.next);
    }

    @Override
    public boolean contains( T x ) {
        return contains(testa, x);
    }

    private boolean contains( Nodo<T> lista, T x ) {
        if (lista == null)
            return false;
        if (lista.info.equals(x))
            return true;
        if (lista.info.compareTo(x) > 0)
            return false;
        return contains(lista.next, x);

    }

    @Override
    public T get( T x ) {
        return get(testa, x);
    }

    private T get( Nodo<T> lista, T x ) {
        if (lista == null)
            return null;
        if (lista.info.equals(x))
            return lista.info;
        if (lista.info.compareTo(x) > 0)
            return null;
        return get(lista.next, x);
    }

    @Override
    public void add( T x ) {
        //il metodo privato ritorna un nodo su testa, o la testa stessa
        //in caso sia vuota la lista
        testa = add(testa, x);
    }

    private Nodo<T> add( Nodo<T> lista, T x ) {
        if (lista == null) {
            //inserimento in testa
            lista = new Nodo<>();
            lista.info = x;
            lista.next = null;
            return lista;
        } else if (lista.info.compareTo(x) >= 0) {
            // elem più grande della testa ed eventualmente dal secondo in poi
            Nodo<T> n = new Nodo<>();
            n.info = x;
            n.next = lista;
            return n;
        } else {
            //ricorsivamente richiamo per iterare il processo di if e di else if
            lista.next = add(lista.next, x);
            return lista;
        }
    }

    @Override
    public void remove( T x ) {
        testa = remove(testa, x);

    }

    public void removeAllAccurrence(T x)
    {
        while (contains(x))
            testa=remove(testa,x);
    }

    private Nodo<T> remove( Nodo<T> lista, T x ) {
        if (lista == null) //lista vuota
            return null;
        if (lista.info.equals(x)) //bypass perché ho trovato l'elemento
            return lista.next;
        if (lista.info.compareTo(x) > 0) //ritorno la lista perché non c'è l'elemento
            return lista;
        lista.next = remove(lista.next, x); //avanzo ricorsivamente nella lista
        return lista;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        toString(testa, sb);
        sb.append("]");
        return sb.toString();
    }

    private void toString( Nodo<T> lista, StringBuilder sb ) {
        if (lista == null)
            return;
        sb.append(lista.info);
        if (lista.next != null) sb.append(", ");
        toString(lista.next, sb);
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaRecIteratore();
    }

    private class ListaRecIteratore implements Iterator<T> {

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
            } else {
                //Bypass
                pre.next = cur.next;
                //cur è isolato attualmente, allora lo porto indietro
                //in modo che evito di fare subito la remove subito dopo

                cur = pre;
                size--;

            }

        }


    }
}
