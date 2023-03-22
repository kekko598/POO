package poo.app;

import poo.razionali.Razionale;

import java.util.Scanner;

public class TestRazionali1 {
    /* Si ammette che l'eccezione denominatore nullo sia una RuntimeException.
     * Nel main si intercetta l'eccezione, la si gestisce e si riparte
     * con l'esecuzione in avanti, ossia il programma non termina in seguito
     * all'eccezione.
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
            try {
                v[i] = new Razionale(n, d);
            } catch (RuntimeException e) {
                System.out.println("Denominatore nullo! Ridare il razionale.");
                continue loop;
            }
            i++;
        }
        System.out.println("Razionali letti:");
        System.out.println(java.util.Arrays.toString(v));
        sc.close();
    }
}//TestRazionali1
