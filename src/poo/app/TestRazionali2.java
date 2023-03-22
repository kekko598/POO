package poo.app;

import poo.razionali.Razionale;

import java.util.Scanner;

public class TestRazionali2 {
    /* Si ammette che l'eccezione denominatore nullo sia una RuntimeException.
     * Nel main si evita l'insorgere dell'eccezione con un if,
     * ossia testando, prima di invocare il costruttore di Razionale,
     * che il denominatore sia !=0.
     */
    public static void main( String[] args ) throws Exception {
        System.out.println("Fornisci numeratore e denominatore di 10 razionali.");
        Scanner sc = new Scanner(System.in);
        Razionale[] v = new Razionale[10];
        int i = 0, n = 0, d = 0;
        loop:
        while (i < v.length) {
            System.out.println((i + 1) + " razionale");
            System.out.print("n=");
            n = sc.nextInt();
            sc.nextLine();
            System.out.print("d=");
            d = sc.nextInt();
            sc.nextLine();
            if (d == 0) {
                System.out.println("Denominatore nullo! Ridare il razionale.");
                continue loop;
            }
            v[i] = new Razionale(n, d);
            i++;
        }
        System.out.println("Razionali letti:");
        System.out.println(java.util.Arrays.toString(v));
        sc.close();
    }
}//TestRazionali2
