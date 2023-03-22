package poo.esercizio12_01_2005;

public abstract class Azienda implements Iterable<Stanza> {
    public abstract void aggiungiImpiegato(Persona p,Stanza s);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Stanza stanza : this){
            sb.append(stanza).append("\n");
        }
        return sb.toString();
    }

}
