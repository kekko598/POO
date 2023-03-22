package poo.esercizio11_09_2019;

import java.io.*;
import java.util.*;

public class RiferimentiIncrociati {

    private Map<String, String> datiFiltrati = new TreeMap<>(( o1, o2 ) -> {
        if (o1.length() > o2.length()) return 1;
        if (o1.length() < o2.length()) return -1;
        return o1.compareTo(o2);
    });


    public RiferimentiIncrociati( String nomeFile ) throws IOException {
        File f = new File(nomeFile);
        if (!f.exists())
            throw new IllegalArgumentException("File non trovato!");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String regex = "\\w+:\\d+(\\s\\d+)*";
        String linea = "";
        while (true) {
            linea = br.readLine();
            if (linea == null) break;
            if (!(linea.matches(regex)))
                throw new IllegalArgumentException("il file deve contenere informazioni del tipo parola:numero-di-pagina");
            String[] parts = linea.split(":");
            for (int i = 0; i < parts.length - 1; i++) {

                if (!datiFiltrati.containsKey(parts[i].toUpperCase()))
                    datiFiltrati.put(parts[i].toUpperCase(), parts[i + 1]);
                else {
                    String rip = (datiFiltrati.get(parts[i].toUpperCase()) + " " + parts[i + 1]);
                    datiFiltrati.put(parts[i].toUpperCase(), rip);
                }

            }
        }
        br.close();
        for (Map.Entry<String, String> entry : datiFiltrati.entrySet()) {
            String parola = entry.getKey();
            String numeri = entry.getValue();
            Comparator<String> c = ( o1, o2 ) -> {
                int n = Integer.parseInt(o1);
                int m = Integer.parseInt(o2);
                return Integer.compare(n, m);
            };
            // Splitta la stringa sullo spazio come separatore e converte i risultati in un List
            List<String> valueSet = new ArrayList<>(Arrays.asList(numeri.split(" ")));
            Collections.sort(valueSet, c); //Ordino la lista dei numeri
            Set<String> valueSet2 = new LinkedHashSet<>(valueSet);//trasferisco la lista nel set
            // Riunisce il set in una stringa separata da spazi
            String newValue = String.join(" ", valueSet2);

            // Sostituisce il vecchio valore con il nuovo valore senza duplicati
            datiFiltrati.put(parola, newValue);
        }


    }

    public static void main( String[] args ) throws IOException {
        RiferimentiIncrociati r = new RiferimentiIncrociati("/Users/francesco/Desktop/riferimenti");
        System.out.println(r);
        r.salva("/Users/francesco/Desktop/riferimenti2");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : datiFiltrati.entrySet()) {
            sb.append(entry.getKey()).append("\n");
            sb.append(" ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public void salva( String NomeFile ) throws IOException {
        File f = new File(NomeFile);
        f.createNewFile();
        PrintWriter pw = new PrintWriter(new FileWriter(f));
        for (Map.Entry<String, String> entry : datiFiltrati.entrySet()) {
            pw.write(entry.getKey() + "\n" +
                    " " + entry.getValue() + "\n");
        }
        pw.close();
    }
}
