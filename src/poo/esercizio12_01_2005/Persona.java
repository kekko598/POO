package poo.esercizio12_01_2005;

public class Persona implements Comparable<Persona>{
    private String cognome,nome;

    public Persona( String cognome, String nome ) {
        this.cognome = cognome.toUpperCase();
        this.nome = nome.toUpperCase();
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
        if (!(o instanceof Persona persona)) return false;
        return nome.equals(persona.nome) && cognome.equals(persona.cognome);
    }

    @Override
    public int hashCode() {
        int result = cognome.hashCode();
        result = 31 * result + nome.hashCode();
        return result;
    }

    @Override
    public int compareTo( Persona o ) {
        int x = cognome.compareTo(o.cognome);
        return x!=0 ? x:nome.compareTo(o.nome);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("cognome=").append(cognome);
        sb.append(", nome=").append(nome);
        return sb.toString();
    }
}
