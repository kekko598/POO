package poo.esercizio09_01_2020;

import java.util.List;

public interface Cruciverba {
    int getNumeroRighe();

    int getNumeroColonne();

    boolean contains( String parola );

    List<String> paroleOrizzontali();

    List<String> paroleVericatili();
}
