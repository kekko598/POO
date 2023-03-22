package poo.esercizio17_06_2022;

public record Volo(String partenza, String arrivo, int prezzoBusiness, int prezzoEconomia) {

    @Override
    public String toString() {
        return "{" +
                "partenza='" + partenza + '\'' +
                ", arrivo='" + arrivo + '\'' +
                ", prezzo Business=" + prezzoBusiness +
                ", prezzo Economia=" + prezzoEconomia +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volo volo = (Volo) o;
        if (prezzoBusiness != volo.prezzoBusiness) return false;
        if (prezzoEconomia != volo.prezzoEconomia) return false;
        if (!partenza.equals(volo.partenza)) return false;
        return arrivo.equals(volo.arrivo);
    }

}
