package poo.app;

import java.util.LinkedList;

public class DemoQueue {
    public static void main( String... args ) {
        LinkedList<String> ls = new LinkedList<>();
        ls.addLast("casa");
        ls.addLast("dado");
        ls.addLast("abaco");
        ls.addLast("fuoco");
        while (!ls.isEmpty()) {
            String x = ls.removeFirst();
            System.out.println(x);
        }
    }
}
