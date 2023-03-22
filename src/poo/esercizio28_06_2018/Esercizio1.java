package poo.esercizio28_06_2018;

import java.io.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Esercizio1 {
    private Map<String, Integer> dati = new LinkedHashMap<>();
    private File fdest;

    public Esercizio1( String src, String dest ) throws IOException {
        File fsrc = new File(src);
        fdest = new File(dest);
        if (!fsrc.exists())
            throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(fsrc));
        String linea = "";
        while (true) {
            linea = br.readLine();
            if (linea == null) break;
            String[] parti = linea.split("\n");
            for (String s : parti)
                dati.put(s, s.length());
        }
        br.close();
    }

    public static void main( String[] args ) throws IOException {
        Esercizio1 e = new Esercizio1("/Users/francesco/Desktop/prosa", "/Users/francesco/Desktop/prosa2");
        System.out.println(e);
        e.giustifica();
    }

    @Override
    public String toString() {
        return dati.toString();
    }

    public String rigaPiuLunga() {
        int max = Collections.max(dati.values());
        for (String s : dati.keySet())
            if (dati.get(s).equals(max))
                return s;
        return null;
    }

    public void giustifica() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(fdest));
        for (Map.Entry<String, Integer> entry : dati.entrySet()) {
            if (entry.getKey().contains(".") || entry.getValue() == 25) {
                System.out.println("non andrà giustificata ->" + entry.getKey());
                pw.write(entry.getKey() + "\n");
            } else {
                String s = entry.getKey();
                System.out.println("andrà giustificata ->" + entry.getKey());
                int spaziMancanti = 25 - entry.getKey().length();
                StringBuilder sb = new StringBuilder();
                int index = 0;
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    sb.append(c);
                    index++;
                    if (c == ' ' && index < 25) {
                        for (int j = 0; j < spaziMancanti / 2; j++) {
                            sb.append(" ");
                            index++;
                        }
                    }
                }
                while (index < 25) {
                    sb.append(" ");
                    index++;
                }

                pw.write(sb + "\n");
            }
        }
        System.out.println("Scritti nel file con le parti giustificati in -> " + fdest);
        pw.close();
        //System.out.println(datiGiustificati);
    }
}
