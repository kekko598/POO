package poo.esercizioRetiPN;

public class Posto extends Entita {
    private final String nomePosto;
    private int nToken;

    public Posto( String nomePosto, int nToken ) {
        super(nomePosto);
        if (nToken < 0) throw new IllegalArgumentException("Si ammette almeno 1 token");
        this.nToken = nToken;
        this.nomePosto = nomePosto;
    }

    public Posto( String nomePosto ) {
        super(nomePosto);
        this.nomePosto = nomePosto;
        this.nToken = 0;
    }

    public int getnToken() {
        return nToken;
    }

    public void setnToken( int nToken ) {
        if (nToken < 0) throw new IllegalArgumentException("Si ammette almeno 1 token");
        this.nToken = nToken;
    }

    @Override
    public String toString() {
        String sb = nomePosto + "#" + nToken;
        return sb;
    }
}
