package poo.esercizioRoulette;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Roulette {
    private Map<String, Integer> numeri = new HashMap<>();
    private List<String> numeriRipetuti = new ArrayList<>();

    private LinkedList<Integer> numeriInteri = new LinkedList<>();

    public Roulette( String nomeFile ) {
        try {
            File f = new File(nomeFile);
            if (!(f.exists()))
                throw new IOException("File non trovato!");

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                // Estrai i numeri dalla linea
                String[] parts = line.split("\\s+");
                for (int i = 0; i < parts.length - 1; i++) {
                    if (Integer.parseInt(parts[i]) >= 0 && Integer.parseInt(parts[i]) <= 36 && Integer.parseInt(parts[i + 1]) >= 0 && Integer.parseInt(parts[i + 1]) <= 36) {
                        String coppia = parts[i] + "-" + parts[i + 1];
                        numeri.put(coppia, 0);

                    }

                }
                for (int i = 0; i < parts.length - 1; i++) {
                    if (Integer.parseInt(parts[i]) >= 0 && Integer.parseInt(parts[i]) <= 36 && Integer.parseInt(parts[i + 1]) >= 0 && Integer.parseInt(parts[i + 1]) <= 36) {
                        numeriRipetuti.add((parts[i] + "-" + parts[i + 1]));
                    }

                }
                for (int i = 0; i < parts.length; i++) {
                    if (Integer.parseInt(parts[i]) >= 0 && Integer.parseInt(parts[i]) <= 36) {
                        numeriInteri.addLast(Integer.valueOf(parts[i]));
                    }

                }
            }
            //calcolo coppie
            for (String x : numeri.keySet())
                numeri.put(x, Collections.frequency(numeriRipetuti, x));
            br.close();


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public static void main( String[] args ) throws IOException {
        Roulette r = new Roulette("/Users/francesco/Desktop/numeri.txt");
        System.out.println(r);
        System.out.println(r.numeroUscitoPiuFrequenteDopo(12));

    }

    public boolean uscito( int x ) {
        return numeriInteri.contains(x);
    }

    public int numeroUscitoPiuFrequenteDopo( int x ) {
        int y = 0;
        if (uscito(x)) {
            System.out.println("Lista globale:" + numeriInteri);
            List<Integer> ls = new ArrayList<>(numeriInteri.subList(numeriInteri.indexOf(x) + 1, numeriInteri.size()));
            System.out.println("lista considerata dopo il valore " + x + " scelto: " + ls);
            Map<Integer, Integer> map = new HashMap<>();
            for (int z : ls)
                map.put(z, 0);
            for (int z : map.keySet())
                if (ls.contains(z))
                    map.put(z, Collections.frequency(ls, z));
            int max = Collections.max(map.values());
            for (int z : map.keySet())
                if (map.get(z) == max)
                    y = z;
            System.out.println("Ripetizioni globali " + map);
        } else {
            System.out.println("Numero non presente in lista!");
            System.exit(-1);
        }
        return y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : numeri.keySet())
            sb.append(s, 0, s.indexOf('-'))
                    .append(" seguito da ")
                    .append((s.substring(s.indexOf('-') + 1)))
                    .append(":")
                    .append(numeri.get(s))
                    .append(" volta/e\n");
        return sb.toString();
    }


}
