package poo.app;

import poo.util.BufferLimitato;
import poo.util.Coda;

import java.util.Scanner;

public class TestCodaCoopBufferLimitato {
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        Coda<String> cassa = new BufferLimitato<>(2);
        String comando = "([Aa]\\s+\\w+|[Pp][Qq])";
        //comando del cliente, partenza cliente e quit programma
        try {
            while (true) {
                System.out.print("--->");
                String com = sc.nextLine();
                if (com.matches("[Aa]\\s+\\w+")) {
                    int i = com.lastIndexOf(' ');
                    String clinte = com.substring(i + 1);
                    System.out.println("Arriva " + clinte);
                    if (cassa.isFull())
                        System.out.println("Coda piena, non inserisco il cliente!");
                    else
                        cassa.offer(clinte);
                    System.out.println("Situazione coda:" + cassa);

                } else if (com.matches("[Pp]")) {
                    if (cassa.isEmpty()) System.out.println("Coda Vuota!");
                    else {
                        String cliente = cassa.poll();
                        System.out.println("Esce " + cliente);
                        System.out.println("Coda residua: " + cassa);
                    }
                } else if (com.matches("[Qq]")) {
                    System.out.println("Chisura!");
                    System.out.println("Coda residua: " + cassa);
                    System.out.println("Bye...");
                    break;
                } else {
                    System.out.println("Cosa?");
                }
            }
        } catch (Exception e) {
            System.out.println("Errore!");
        }

        sc.close();
    }


}
