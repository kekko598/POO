package poo.esercizio28_02_2013;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main( String[] args ) throws IOException {
        System.out.println("Gestione lista persone:");
        Scanner sc = new Scanner(System.in);
        String input;
        Boolean caricato = false;
        ListaPersoneConcatenata lp = null;
        while (true) {
            System.out.print("DIGITA INP->");
            input = sc.nextLine();
            if (input.equals("INP")) {
                System.out.print("Inserisci percorso file ->");
                input = sc.nextLine();
                lp = new ListaPersoneConcatenata(input);
                System.out.println("File caricato");
                caricato = true;
            }
            if (caricato) break;

        }

        boolean flag = false;
        while (true) {
            System.out.print("DIGITA OUT,SBN,SBC,SHW,END,SEARCH,ADD,REMOVE ->");
            input = sc.nextLine();
            switch (input) {

                case "END" -> {
                    System.out.println("Saluti!");
                    System.exit(0);
                }
                case "SBN" -> {
                    lp.sbn();
                    flag = true;
                }
                case "SBC" -> {
                    lp.sbc();
                    flag = true;

                }
                case "SHW" -> {
                    if (flag)
                        System.out.println(lp);
                    else
                        System.out.println("esegui il comando SBN o SBC prima, sennò vedi disordinato");
                }
                case "OUT" -> {
                    if (flag) {
                        System.out.print("Per salvare, dammi un percorso completo di nome file->");
                        input = sc.nextLine();
                        lp.out(input);
                        System.exit(0);
                    } else
                        System.out.println("esegui il comando SBN o SBC prima, sennò salvi tutto in modo disordinato");
                }
                case "ADD" -> {
                    flag = false;
                    System.out.print("inserisci nome->");
                    String nome = sc.nextLine();
                    System.out.print("inserisci cognome->");
                    String cognome = sc.nextLine();
                    System.out.print("inserisci codice->");
                    int codice = Integer.parseInt(sc.nextLine());
                    lp.add(new Nominativo(nome, cognome, codice));
                    if (lp.verificaOccorrenze()) {
                        System.out.println("Esiste più di una occorrenza, la rimuovo");
                        lp.remove(new Nominativo(nome, cognome, codice));
                    } else {
                        System.out.println("Aggiunto");
                    }

                }
                case "REMOVE" -> {
                    flag = false;
                    System.out.print("inserisci nome->");
                    String nome = sc.nextLine();
                    System.out.print("inserisci cognome->");
                    String cognome = sc.nextLine();
                    System.out.print("inserisci codice->");
                    int codice = Integer.parseInt(sc.nextLine());
                    lp.remove(new Nominativo(nome, cognome, codice));
                    System.out.println("Rimosso");
                }
                case "SEARCH" -> {
                    System.out.print("inserisci nome->");
                    String nome = sc.nextLine();
                    System.out.print("inserisci cognome->");
                    String cognome = sc.nextLine();
                    System.out.print("inserisci codice->");
                    int codice = Integer.parseInt(sc.nextLine());
                    System.out.println(lp.search(new Nominativo(nome, cognome, codice)));
                }
                case default -> System.out.println("Comando non riconosciuto");

            }
        }
    }

}
