package poo.grafi;

import poo.grafo.Arco;
import poo.grafo.Grafo;
import poo.grafo.GrafoOrientato;
import poo.grafo.GrafoOrientatoImpl;

import java.util.*;

public final class Grafi {
    //visita in profondità
    public static <T> void depth_first( Grafo<T> g, T u, List<T> ls ) {
        Set<T> visitati = new HashSet<>();
        depth_first(g, u, ls, visitati);
    }

    private static <T> void depth_first( Grafo<T> g, T u, List<T> ls, Set<T> visitati ) {
        visitati.add(u);
        ls.add(u);
        Iterator<? extends Arco<T>> ad = g.adiacenti(u);
        while (ad.hasNext()) {
            Arco<T> adiacenti = ad.next();
            T v = adiacenti.getDestinazione();
            if (!visitati.contains(v)) {
                depth_first(g, v, ls, visitati);
            }
        }
    }

    //visita in ampiezza
    public static <T> void breadth_first( Grafo<T> g, T u, List<T> ls ) {
        LinkedList<T> coda = new LinkedList<>();
        Set<T> visitati = new HashSet<>();
        visitati.add(u);
        ls.add(u);
        coda.addLast(u);
        while (!(coda.isEmpty())) {
            T v = coda.removeFirst();
            Iterator<? extends Arco<T>> adiacenti = g.adiacenti(v);
            while (adiacenti.hasNext()) {
                Arco<T> arco = adiacenti.next();
                T w = arco.getDestinazione();
                if (!visitati.contains(w)) {
                    visitati.add(w);
                    ls.add(w);
                    coda.addLast(w);
                }
            }
        }
    }

    //Verifica ciclo in grafo: nodo entrata 0, togliamolo e continuiamo cosi, se riesco a svuotare il grafo allora è aciclico altimenti ha un ciclo
    public static <T> boolean aciclicoFattoDaMe( GrafoOrientato<T> g ) {
        T nodoIniziale = null;
        GrafoOrientato<T> tmp = (GrafoOrientato<T>) g.copia();
        LinkedList<T> coda = new LinkedList<>();
        Set<T> visitati = new HashSet<>();
        for (T nodo : g) {
            nodoIniziale = nodo;
            break;
        }

        visitati.add(nodoIniziale);
        coda.addLast(nodoIniziale);
        while (!(coda.isEmpty())) {
            if (tmp.toString() == "") return true;
            T v = coda.removeFirst();
            int gradoEntrataArco = tmp.gradoEntrata(v);
            Iterator<? extends Arco<T>> adiacenti = tmp.adiacenti(v);
            if (gradoEntrataArco == 0)
                tmp.rimuoviNodo(v);
            while (adiacenti.hasNext()) {
                Arco<T> arco = adiacenti.next();
                T w = arco.getDestinazione();
                if (!visitati.contains(w)) {
                    visitati.add(w);
                    coda.addLast(w);
                    gradoEntrataArco = tmp.gradoEntrata(w);
                    if (gradoEntrataArco == 0)
                        tmp.rimuoviNodo(w);

                }
            }
        }

        return false;
    }

    public static <T> boolean aciclico( GrafoOrientato<T> g ) {
        GrafoOrientato<T> tmp = (GrafoOrientato<T>) g.copia();
        boolean prosegui = false;
        do {
            T v = null;
            prosegui = false;
            for (T u : tmp)
                if (tmp.gradoEntrata(u) == 0) {
                    v = u;
                    prosegui = true;
                    break;
                }
            if (prosegui)
                tmp.rimuoviNodo(v);
        } while (tmp.numNodi() != 0 && prosegui);
        return tmp.numNodi() == 0;
    }

    public static <T> GrafoOrientato<T> raggiungibilitàMax( GrafoOrientato<T> g ) {
        GrafoOrientato<T> gr = (GrafoOrientato<T>) g.copia();
        for (int k = 2; k < g.numNodi(); k++)
            for (T i : g)
                for (T j : g)
                    for (T m : g)
                        if (g.esisteArco(i, m) && g.esisteArco(m, j))
                            gr.insArco(i, j);
        return gr;
    }

    public static void main( String[] args ) {
        GrafoOrientato<Integer> g = new GrafoOrientatoImpl<>();

/*

        g.insNodo("a");g.insNodo("b");g.insNodo("c");g.insNodo("d");g.insNodo("e");
        g.insArco("a","b");g.insArco("a","c");g.insArco("a","d");g.insArco("a","e");
        g.insArco("b","d");
        g.insArco("c","d");g.insArco("c","e");
        g.insArco("d","e");

*/
        g.insNodo(1);
        g.insNodo(2);
        g.insNodo(3);
        g.insNodo(4);
        g.insNodo(5);
        g.insNodo(6);
        g.insNodo(7);
        g.insArco(1, 2);
        g.insArco(1, 3);
        g.insArco(2, 4);
        g.insArco(4, 5);
        g.insArco(4, 6);
        g.insArco(3, 5);
        g.insArco(5, 2);
        g.insArco(5, 3);
        g.insArco(7, 6);


        System.out.println("----grafo----");
        System.out.println(g);
        System.out.println("Aciclico?(mio metodo) " + (aciclicoFattoDaMe(g) ? "Si" : "No"));
        System.out.println("Aciclico?(metodo Nigro) " + (aciclico(g) ? "Si" : "No"));

        List<Integer> ampiezza = new LinkedList<>();
        breadth_first(g, 1, ampiezza);
        List<Integer> profondità = new LinkedList<>();
        depth_first(g, 1, profondità);
        System.out.println("Visita in ampiezza: " + ampiezza);
        System.out.println("Visita in profondità: " + profondità);
        System.out.println();
        System.out.println("----max raggiungibilità del grafo----");
        System.out.println(raggiungibilitàMax(g));


    }

}

