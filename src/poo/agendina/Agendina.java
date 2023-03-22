package poo.agendina;

import poo.util.ArrayVector;
import poo.util.Vector;

import java.io.*;
import java.util.Iterator;
import java.util.StringTokenizer;

public interface Agendina extends Iterable<Nominativo> {
    default int size() {
        Iterator<Nominativo> it = iterator();
        int c = 0;
        while (it.hasNext()) {
            it.next();
            c++;
        }
        return c;
    }

    default void svuota() {
        Iterator<Nominativo> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    void aggiungi( Nominativo n );

    default void rimuovi( Nominativo n ) {
        Iterator<Nominativo> it = iterator();
        while (it.hasNext()) {
            Nominativo x = it.next();
            if (x.equals(n)) {
                it.remove();
                return;
            }
        }

    }

    default Nominativo cerca( Nominativo n ) {
        for (Nominativo x : this) {
            if (x.equals(n)) return x;
            if (x.compareTo(n) > 0) return null;
        }
        return null;
    }

    ;//per cognome-nome

    default Nominativo cerca( String prefisso, String telefono ) {
        for (Nominativo x : this) {
            if (x.getPrefisso().equals(prefisso)
                    && x.getTelefono().equals((telefono)))
                return x;
        }
        return null;

    }

    ;

    default void salva( String nomeFile ) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(nomeFile));
        for (Nominativo n : this)
            pw.println(n);
        pw.close();
    }

    ;

    default void ripristina( String nomeFile ) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeFile));
        Vector<Nominativo> tmp = new ArrayVector<>();
        boolean okLettura = true;
        try {
            while (true) {
                String linea = br.readLine();
                StringTokenizer st = new StringTokenizer(linea, " -");
                String cog = st.nextToken();
                String nome = st.nextToken();
                String prefisso = st.nextToken();
                String telefono = st.nextToken();
                Nominativo n = new Nominativo(cog, nome, prefisso, telefono);
                tmp.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            okLettura = false;
            throw e;
        } finally {
            if (okLettura) {
                svuota();
                for (Nominativo y : tmp) {
                    aggiungi(y);
                }
            }
            br.close();
        }
    }

    ;
}
