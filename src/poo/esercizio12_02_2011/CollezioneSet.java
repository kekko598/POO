package poo.esercizio12_02_2011;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class CollezioneSet extends Collezione {

    private final Set<Parola> parole = new HashSet<>();
    private final String nomeF2;

    public CollezioneSet( String nomeF2 ) throws IOException {

        this.nomeF2 = nomeF2;
        File f2 = new File(nomeF2);
        if (!(f2.exists())) throw new FileNotFoundException("File f2 non esistente");
        String regex = "[a-zA-Z]+";
        BufferedReader br2 = new BufferedReader(new FileReader(f2));
        String linea2 = "";
        while (true) {
            linea2 = br2.readLine();
            if (linea2 == null) break;
            if (!linea2.matches(regex)) throw new RuntimeException();
            Scanner sc2 = new Scanner(linea2);
            sc2.useDelimiter("\\n+");
            parole.add(new Parola(sc2.next()));

        }
        br2.close();
    }

    @Override
    protected CollezioneSet factory() throws IOException {
        return new CollezioneSet(nomeF2);
    }


    @Override
    public Iterator<Parola> iterator() {
        return parole.iterator();
    }

}
