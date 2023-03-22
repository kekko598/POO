package poo.esercizio10_11_2017;

public interface MatriceSparsa<T> {
    int getN();

    void clear();

    double grado();

    T get( int i, int j );

    void set( int i, int j, T v );

    MatriceSparsa creaMatriceVuota();//factory

    MatriceSparsa<Integer> addInt( MatriceSparsa m );

    MatriceSparsa<Double> addDouble( MatriceSparsa m );

    MatriceSparsa<Integer> mulInt( MatriceSparsa m );

    MatriceSparsa<Double> mulDouble( MatriceSparsa m );

    boolean simmetrica();

}
