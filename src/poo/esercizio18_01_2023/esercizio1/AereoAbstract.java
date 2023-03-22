package poo.esercizio18_01_2023.esercizio1;

public abstract class AereoAbstract implements Aereo{

    private Posto posto;

    public AereoAbstract( Posto posto ) {
        this.posto = posto;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("Aereo{").append(posto).append("}");
       return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AereoAbstract that = (AereoAbstract) o;
        return posto.equals(that.posto);
    }

    @Override
    public int hashCode() {
        return posto.hashCode();
    }
}
