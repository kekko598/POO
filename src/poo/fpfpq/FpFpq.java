package poo.fpfpq;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class FpFpq {
    public static void main( String[] args ) throws IOException {
        System.out.println("___statistica delle parole di un testo___");
        String nomeFile;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome del file di testo(con pattern possibilmente) ->");
        Statistica stat = new StatisticaMap();
        nomeFile = sc.nextLine();
        File f = new File(nomeFile);
        if (!f.exists()) throw new NoSuchFileException("File  inesistente!");
        BufferedReader br = new BufferedReader(new FileReader(f));
        try {
            String parolaPrecedente = null;
            while (true) {
                String linea = br.readLine();
                if (linea == null) break;
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\W+"); //qualunque carattere non sia un word deve delimitare la parola
                while (sl.hasNext()) {
                    String parolaCorrente = sl.next();
                    stat.arrivoParola(parolaCorrente);
                    if (parolaPrecedente != null)
                        stat.paroleConsecutive(parolaPrecedente, parolaCorrente);
                    parolaPrecedente = parolaCorrente;
                }
            }
        } finally {
            br.close();
        }

        System.out.println("___situazione delle parole del testo___");
        System.out.println(stat);
        System.out.print("Parola obbiettivo: ");
        String target = sc.nextLine();
        String ppf = stat.parolaCheSeguePiuFrequente(target);
        String pmf = stat.parolaCheSegueMenoFrequente(target);
        System.out.println("Parola che segue di più nel testo: " + ppf);
        System.out.println("Parola che segue di meno nel testo: " + pmf);
        sc.close();
    }

}
