package poo.lambda;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class for_each {
    public static void main( String[] args ) {

        Set<String> ls = new TreeSet<>(Comparator.naturalOrder());
        ls.add("z");
        ls.add("b");
        ls.add("a");
        ls.add("a");
        ls.add("d");
        ls.forEach(s -> System.out.println(s));
        // ls.forEach(System.out::println);
    }
}
