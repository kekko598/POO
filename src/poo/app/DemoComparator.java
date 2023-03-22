package poo.app;

import java.util.*;


public class DemoComparator {
    public static void main( String[] args ) {
        Set<String> s = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare( String o1, String o2 ) {
                if (o1.length() < o2.length()) return -1;
                if (o1.length() > o2.length()) return 1;
                return o2.compareTo(o1);
            }
        }); //crea java direttamente una classe anonima
        s.add("terra");
        s.add("aria");
        s.add("acqua");
        s.add("cuoco");
        s.add("fuoco");
        s.addAll(Arrays.asList("ciao", "ciao2", "ciao3"));
        List<String> s1 = new ArrayList<>(Arrays.asList("aaaa"));
        s1.add("prova");//crea un nuovo oggetto perché è immutabile
        s.addAll(s1);
        System.out.println(s);

    }

}
