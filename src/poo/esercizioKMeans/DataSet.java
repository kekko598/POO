package poo.esercizioKMeans;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public final class DataSet {
    private static String nomeFile;
    private static int n, min, max;

    public DataSet( String nomeFile, int n, int min, int max ) {
        DataSet.nomeFile = nomeFile;
        DataSet.n = n;
        DataSet.min = min;
        DataSet.max = max;
    }

    public static void crea() throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter(nomeFile));
        for (int i = 0; i < n; i++)
            pr.write(Math.random() * (max - min) + min + " " + Math.random() * (max - min) + min + "\n");
        pr.close();
        System.out.println("File " + nomeFile + " generato con successo!");
    }

    public static void main( String[] args ) throws IOException {
        DataSet dt = new DataSet("/Users/francesco/Desktop/centroidi.txt", 15, 500, 1500);
        crea();
    }
}
