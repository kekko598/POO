package poo.esercizio23_07_2019;

import java.io.*;

public class Esercizio2Sol1 {


    private ListaConcatenata<Integer> l;

    public Esercizio2Sol1( String s ) throws IOException {

        File f = new File(s);
        if (!f.exists())
            throw new FileNotFoundException();
        l = new ListaConcatenata<>();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea = "";
        while (true) {
            linea = br.readLine();
            if (linea == null) break;
            String[] parts = linea.split("\\s+");
            for (String x : parts)
                l.add(Integer.valueOf(x));

        }
        br.close();
        ListaConcatenata.bubbleSort(l);
        PrintWriter pw = new PrintWriter(new FileWriter(f));
        int[] tmp = l.elementi();
        for (int i = 0; i < l.size(); i++)
            pw.write(tmp[i] + " ");
        pw.close();
    }


    public static void main( String[] args ) throws IOException {
        Esercizio2Sol1 a = new Esercizio2Sol1("/Users/francesco/Desktop/topo");

    }
}
