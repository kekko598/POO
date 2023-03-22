package poo.app;

public class Monetina {
    private Esito faccia;

    public Monetina() {
        lancia();
    }

    public static void main( String[] args ) {
        Monetina m1 = new Monetina();
        int testaC = 0;
        int croceC = 0;
        int iterazioni = 1000000;
        for (int i = 0; i < iterazioni; i++) {
            m1.lancia();
            if (m1.getFaccia() == Esito.CROCE) croceC++;
            if (m1.getFaccia() == Esito.TESTA) testaC++;
        }
        System.out.println("Numero di testa uscite " + testaC);
        System.out.println("Numero di croci uscite " + croceC);

    }

    public void lancia() {
        if (Math.random() < 0.5)
            faccia = Esito.TESTA;
        else
            faccia = Esito.CROCE;
    }

    public Esito getFaccia() {
        return faccia;
    }

    @Override
    public String toString() {
        return faccia.toString();
    }

    public enum Esito {TESTA, CROCE}
}
