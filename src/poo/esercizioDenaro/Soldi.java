package poo.esercizioDenaro;

public interface Soldi extends Comparable, Iterable {
    double totale();

    void add( Denaro d );

    void add( Soldi s );

    Double sub( Soldi pagamento, Soldi sommmaDiDenaro );
}
