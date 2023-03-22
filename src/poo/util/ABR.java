package poo.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ABR<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {


    private Nodo<T> radice = null;
    private int size, contatoreFoglie;


    public static void main( String[] args ) {
        ABR<Integer> albero = new ABR<>();
        /*
        albero.add(34);
        albero.add(-2);
        albero.add(14);
        albero.add(18);
        albero.add(7);
        albero.add(40);
        albero.add(3);
        albero.add(6);
        albero.add(17);
        albero.add(-5);
        albero.add(37);
        albero.remove(-2);
        /*
        albero.add(2);
        albero.add(3);
        albero.add(7);
        albero.add(9);
        albero.add(5);
        albero.add(8);
         */

        /*
        //albero non bilanciato
        /*




        System.out.println("Visita in profondità/simmetrica/in-order (sx-radix-dx): "+albero);
        List<Integer> l1 = new LinkedList<>();albero.visitaAnticipata(l1);
        System.out.println("Visita anticipata/pre-order (radix-sx-dx): "+ l1);
        List<Integer> l2 = new LinkedList<>();albero.visitaPosticipata(l2);
        System.out.println("Visita posticipata/post-order (sx-dx-radix):  "+ l2);
        List<Integer> l3 = new LinkedList<>();albero.visitaPerLivelliITE(l3);
        System.out.println("Visita per livelli (partendo dalla radice si naviga da sinistra a destra): "+l3);
       ABR<Integer> a = albero.copia();
        System.out.println("Copia speculare: "+ a);
         */

/*
        albero.add(5);
        albero.add(3);
        albero.add(2);
        albero.add(5);
        albero.add(7);
        albero.add(8);

        prof
        albero.add(17);
        albero.add(2);
        albero.add(5);
        albero.add(-1);
        albero.add(10);
        albero.add(39);
        albero.add(-4);
*/
        //albero.add(8);albero.add(12);albero.add(15);albero.add(13);albero.add(18);albero.add(16);albero.add(20);albero.add(0);
        //albero.add(50);albero.add(20);albero.add(70);albero.add(10);albero.add(40);albero.add(90);albero.add(30);albero.add(80);albero.add(100);albero.add(35);
        /*
        albero.add(17);
        albero.add(2);
        albero.add(5);
        albero.add(-1);
        albero.add(10);
        albero.add(39);
        albero.add(-4);
        albero.add(40);
        albero.add(50);
         */


        //albero.add(18);albero.add(10);albero.add(25);albero.add(23);albero.add(28);

        /*
        albero.add(10);
        albero.add(2);
        albero.add(5);
        albero.add(-3);
        albero.add(-1);
        albero.add(7);
        albero.add(12);
        albero.add(4);
        albero.remove(2);
         */

        albero.add(16);albero.add(10);albero.add(12);albero.add(-3);albero.add(-5);albero.add(4);albero.add(7);albero.add(-1);albero.add(17);albero.add(13);
        albero.remove(-3);


        System.out.println("Visita a livelli: " + albero);
        List<Integer> ls = new LinkedList<>();
        albero.visitaAnticipata(ls);
        System.out.println("Visita anticipata: " + ls);
        ls.clear();
        albero.visitaPosticipata(ls);
        System.out.println("Visita posticipata: " + ls);
        ls.clear();
        albero.visitaSimmetrica(ls);
        System.out.println("Visita simmetrica: " + ls);
        System.out.println("Altezza complessiva albero: " + albero.altezza());
        System.out.println("Albero bilanciato? " + albero.bilanciato());
        System.out.println("Numero foglie dell'albero: " + albero.numeroFoglie());
        List<Integer> foglie = new ArrayList<>();
        albero.frontiera(foglie);
        List<Integer> foglieInverse = new ArrayList<>();
        albero.frontieraInversa(foglieInverse);
        System.out.println("Foglie dell'albero: " + foglie);
        System.out.println("Foglie dell'albero inverse: " + foglieInverse);



    }

    public int size() {
        return size(radice);
    }

    private int size( Nodo<T> radice ) { //cardinalità
        if (radice == null)
            return 0;
        return 1 + size(radice.figlioSX) + size(radice.figlioDX);
    }



    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof ABR)) return false;
        if (o == this) return true;
        ABR<T> abr = (ABR<T>) o;
        return equals(radice, abr.radice);
    }

    private boolean equals( Nodo<T> a1, Nodo<T> a2 ) {
        if (a1 == null && a2 == null) return true;
        if (a1 != null && a2 == null || a1 == null && a2 != null ) return false;
        if (!a1.info.equals(a2.info)) return false;
        return equals(a1.figlioSX, a2.figlioSX)
                &&
                equals(a1.figlioDX, a2.figlioDX);
    }



    @Override
    public void clear() {
        radice = null;
    }

    public boolean contains( T x ) {
        return contains(radice, x);
    }

    private boolean contains( Nodo<T> albero, T x ) {
        if (albero == null) return false;
        if (albero.info.equals(x)) return true;
        return albero.info.compareTo(x) > 0 ? contains(albero.figlioSX, x) : contains(albero.figlioDX, x);
    }

    public T get( T x ) {
        return get(radice, x);
    }

    private T get( Nodo<T> radice, T x ) {
        if (radice == null) return null;
        if (radice.info.equals(x)) return radice.info;
        return radice.info.compareTo(x) > 0 ? get(radice.figlioSX, x) : get(radice.figlioDX, x);

    }

    @Override
    public void add( T x ) {
        // if (contains(x)) return;
        radice = add(radice, x);
    }

    private Nodo<T> add( Nodo<T> radice, T x ) {
        if (radice == null) {
            Nodo<T> n = new Nodo<>();
            n.info = x;
            n.figlioSX = n.figlioDX = null;
            return n;
        }
        if (radice.info.compareTo(x) >= 0) {
            radice.figlioSX = add(radice.figlioSX, x);
            return radice;
        }
        radice.figlioDX = add(radice.figlioDX, x);
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
        if (radice.info.compareTo(x) > 0) {
            radice.figlioSX = remove(radice.figlioSX, x);
            return radice;
        }
        if (radice.info.compareTo(x) < 0) {
            radice.figlioDX = remove(radice.figlioDX, x);
            return radice;
        }
        if (radice.figlioSX == null && radice.figlioDX == null)
            return null;
        // caso nodo foglia
        if (radice.figlioSX == null) //nodo col solo figlio dx
            return radice.figlioDX;
        if (radice.figlioDX == null) //nodo col solo figlio sx
            return radice.figlioSX;
            /*
              nodo con due figli
             */

        if (radice.figlioDX.figlioSX == null) {
            //la radice del sotto albero destro è essa stessa il minimo nel sotto albero destro del nodo candidato
            radice.info = radice.figlioDX.info; //promozione
            radice.figlioDX = radice.figlioDX.figlioDX; //bypass
            return radice;
        }
                /*
                caso più generale, troviamo il nodo più a sinistra
                del sottoalbero destro del nodo da togliere
                 */
        Nodo<T> padre = radice.figlioDX, figlio = padre.figlioSX;
        while (figlio.figlioSX != null) //fin quando il figlio sx ha un figlio sx a sua volta
        {
            padre = figlio;
            figlio = figlio.figlioSX;

        }
        radice.info = figlio.info; //promozione
        padre.figlioSX = figlio.figlioDX;
        return radice;
    }

    //visita in ordine/simmetrica/->Sx-radice-Dx
    //visita post-order/posticipata-> sx-dx-radice
    //visita in pre-ordine/anticipata ->radice-sx-dx
    public void visitaPosticipata( List<T> ls ) {
        visitaPosticipata(radice, ls);
    }

    private void visitaPosticipata( Nodo<T> radice, List<T> ls ) {
        if (radice != null) {
            visitaPosticipata(radice.figlioSX, ls);
            visitaPosticipata(radice.figlioDX, ls);
            ls.add(radice.info);

        }
    }

    public void visitaAnticipata( List<T> ls ) {
        visitaAnticipata(radice, ls);
    }

    private void visitaAnticipata( Nodo<T> radice, List<T> ls ) {
        if (radice != null) {
            ls.add(radice.info);
            visitaAnticipata(radice.figlioSX, ls);
            visitaAnticipata(radice.figlioDX, ls);
        }
    }

    public void visitaSimmetrica( List<T> ls ) {
        visitaSimmetrica(radice, ls);
    }

    private void visitaSimmetrica( Nodo<T> radice, List<T> ls ) {
        if (radice != null) {
            visitaSimmetrica(radice.figlioSX, ls);
            ls.add(radice.info);
            visitaSimmetrica(radice.figlioDX, ls);
        }
    }

    public void visitaPerLivelliITE( List<T> ls ) {
        //radice - Sotto albero SX e sotto albero DX
        Coda<Nodo<T>> coda = new CodaConcatenata<>();
        coda.poll(radice);
        while (!coda.isEmpty()) {
            Nodo<T> n = coda.poll();
            ls.add(n.info); //visita N
            if (n.figlioSX != null)
                coda.poll(n.figlioSX);
            if (n.figlioDX != null)
                coda.poll(n.figlioDX);
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new ABRIterator();
    }

    private void addIte( T x ) {
        Nodo<T> n = new Nodo<>();
        n.info = x;
        n.figlioSX = null;
        n.figlioDX = null;
        if (radice == null)
            radice = n;
        else {
            Nodo<T> padre = null, figlio = radice;
            Direzione dir = null;
            while (figlio != null) {
                padre = figlio;
                if (figlio.info.compareTo(x) > 0) {
                    dir = Direzione.Sinistra;
                    figlio = figlio.figlioSX;
                } else {
                    dir = Direzione.Destra;
                    figlio = figlio.figlioDX;
                }
            }
            if (dir == Direzione.Sinistra)
                padre.figlioSX = n;
            else
                padre.figlioDX = n;

        }
    }

    public boolean bilanciato() {
        /*
        teniamoci in ogni nodo una sorta di contatori una a SX e un altro a DX, poi si deve
        fare la differenza tra i due contatori. Il contatore deve essere o +1,0,-1
         */
        return bilanciato(radice);
    }

    private boolean bilanciato( Nodo<T> radice ) {
        if (radice == null) return true;
        int c1 = size(radice.figlioSX);
        int c2 = size(radice.figlioDX);
        if (Math.abs(c1 - c2) > 1) return false;
        return bilanciato(radice.figlioSX) || bilanciato(radice.figlioDX);
    }

    public void build( T[] a ) {
        radice = build(a, 0, a.length - 1);
    }

    private Nodo<T> build( T[] a, int inf, int sup ) {

        Nodo<T> r = new Nodo<>();
        int med = (inf + sup) / 2;
        if (inf > sup) return null;
        r.info = a[med];
        r.figlioSX = build(a, inf, med - 1);
        r.figlioDX = build(a, med + 1, sup);
        return r;
    }


    public int altezza() {

        return altezza(radice,0);
    }

    private int altezza( Nodo<T> radice,int h) {
        if(radice==null)
            return h;
        int h1=h,h2=h;
        if(radice.figlioSX!=null)
            h1 = altezza(radice.figlioSX,h+1);
        if(radice.figlioDX!=null)
            h2 = altezza(radice.figlioDX,h+1);
        return Math.max(h1,h2);
    }

    public ABR<T> copia() {
        ABR<T> a = new ABR<>();
        for (T t : this) a.add(t);
        return a;
    }

   public void copiaRec(ABR<T> albero){
        radice=copiaRec(albero.radice);
   }

    private Nodo<T> copiaRec( Nodo<T> radice ) {
        if(radice==null)
            return null;
        Nodo<T> n = new Nodo<>();
        n.info=radice.info;
        n.figlioSX=copiaRec(radice.figlioSX);
        n.figlioDX=copiaRec(radice.figlioDX);
        return n;
    }

    public int numeroFoglie() {
        numeroFoglie(radice);
        return contatoreFoglie;
    }

    private void numeroFoglie( Nodo<T> radice ) {
        if (radice != null) {
            if (radice.figlioSX == null && radice.figlioDX == null)
                contatoreFoglie++;
            numeroFoglie(radice.figlioSX);
            numeroFoglie(radice.figlioDX);
        }

    }

    public void frontiera( List<T> l ) {
        frontiera(radice, l);
    }

    private void frontiera( Nodo<T> radice, List<T> l ) {
        if (radice != null) {
            if (radice.figlioSX == null && radice.figlioDX == null)
                l.add(radice.info);
            frontiera(radice.figlioSX, l);
            frontiera(radice.figlioDX, l);

        }
    }
    public void frontieraInversa( List<T> l ) {
        frontieraInversa(radice, l);
    }

    private void frontieraInversa( Nodo<T> radice, List<T> l ) {
        if (radice != null) {
            if (radice.figlioSX == null && radice.figlioDX == null)
                l.add(radice.info);
            frontieraInversa(radice.figlioDX, l);
            frontieraInversa(radice.figlioSX, l);
        }
    }

    private enum Direzione {Sinistra, Destra}

    private static class Nodo<E> {
        E info;
        Nodo<E> figlioSX, figlioDX;
    }

    class ABRIterator implements Iterator<T> {

        private List<T> lista = new LinkedList<>();
        private Iterator<T> it;
        private T curr = null;

        public ABRIterator() {
            visitaPerLivelliITE(lista);
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
            ABR.this.remove(curr);
            curr = null;
        }
    }


}

