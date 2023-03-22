package poo.esercizio18_01_2023.esercizio1;

public record Posto(int Fila, int Sedile) {
    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posto posto = (Posto) o;
        return Sedile == posto.Sedile && Fila == posto.Fila;
    }

    @Override
    public int hashCode() {
        int result = Fila;
        result = 31 * result + Sedile;
        return result;
    }

    @Override
    public String toString() {
        return "Posto{" +
                "Fila=" + Fila +
                ", Sedile=" + Sedile +
                '}';
    }
}
