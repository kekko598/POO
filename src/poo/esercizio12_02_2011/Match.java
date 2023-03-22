package poo.esercizio12_02_2011;


import java.io.*;
import java.util.*;

public class Match {

    private Map<Parola, List<Integer>> numeroParoleSuRiga = new TreeMap<>(Comparator.naturalOrder());

    public Match( String nf1, String nf2 ) throws IOException {
        Collezione c = new CollezioneSet(nf2);
        File f1 = new File(nf1);
        if (!(f1.exists())) throw new FileNotFoundException("File f1 non esistente");
        for (Parola p : c) {
            BufferedReader br1 = new BufferedReader(new FileReader(f1));
            int nRiga = 0;
            LinkedList<Integer> conta = new LinkedList<>();
            String linea1 = "";
            while (true) {
                nRiga++;
                linea1 = br1.readLine();
                if (linea1 == null) break;
                Scanner sc1 = new Scanner(linea1);
                while (sc1.hasNext()) {
                    sc1.useDelimiter("\\W+");
                    Parola p2 = new Parola(sc1.next());
                    if (p.equals(p2)) {
                        conta.addLast(nRiga);
                        numeroParoleSuRiga.put(p2, conta);
                    }
                }

            }
            br1.close();
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Parola p : numeroParoleSuRiga.keySet())
            sb.append(p).append(" si ripete ").
                    append(numeroParoleSuRiga.get(p).size()).append(" volte").append("\n")
                    .append("su righe ").append(stampaRipetizioni(numeroParoleSuRiga.get(p))).append("\n");
        return sb.toString();
    }

    private String stampaRipetizioni( List<Integer> ls ) {
        StringBuilder sb = new StringBuilder();

        for (int x : ls) {
            sb.append(x).append("   ");
        }
        return sb.toString();
    }
}
