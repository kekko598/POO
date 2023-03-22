package poo.esercizio12_02_2011;

public class Parola implements Comparable<Parola> {
    private final String parola;

    public Parola( String sF ) {
        if (sF.length() < 1)
            throw new IllegalArgumentException("Deve contenere solo parole!");
        this.parola = sF.toUpperCase();
    }

    @Override
    public int compareTo( Parola o ) {
        return parola.compareTo(o.parola);
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof Parola)) return false;
        return parola.equals(((Parola) o).parola);
    }

    public String getParola() {
        return parola;
    }

    @Override
    public int hashCode() {
        return parola.hashCode();
    }

    @Override
    public String toString() {

        return parola;
    }
}
