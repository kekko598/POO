package poo.esercizio27_02_2017;

import java.util.List;

public class Stanza implements Comparable<Stanza> {
    private String numeroTelefono;
    private List<Impiegato> impiegati;

    public Stanza( String numeroTelefono, List<Impiegato> impiegati ) {
        this.numeroTelefono = numeroTelefono;
        this.impiegati = impiegati;
        impiegati.sort(Impiegato::compareTo);
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public List<Impiegato> getImpiegati() {
        return impiegati;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof Stanza stanza)) return false;
        if (numeroTelefono != stanza.numeroTelefono) return false;
        return impiegati.equals(stanza.impiegati);
    }

    @Override
    public int hashCode() {
        int result = numeroTelefono.hashCode();
        result = 31 * result + impiegati.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Stanza{");
        sb.append("numeroTelefono=").append(numeroTelefono);
        sb.append(", impiegati=").append(impiegati);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int compareTo( Stanza o ) {
        return numeroTelefono.compareTo(o.numeroTelefono);
    }
}
