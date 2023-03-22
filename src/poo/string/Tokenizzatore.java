package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Tokenizzatore {
    public static void main( String[] args ) {
        //parola prima dei separatori
        //ripeti fino alla fine della linea
        //finchè il carattere non è di parola
        //salta il carattere e vai avanti
        //finchè il carattere è di parola memorizza il carattere
        // e vai avanti
        System.out.print("Inserisci una linea->");
        String linea = new Scanner(System.in).nextLine();
        StringTokenizer st = new StringTokenizer(linea, " .,:!?");
        //con il metodo che vuole la variabile booleana con true gli dico di identificare e non buttare il separatore
        //filtra la stringa dalla black list di token inseriti
        String lineaPulita = null;
        while (st.hasMoreTokens()) {
            lineaPulita = st.nextToken();
            System.out.println(lineaPulita.toUpperCase());
        }
        //2 metodo
        System.out.println();
        System.out.println();

        Scanner sl = new Scanner(linea);
        sl.useDelimiter("\\W+"); //separatori caratteri non di word
        while (sl.hasNext()) {
            String p = sl.next();
            p = p.toLowerCase();
            System.out.println(p);
        }
        System.out.println();
        System.out.println();
        //3 metodo
        String[] a = linea.split("\\W+");
        for (String x : a)
            System.out.println(x);
    }
}
