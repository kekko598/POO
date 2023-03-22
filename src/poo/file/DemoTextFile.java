package poo.file;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DemoTextFile {
    public static void main( String[] args ) throws IOException {
        int[][] a = {
                {1, 2},
                {3, 4}
        };

        File f = new File("matrice_int");
        BufferedWriter pw = new BufferedWriter(new FileWriter(f));
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                pw.write(a[i][j]);
            }
        }
        pw.close();

        Scanner sc = new Scanner(f);

        BufferedReader br = new BufferedReader(new FileReader(f));
        StringTokenizer st = null;
        int[][] b = new int[a.length][];
        String linea = br.readLine();
        for (int i = 0; i < b.length; i++) {//leggo riga matrice
            if (linea == null)
                throw new IOException("File problematico");
            st = new StringTokenizer(linea, " ");
            int[] riga = new int[a.length];
            for (int j = 0; j < riga.length; j++)
                riga[j] = Integer.parseInt(st.nextToken());
            b[i] = riga;
        }
        for (int i = 0; i < b.length; i++)
            System.out.println(Arrays.toString(b[i]));

        br.close();
    }
}
