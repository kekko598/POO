package poo.esercizioRetiPN;


import java.util.List;

public class Transazione extends Entita {
    private final List<ArcoIn> preset;
    private final List<ArcoOut> postset;
    private final String nomeTransazione;

    public Transazione( String nomeTransazione, List<ArcoIn> preset, List<ArcoOut> postset ) {
        super(nomeTransazione);
        this.nomeTransazione = nomeTransazione;
        this.preset = preset;
        this.postset = postset;

    }

    public boolean abilitata() {
        for (Arco x : preset) {
            if (!x.abilitato())
                return false;
        }
        return true;
    }

    public void sparo() {
        if (abilitata()) {
            for (ArcoIn x : preset)
                x.getPosto().setnToken(Math.abs(x.getPosto().getnToken() - x.getPeso()));
            for (ArcoOut x : postset)
                x.getPosto().setnToken(Math.abs(x.getPosto().getnToken() + x.getPeso()));

        } else {
            throw new IllegalStateException("Transazione " + nomeTransazione + " non abilitata a sparare");
        }
    }

    @Override
    public String toString() {
        String sb = "Nome transazione: " + nomeTransazione + ", Archi entranti=" + preset +
                ", Archi uscenti=" + postset;
        return sb;
    }

    public List<ArcoIn> getPreset() {
        return preset;
    }

    public List<ArcoOut> getPostset() {
        return postset;
    }
}
