package poo.esercizioRetiPN;

public class Arco {
    private final int peso;
    private final Posto posto;

    public Arco( int peso, Posto posto ) {
        if (peso < 0) throw new IllegalArgumentException();
        this.peso = peso;
        this.posto = posto;
    }

    public Arco( Posto posto ) {
        this(1, posto);
    }

    public int getPeso() {
        return peso;
    }

    public Posto getPosto() {
        return posto;
    }

    public boolean abilitato() {
        return peso <= posto.getnToken();
    }

    @Override
    public String toString() {
        String sb = "Arco{" + "peso=" + peso +
                ", posto=" + posto +
                '}';
        return sb;
    }
}
