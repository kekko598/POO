package poo.esercizio12_07_2017;

import poo.sistema.Gauss;

import java.io.*;
import java.util.StringTokenizer;

public class FileGauss {
    private double[][] m;
    private Gauss g;

    FileGauss( String s, int n ) throws IOException {
        m = new double[n][n];
        File f = new File(s);
        if (!f.exists()) throw new FileNotFoundException();
        String regex =
                "(i=\\d+\\s+j=\\d+\\s+v=\\d+)*|" +
                        "(v=\\d+\\s+i=\\d+\\s+j=\\d+)*|" +
                        "(v=\\d+\\s+j=\\d+\\s+i=\\d+)*|" +
                        "(i=\\d+\\s+v=\\d+\\s+j=\\d+)*|" +
                        "(j=\\d+\\s+v=\\d+\\s+i=\\d+)*|" +
                        "(j=\\d+\\s+i=\\d+\\s+v=\\d+)*|";
        String linea = "";
        BufferedReader br = new BufferedReader(new FileReader(f));
        while (true) {
            linea = br.readLine();
            if (linea == null) break;
            if (!(linea.matches(regex))) throw new IllegalArgumentException();
            StringTokenizer st = new StringTokenizer(linea, " =");
            int i = 0, j = 0, v = 0;
            while (st.hasMoreTokens()) {
                String e = st.nextToken();
                if (e.equals("i"))
                    i = Integer.parseInt(st.nextToken());
                if (e.equals("j"))
                    j = Integer.parseInt(st.nextToken());
                if (e.equals("v"))
                    v = Integer.parseInt(st.nextToken());
            }
            m[i][j] = v;

        }
        g = new Gauss(m, new double[n]);
        br.close();
    }

    public static void main( String[] args ) throws IOException {
        FileGauss fg = new FileGauss("/Users/francesco/Desktop/matrix", 3);
        System.out.println(fg);
        System.out.println(fg.determinante());
    }

    @Override
    public String toString() {
        return g.toString();
    }

    public int determinante() {
        return g.determinante();
    }
}
