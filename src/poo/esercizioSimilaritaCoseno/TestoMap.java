package poo.esercizioSimilaritaCoseno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TestoMap implements Testo, Cloneable {

    private Map<String, Integer> dati = new TreeMap<>();
    private ArrayList<String> contaParole = new ArrayList<>();


    public TestoMap( File f ) throws IOException {
        if (!f.exists())
            throw new IOException("File non trovato");
        BufferedReader br = new BufferedReader(new FileReader(f));
        while (true) {
            String linea = br.readLine();
            if (linea == null) break;
            Scanner sc = new Scanner(linea);
            sc.useDelimiter("\\W+");
            while (sc.hasNext()) {
                String parola = sc.next().toUpperCase();
                dati.put(parola, 0);
                contaParole.add(parola);
            }
        }

        calcolaFrequenzaParoleDaArray();
        br.close();
    }

    public static void main( String[] args ) throws IOException, NoSuchMethodException, CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Pathname primo file->");
        String path1 = sc.nextLine();
        System.out.print("Pathname secondo file->");
        String path2 = sc.nextLine();
        System.out.println("Attendere....");
        Testo t = new TestoMap(new File(path1));
        Testo t2 = new TestoMap(new File(path2));
        System.out.println("Primo file" + "\n" + t);
        System.out.println();
        System.out.println("Secondo file" + "\n" + t2);
        System.out.println();
        String parola = "Et";
        System.out.println("La parola " + parola + " ha una frequenza di: " + t.frequenza(parola) + " volte nel testo t");
        System.out.println("Parola più frequente nel testo t: " + t.parolaPiuFrequente() + " con ben " + t.frequenza(t.parolaPiuFrequente()) + " ripetzioni");
        System.out.println("Parola più frequente nel testo t2: " + t2.parolaPiuFrequente() + " con ben " + t2.frequenza(t2.parolaPiuFrequente()) + " ripetzioni");
        System.out.println("Affinità tra i testi: " + t.similaritaCoseno(t2) + " più si avvicina a 1 e più sono affini, se tende a 0 non c'è affinità!");
        System.out.println("Insieme differenza tra t1 e t2 :" + t.retainALL(t2));

    }

    @Override
    public int frequenza( String parola ) {
        if (dati.get(parola.toUpperCase()) == null) return 0;
        return dati.get(parola.toUpperCase());
    }

    private void calcolaFrequenzaParoleDaArray() {

        for (String p : dati.keySet()) {
            int c = 0;
            for (String q : contaParole)
                if (p.equals(q))
                    c++;
            dati.replace(p, c);
        }
    }

    @Override
    public String parolaPiuFrequente() {
        int maxFreq = Collections.max(dati.values());
        String parola = null;
        for (String p : dati.keySet()) {
            int c = 0;
            for (String q : contaParole)
                if (p.equals(q))
                    c++;
            if (c == maxFreq)
                parola = p;
        }
        return parola;
    }

    @Override
    public Testo retainALL( Testo T ) throws CloneNotSupportedException {
        TestoMap t = (TestoMap) T;
        TestoMap cloneDiThis = (TestoMap) this.copia();
        Map<String, Integer> filtro = new TreeMap<>();
        for (String p : cloneDiThis.dati.keySet())
            for (String q : t.dati.keySet())
                if (p.equals(q) && dati.get(p).equals(t.dati.get(q)))
                    filtro.put(p, cloneDiThis.dati.get(p));


        cloneDiThis.dati.keySet().retainAll(filtro.keySet());

        return cloneDiThis;
    }

    @Override
    public double similaritaCoseno( Testo t ) {
        Set<String> tmp = new HashSet<>(dati.keySet());
        TestoMap T = (TestoMap) t;
        tmp.retainAll(T.dati.keySet());
        double Ak = 0;
        double Bk = 0;
        double scalare = 0;
        for (String x : tmp)//numeratore
            scalare += dati.get(x) * T.dati.get(x); //t1 * t2
        for (String x : dati.keySet()) //calcolo il prodotto scalare di t1
            Ak += dati.get(x) * dati.get(x);
        for (String x : T.dati.keySet())//calcolo il prodotto scalare di t2
            Bk += T.dati.get(x) * T.dati.get(x);

        return scalare / Math.sqrt(Ak * Bk);
    }

    @Override
    public String toString() {
        return dati.toString();
    }

    @Override
    protected Testo clone() throws CloneNotSupportedException {
        try {
            TestoMap clone = (TestoMap) super.clone();
            clone.dati = new TreeMap<>(dati);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Testo copia() throws CloneNotSupportedException {
        return clone();
    }
}
