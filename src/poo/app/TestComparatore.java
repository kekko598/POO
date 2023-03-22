package poo.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestComparatore {
    public static void main( String[] args ) {
        List<String> a = new ArrayList<String>();
        a.add("strada");
        a.add("casa");
        a.add("banana");
        a.add("oro");
        a.add("dado");
        System.out.println("Lista iniziale:");
        System.out.println(a);
        Collections.sort(a);
        //ordina a secondo l’ordine naturale delle stringhe
        System.out.println("Lista ordinata con confronto naturale:");
        System.out.println(a);
        Collections.sort(a, new EsempioDiComparatore());
        //ordina a secondo EsempioDiComparatore
        System.out.println("Lista ordinata con comparatore esplicito:");
        System.out.println(a);
    }

    static class EsempioDiComparatore implements Comparator<String> {
        public int compare( String s1, String s2 ) {
            if (s1.length() < s2.length()) return -1;
            if (s1.length() > s2.length()) return +1;
            return s1.compareTo(s2);
        }//compare
    }// EsempioDiComparatore
}
