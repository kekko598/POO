package poo.esercizio15_03_2011;

public class Parola implements Comparable<Parola>{
    private String p;

    public Parola( String parola ) {
        if(parola.length()<1)
            throw new IllegalArgumentException();
        this.p = parola.toUpperCase();
    }

    @Override
    public int compareTo( Parola o ) {
        return p.compareTo(o.p);
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parola parola = (Parola) o;
        return p.equals(parola.p);
    }

    @Override
    public int hashCode() {
        return p.hashCode();
    }

    @Override
    public String toString() {

        return p;
    }
    public int length(){
        return p.length();
    }
    public char charAt(int i) {
       return p.charAt(i);
    }
}
