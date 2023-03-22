package poo.esercizio28_02_2017;

import java.util.List;

public interface PhoneBook extends Iterable {
    void add( Persona p );

    List<Persona> remove( String s );

    Persona remove( Persona p ); //p vale solo per i confronti

    List<Persona> locate( String s );

    int size();

    void clear();

}
