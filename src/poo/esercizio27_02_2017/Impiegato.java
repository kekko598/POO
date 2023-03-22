package poo.esercizio27_02_2017;

public class Impiegato implements Comparable<Impiegato> {
    private String cognome, nome;

    public Impiegato( String cognome, String nome ) {
        this.cognome = cognome;
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }


    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof Impiegato impiegato)) return false;
        return nome.equals(impiegato.nome) && cognome.equals(impiegato.cognome);
    }


    @Override
    public int hashCode() {
        int result = cognome.hashCode();
        result = 31 * result + nome.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("cognome='").append(cognome).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo( Impiegato o ) {
        int cognome = this.cognome.compareTo(o.cognome);
        if (cognome != 0)
            return cognome;
        return this.nome.compareTo(o.nome);
    }
}
