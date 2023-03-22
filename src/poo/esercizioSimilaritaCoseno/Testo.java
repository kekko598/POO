package poo.esercizioSimilaritaCoseno;

public interface Testo {
    String toString();

    int frequenza( String parola );

    String parolaPiuFrequente();

    Testo retainALL( Testo T ) throws CloneNotSupportedException;

    double similaritaCoseno( Testo t );

}
