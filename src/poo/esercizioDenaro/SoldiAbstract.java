package poo.esercizioDenaro;

public abstract class SoldiAbstract implements Soldi {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Soldi: " + totale() + " €");
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Soldi s = (Soldi) o;
        return s.totale() == totale();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
