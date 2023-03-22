package poo.esercizio28_02_2013;

public class Nominativo implements Comparable<Nominativo> {

    private final String nome;
    private final String cognome;
    private final int codice;

    public Nominativo( String nome, String cognome, int codice ) {
        if (codice < 1)
            throw new IllegalArgumentException("Il codice deve essere un intero non negativo");
        this.nome = nome;
        this.cognome = cognome;
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getCodice() {
        return codice;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof Nominativo that)) return false;
        return cognome.equals(that.cognome) && (nome.equals(that.nome)) && (codice == that.codice);
    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + cognome.hashCode();
        result = 31 * result + codice;
        return result;
    }

    @Override
    public int compareTo( Nominativo o ) {
        int c = cognome.compareTo(o.cognome);
        if (c != 0) return c;
        return nome.compareTo(o.nome);

    }

    @Override
    public String toString() {
        String sb = "{" + "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codice=" + codice +
                '}';
        return sb;
    }
}
