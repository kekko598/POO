package poo.esercizio27_02_2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main( String[] args ) throws IOException {
        Elenco<Stanza> stanze = new ElencoLinked<>();
        String nomeFile = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci nome file -> ");
        nomeFile = sc.nextLine();
        File f = new File(nomeFile);
        if (!f.exists()) throw new IllegalArgumentException();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea = "";
        while (true) {
            String numero = null, nome = null, cognome = null;
            linea = br.readLine();
            if (linea == null) break;
            StringTokenizer st = new StringTokenizer(linea, " =");
            while (st.hasMoreTokens()) {
                String elem = st.nextToken();
                if (elem.equals("numero"))
                    numero = st.nextToken();
                elem = st.nextToken();
                if (elem.equals("cognome"))
                    cognome = st.nextToken();
                elem = st.nextToken();
                if (elem.equals("nome"))
                    nome = st.nextToken();

            }
            Impiegato impiegato = new Impiegato(cognome, nome);
            Stanza stanza = null;
            for (Stanza s : stanze)
                if (s.getNumeroTelefono().equals(numero)) {
                    stanza = s;
                    break;
                }
            if (stanza == null) {
                stanza = new Stanza(numero, new ArrayList<>());
                stanze.add(stanza);
            }
            stanza.getImpiegati().add(impiegato);

        }

        stanze.setComparatorAndSort(Stanza::compareTo);

        System.out.println(stanze);
        br.close();

    }

}
