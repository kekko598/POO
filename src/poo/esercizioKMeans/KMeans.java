package poo.esercizioKMeans;

import poo.geometria.Punto;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

public class KMeans {

    private Punto[] centroidi;
    private ArrayList<Punto> centroidiTMP = new ArrayList<>();
    private Hashtable<Punto, Integer> dataSet = new Hashtable<>();
    private int k;

    public KMeans( String nomeFilDataSet, String nomeFileKcentroidiIniziali, int k ) throws IOException {
        if (k < 0)
            throw new IllegalArgumentException();
        this.k = k;
        centroidi = new Punto[k];
        File FNomeFilDataSet = new File(nomeFilDataSet);
        File FnomeFileKcentroidiIniziali = new File(nomeFileKcentroidiIniziali);
        if (!FNomeFilDataSet.exists() || !FnomeFileKcentroidiIniziali.exists())
            throw new IllegalArgumentException();

        //carico i centrodi dal file a l'array
        BufferedReader br = new BufferedReader(new FileReader(FnomeFileKcentroidiIniziali));
        String line;
        while ((line = br.readLine()) != null) {
            // Estrai i numeri dalla linea
            String[] parts = line.split("\\s+");
            for (int i = 0; i < parts.length - 1; i++) {
                double x = Double.parseDouble(parts[i]);
                double y = Double.parseDouble(parts[i + 1]);
                centroidiTMP.add(new Punto(x, y));
            }
        }
        for (int i = 0; i < centroidi.length; i++)
            centroidi[i] = centroidiTMP.get(i);
        br.readLine();
        centroidiTMP.clear();

        //carico i punti nel dataSet inizializzati con -1
        BufferedReader br2 = new BufferedReader(new FileReader(FNomeFilDataSet));
        String line2;
        while ((line2 = br2.readLine()) != null) {
            // Estrai i numeri dalla linea
            String[] parts = line2.split("\\s+");
            for (int i = 0; i < parts.length - 1; i++) {
                double x = Double.parseDouble(parts[i]);
                double y = Double.parseDouble(parts[i + 1]);
                dataSet.put(new Punto(x, y), -1);
            }
        }
        br2.readLine();


    }

    public static void main( String[] args ) throws IOException {
        KMeans km = new KMeans("/Users/francesco/Desktop/dataSet.txt", "/Users/francesco/Desktop/centroidi.txt", 15);
        System.out.println(km);
        System.out.println();
        km.run(100);
        System.out.println();
        System.out.println(km);

    }

    public void run( int maxIterazioni ) throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("/Users/francesco/Desktop/centroidiIntermedi.txt"));

        if (maxIterazioni <= 0) throw new IllegalArgumentException();
        boolean terminazione = false;
        int c = 0;
        for (int i = 0; !terminazione && i < maxIterazioni; i++) {
            terminazione = true; //ottimismo
            //calcolo distanza tra i punti p e i centroidi
            for (Punto p : dataSet.keySet()) {
                ArrayList<Double> distanze = new ArrayList<>();
                for (Punto pCentroidi : centroidi)
                    distanze.add(p.distanza(pCentroidi));
                int posMin = distanze.indexOf(Collections.min(distanze));
                if (posMin != dataSet.get(p)) {
                    terminazione = false;
                    dataSet.put(p, posMin);
                }
            }
            //sostituisco i nuovi centroidi se terminazione è falsa
            if (!terminazione) {
                for (int a = 0; a < k; a++) {
                    Punto pMedio = null;
                    for (Punto p : dataSet.keySet()) {
                        if (dataSet.get(p) == a)// vedo se nel dataSet ci sono candidati per l'update della posMin
                        {
                            if (pMedio == null)
                                pMedio = p;
                            else
                                pMedio = Punto.puntoMedio(pMedio, p);
                        }
                    }
                    centroidi[a] = pMedio;
                    pr.write(centroidi[a] + "\n"); //centroidi intermedi salvati in un file per eventuale visione
                }
            }
            c = i;
        }
        if (!terminazione)
            System.out.println("numero max di iterazioni raggiunto, cioè " + maxIterazioni);
        else
            System.out.println(c + " iterazioni fatte a fronte delle max " + maxIterazioni + " iterazioni introdotte");
        pr.close();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("centrodi=").append(Arrays.toString(centroidi)).append("\n");
        sb.append("dataSet=").append(dataSet);
        return sb.toString();
    }

}
