package poo.agendina;

import java.io.Serializable;

public class Nominativo implements Comparable<Nominativo>, Serializable {
    private String nome, cognome, prefisso, telefono;

    public Nominativo( String nome, String cognome, String prefisso, String telefono ) {
        this.nome = nome;
        this.cognome = cognome;
        this.prefisso = prefisso;
        this.telefono = telefono;
    }

    @Override
    public int compareTo( Nominativo n ) {

        if (this.cognome.compareTo(n.cognome) < 0 || this.cognome.equals(n.cognome) && this.nome.compareTo(n.nome) < 0)
            return -1;
        if (this.equals(n)) return 0;
        return +1;
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Nominativo)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nominativo that = (Nominativo) o;
        return nome == (that.nome) && cognome == (that.cognome);
    }

    @Override
    public int hashCode() {
        final int PRIMO = 43;
        int h = cognome.hashCode();
        h = h * PRIMO + nome.hashCode();
        return h;
    }

    @Override
    public String toString() {
        return nome + " " + cognome + " " + prefisso + " " + telefono;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getPrefisso() {
        return prefisso;
    }

    public String getTelefono() {
        return telefono;
    }

}
