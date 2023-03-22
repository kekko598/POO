package poo.esercizio19_02_2019;

import poo.backtracking.Backtracking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Topologia extends Backtracking<Integer, Integer> {

    private List<Segmento> segmenti;
    private List<Segmento> soluzione;

    public Topologia( String s ) throws IOException {
        File f = new File(s);
        if (!(f.exists()))
            throw new IllegalArgumentException("File non esistente");
        segmenti = new ArrayList<>();
        soluzione = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea = "";
        String regex = "x=(\\d+)\\s+yi=(\\d+)\\s+yf=(\\d+)";
        while (true) {
            linea = br.readLine();
            if (linea == null) break;
            if (!linea.matches(regex))
                throw new RuntimeException("il file deve contenere x=numero yi=numero yf=numero");
            String[] parti = linea.split("\\s+");
            int x = 0, yi = 0, yf = 0;
            for (int i = 0; i < parti.length - 2; i++) {
                if (parti[i].charAt(0) == 'x')
                    x = Integer.parseInt(parti[i].substring(2));
                if (parti[i + 1].charAt(0) == 'y' && parti[i + 1].charAt(1) == 'i')
                    yi = Integer.parseInt(parti[i + 1].substring(3));
                if (parti[i + 2].charAt(0) == 'y' && parti[i + 2].charAt(1) == 'f')
                    yf = Integer.parseInt(parti[i + 2].substring(3));
                segmenti.add(new Segmento(x, yi, yf));
            }


        }

        br.close();
    }

    public static void main( String[] args ) throws IOException {
        Topologia t = new Topologia("/Users/francesco/Desktop/topo");
        System.out.println(t);
        t.risolvi();

    }

    @Override
    protected boolean esisteSoluzione( Integer i ) {
        return soluzione.get(i).equals(segmenti.get(segmenti.size() - 1));
    }


    @Override
    public String toString() {
        return segmenti.toString();
    }

    @Override
    protected boolean assegnabile( Integer j, Integer i ) {

        if (segmenti.get(j).getX() < soluzione.get(i - 1).getX() ||
                !segmenti.get(j).consecutivo(soluzione.get(i - 1)))
            return false;
        for (int k = i - 1; k >= 0; k--)
            if (!soluzione.get(k).equals(segmenti.get(j)))
                return false;
        return true;


    }

    @Override
    protected void assegna( Integer j, Integer i ) {
        soluzione.add(segmenti.get(j));
    }

    @Override
    protected void deassegna( Integer j, Integer i ) {
        soluzione.remove(i);
    }

    @Override
    protected void scriviSoluzione( Integer i ) {
        System.out.println(soluzione);
    }


    @Override
    protected Collection<Integer> scelte( Integer integer ) {
        List<Integer> ls = new ArrayList<>();
        for (int i = integer; i <= soluzione.size(); i++)
            ls.add(i);
        return ls;
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> ls = new ArrayList<>();
        for (int i = segmenti.indexOf(soluzione.get(soluzione.size() - 1)) + 1; i < segmenti.size(); i++)
            ls.add(i);
        return ls;
    }

    @Override
    public void risolvi() {

        if (segmenti.size() == 0) return;
        soluzione.add(segmenti.get(0));
        super.risolvi();


    }
}
