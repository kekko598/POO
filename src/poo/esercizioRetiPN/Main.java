package poo.esercizioRetiPN;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main( String[] args ) {
        Map<String, Posto> M = new HashMap<>();
        LinkedList<Transazione> T = new LinkedList<>();

        Posto p1 = new Posto("p1", 2);
        Posto p2 = new Posto("p2");
        Posto p3 = new Posto("p3");
        Posto p4 = new Posto("p4");

        M.put("p1", p1);
        M.put("p2", p2);
        M.put("p3", p3);
        M.put("p4", p4);

        ArcoIn p1t1 = new ArcoIn(p1);
        ArcoOut t1p2 = new ArcoOut(p2);
        ArcoIn p2t2 = new ArcoIn(p2);
        ArcoIn p2t3 = new ArcoIn(p2);
        ArcoOut t3p4 = new ArcoOut(p4);
        ArcoOut t2p3 = new ArcoOut(p3);
        ArcoIn p3t4 = new ArcoIn(p3);
        ArcoIn p4t5 = new ArcoIn(p4);
        ArcoIn p4t4 = new ArcoIn(p4);
        ArcoOut t5p1 = new ArcoOut(p1);
        ArcoOut t4p1 = new ArcoOut(p1);

        List<ArcoIn> archiEntrantiT1 = new LinkedList<>();
        archiEntrantiT1.add(p1t1);
        List<ArcoOut> archiUscentiT1 = new LinkedList<>();
        archiUscentiT1.add(t1p2);
        List<ArcoIn> archiEntrantiT2 = new LinkedList<>();
        archiEntrantiT2.add(p2t2);
        List<ArcoOut> archiUscentiT2 = new LinkedList<>();
        archiUscentiT2.add(t2p3);
        List<ArcoIn> archiEntrantiT3 = new LinkedList<>();
        archiEntrantiT3.add(p2t3);
        List<ArcoOut> archiUscentiT3 = new LinkedList<>();
        archiUscentiT3.add(t3p4);
        List<ArcoIn> archiEntrantiT4 = new LinkedList<>();
        archiEntrantiT4.add(p3t4);
        archiEntrantiT4.add(p4t4);
        List<ArcoOut> archiUscentiT4 = new LinkedList<>();
        archiUscentiT4.add(t4p1);
        List<ArcoIn> archiEntrantiT5 = new LinkedList<>();
        archiEntrantiT5.add(p4t5);
        archiEntrantiT4.add(p4t4);
        List<ArcoOut> archiUscentiT5 = new LinkedList<>();
        archiUscentiT5.add(t5p1);

        Transazione t1 = new Transazione("t1", archiEntrantiT1, archiUscentiT1);
        Transazione t2 = new Transazione("t2", archiEntrantiT2, archiUscentiT2);
        Transazione t3 = new Transazione("t3", archiEntrantiT3, archiUscentiT3);
        Transazione t4 = new Transazione("t4", archiEntrantiT4, archiUscentiT4);
        Transazione t5 = new Transazione("t5", archiEntrantiT5, archiUscentiT5);
        T.add(t1);
        T.add(t2);
        T.add(t3);
        T.add(t4);
        T.add(t5);

        PN pn = new PN(M, T);
        pn.multipleSteps(5);
        System.out.println(pn);
        /*
        System.out.println("Posti della rete-> "+M);
        System.out.println("Transazioni della rete-> " +T);

        System.out.println();
        t1.sparo();

        System.out.println("Posti della rete-> "+M);
        System.out.println("Transazioni della rete-> " +T);

        System.out.println();
        t2.sparo();

        System.out.println("Posti della rete-> "+M);
        System.out.println("Transazioni della rete-> " +T);
        */


    }
}
