package poo.esercizio28_02_2013;

import poo.esercizio17_07_2015.Insieme;
import poo.esercizio17_07_2015.InsiemeConcatenato;

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ListaPersoneConcatenata {
    private final Insieme<Nominativo> lista = new InsiemeConcatenato<>();

    public ListaPersoneConcatenata( String nomeFile ) throws IOException {
        File f = new File(nomeFile);
        if (!(f.exists())) throw new FileNotFoundException("File non esistente");
        String regex = "COGNOME=[a-zA-Z]+\\s+NOME=[a-zA-Z]+\\s+CODICE=\\d+|" +
                "NOME=[a-zA-Z]+\\s+COGNOME=[a-zA-Z]+\\s+CODICE=\\d+|" +
                "CODICE=\\d+\\s+NOME=[a-zA-Z]+\\s+COGNOME=[a-zA-Z]+|" +
                "NOME=[a-zA-Z]+\\s+CODICE=\\d+\\s+COGNOME=[a-zA-Z]+|" +
                "CODICE=\\d+\\s+COGNOME=[a-zA-Z]+\\s+NOME=[a-zA-Z]+";
        String linea = "";
        BufferedReader br = new BufferedReader(new FileReader(f));
        while (true) {
            linea = br.readLine();
            if (linea == null) break;
            if (!linea.matches(regex)) throw new RuntimeException("File inconsistente");
            StringTokenizer st = new StringTokenizer(linea, " =");
            String cognome = null, nome = null;
            int codice = 0;
            while (st.hasMoreTokens()) {
                String elem = st.nextToken();
                if (elem.equals("COGNOME"))
                    cognome = st.nextToken().toUpperCase();
                if (elem.equals("NOME"))
                    nome = st.nextToken().toUpperCase();
                if (elem.equals("CODICE"))
                    codice = Integer.parseInt(st.nextToken());
            }
            lista.add(new Nominativo(nome, cognome, codice));
            if (verificaOccorrenze())
                throw new RuntimeException("File inconsistente ci sono occorrenze tra codice/nome/cognome che si ripetono");

        }
        br.close();
    }

    public boolean verificaOccorrenze() {
        for (Nominativo n : lista)
            for (Nominativo n2 : lista)
                if (!n.equals(n2))
                    if (n.getCodice() == n2.getCodice() || n.getCognome().equals(n2.getCognome()) || n.getNome().equals(n2.getNome()))
                        return true;
        return false;
    }

    public void sbn() {
        lista.sort(Nominativo::compareTo);
    }

    public void sbc() {
        lista.sort(Comparator.comparingInt(Nominativo::getCodice));
    }

    public void customSort( Comparator<Nominativo> c ) {
        lista.sort(c);
    }

    public void out( String nomeFile ) throws IOException {
        File f = new File(nomeFile);
        PrintWriter pw = new PrintWriter(new FileWriter(f));
        for (Nominativo n : lista)
            pw.print("COGNOME=" + n.getCognome() + " " + "NOME=" + n.getNome() + " " + "CODICE=" + n.getCodice() + "\n");
        pw.close();
        System.out.println("File salvato su " + f.getAbsolutePath());
    }

    public void add( Nominativo n ) {
        lista.add(new Nominativo(n.getNome().toUpperCase(), n.getCognome().toUpperCase(), n.getCodice()));
    }

    public void remove( Nominativo n ) {
        lista.remove(new Nominativo(n.getNome().toUpperCase(), n.getCognome().toUpperCase(), n.getCodice()));
    }

    public Nominativo search( Nominativo n ) {
        return lista.get(new Nominativo(n.getNome().toUpperCase(), n.getCognome().toUpperCase(), n.getCodice()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Nominativo n : lista)
            sb.append(n).append("\n");
        return sb.toString();
    }

}
