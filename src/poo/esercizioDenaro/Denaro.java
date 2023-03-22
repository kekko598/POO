package poo.esercizioDenaro;


public class Denaro implements Comparable<Denaro> {
    private Double valore;
    private int pezzo;

    public Denaro( Double valore, int pezzo ) {
        if (valore == 0.01 || valore == 0.02 || valore == 0.05 || valore == 0.10 || valore == 0.20 || valore == 0.50 || valore == 1.00 || valore == 2.00
                || valore == 5.00 || valore == 10.00 || valore == 20.00 || valore == 50.00 || valore == 100.00 || valore == 200.00 || valore == 500.00) {
            this.valore = valore;
        } else {
            throw new IllegalArgumentException("Valore denaro non accettato, accettiamo 0.01, 0.02, 0.05, 0.10, 0.20, 0.50, 1.00, 2.00, 5.00, 10.00, 20.00, 50.00, 100.0, 200.00 e 500.00");
        }
        if (pezzo >= 1) {
            this.pezzo = pezzo;
        } else {
            throw new IllegalArgumentException("il pezzo deve essere da 1 a salire");
        }

    }


    public Double getValore() {
        return valore;
    }

    public int getQuantita() {
        return pezzo;
    }

    public void setQuantita( int pezzo ) {
        if (pezzo >= 1) {
            this.pezzo = pezzo;
        } else {
            throw new IllegalArgumentException("il pezzo deve essere da 1 a salire");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Denaro{");
        sb.append("valore=").append(valore);
        sb.append(", pezzo=").append(pezzo);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Denaro denaro = (Denaro) o;
        return valore.equals(denaro.valore);
    }

    @Override
    public int hashCode() {
        int result = valore != null ? valore.hashCode() : 0;
        result = 31 * result + pezzo;
        return result;
    }

    @Override
    public int compareTo( Denaro o ) {
        Denaro d2 = o;
        if (valore > d2.valore) return 1;
        if (valore.equals(d2.valore)) return 0;
        return -1;
    }
}
