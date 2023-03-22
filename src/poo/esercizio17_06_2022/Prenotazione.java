package poo.esercizio17_06_2022;

import java.util.LinkedList;

public record Prenotazione(LinkedList<String> percorso, String cliente, String classe) {

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prenotazione that = (Prenotazione) o;

        if (!percorso.equals(that.percorso)) return false;
        if (!cliente.equals(that.cliente)) return false;
        return classe.equals(that.classe);
    }

    @Override
    public String toString() {
        return "{" +
                "percorso=" + percorso +
                ", cliente='" + cliente + '\'' +
                ", classe='" + classe + '\'' +
                '}';
    }
}
