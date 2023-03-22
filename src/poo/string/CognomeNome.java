package poo.string;

import java.util.Scanner;

public class CognomeNome {
    public static void main( String[] args ) {
        System.out.print("Inserisci cognome e nome (in mezzo deve avere almeno uno spazio)" +
                "->");
        String S = new Scanner(System.in).nextLine();
        S = S.trim(); //rimuovo spazi iniziali e finali
        int i = S.indexOf(' '); //prendo il primo spazio utile tra cognome e nome, il suo indice
        String cognome = S.substring(0, i); // mi prendo il cognome
        int j = S.lastIndexOf(' ');//prendo l'ultimo spazio tra cognome e nome, il suo indice
        //cerco di trovare il nome
        String nome = S.substring(j + 1);
        System.out.println("Output->" + nome.charAt(0) + ". " + cognome);
    }
}
