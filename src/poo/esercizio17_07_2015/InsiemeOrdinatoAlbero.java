package poo.esercizio17_07_2015;


import java.util.Comparator;
import java.util.Iterator;


public class InsiemeOrdinatoAlbero<T extends Comparable<? super T>> extends InsiemeOrdinatoAstratto<T> implements InsiemeOrdinato<T> {

    private Nodo<T> radice = null;
    private Comparator<T> c = Comparator.naturalOrder();

    public InsiemeOrdinatoAlbero( Comparator<T> c ) {
        this.c = c;
    }

    public InsiemeOrdinatoAlbero(InsiemeOrdinato<T> t)
    {
        for (T elem : t)
            add(elem);
    }
    public InsiemeOrdinatoAlbero() {
    }

    public static void main( String[] args ) {
        InsiemeOrdinato<Integer> albero = new InsiemeOrdinatoAlbero<>();
        albero.add(10);
        albero.add(2);
        albero.add(5);
        albero.add(-3);
        albero.add(-1);
        albero.add(7);
        albero.add(12);
        albero.add(4);
        System.out.println(albero);
        InsiemeOrdinato<Integer> alberoClone = new InsiemeOrdinatoAlbero<>(albero);
        alberoClone.remove(-1);alberoClone.add(100);
        System.out.println(alberoClone);
        System.out.println(albero.equals(alberoClone));
    }

    @Override
    public void add( T x ) {
        radice = add(radice, x);
    }

    private Nodo<T> add( Nodo<T> radice, T x ) {
        if (radice == null) {
            Nodo<T> n = new Nodo<>();
            n.info = x;
            n.sx = n.dx = null;
            return n;

        }
        if (c.compare(radice.info, x) >= 0) {
            radice.sx = add(radice.sx, x);
            return radice;
        }
        radice.dx = add(radice.dx, x);
        return radice;
    }

    @Override
    public void remove( T x ) {
        radice = remove(radice, x);
        //mi ritorna l'albero già modificato!
    }

    private Nodo<T> remove( Nodo<T> radice, T x ) {
        if (radice == null)
            return null;
        if (c.compare(radice.info, x) > 0) {
            radice.sx = remove(radice.sx, x);
            return radice;
        }
        if (c.compare(radice.info, x) < 0) {
            radice.dx = remove(radice.dx, x);
            return radice;
        }
        if (radice.sx == null && radice.dx == null)
            return null;
        // caso nodo foglia
        if (radice.sx == null) //nodo col solo figlio dx
            return radice.dx;
        if (radice.dx == null) //nodo col solo figlio sx
            return radice.sx;
            /*
              nodo con due figli
             */

        if (radice.dx.sx == null) {
            //la radice del sotto albero destro è essa stessa il minimo nel sotto albero destro del nodo candidato
            radice.info = radice.dx.info; //promozione
            radice.dx = radice.dx.dx; //bypass
            return radice;
        }
                /*
                caso più generale, troviamo il nodo più a sinistra
                del sottoalbero destro del nodo da togliere
                 */
        Nodo<T> padre = radice.dx, figlio = padre.sx;
        while (figlio.sx != null) //fin quando il figlio sx ha un filio sx a sua volta
        {
            padre = figlio;
            figlio = figlio.sx;

        }
        radice.info = figlio.info; //promozione
        padre.sx = figlio.dx;
        return radice;
    }

    public void visitaSimmetrice( Insieme<T> ls ) {
        visitaSimmetrice(ls, radice);
    }

    private void visitaSimmetrice( Insieme<T> ls, Nodo<T> radice ) {
        if (radice != null) {
            visitaSimmetrice(ls, radice.sx);
            ls.add(radice.info);
            visitaSimmetrice(ls, radice.dx);

        }
    }

    @Override
    public void sort( Comparator<T> c ) {
        System.out.println("Sort non implementato per gli alberi");
    }

    @Override
    public InsiemeOrdinato<T> factory() {
        return new InsiemeOrdinatoAlbero<>();
    }

    @Override
    public Iterator<T> iterator() {
        return new ABRiterator();
    }

    class Nodo<E> {
        E info;
        Nodo<E> sx, dx;

    }

    class ABRiterator implements Iterator<T> {
        private final Insieme<T> lista = new InsiemeConcatenato<>();
        private final Iterator<T> it;
        private T curr = null;

        public ABRiterator() {
            visitaSimmetrice(lista);
            it = lista.iterator();
        }

        @Override
        public boolean hasNext() {
            if (lista.isEmpty()) return false;
            return it.hasNext();
        }

        @Override
        public T next() {
            return curr = it.next();
        }

        @Override
        public void remove() {
            if (curr == null)
                throw new IllegalStateException();
            it.remove(); //tolgo anche dalla lista
            InsiemeOrdinatoAlbero.this.remove(curr);
            curr = null;
        }
    }
}
