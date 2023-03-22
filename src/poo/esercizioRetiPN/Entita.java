package poo.esercizioRetiPN;

public abstract class Entita {

    private final String nome;

    public Entita( String nome ) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        String sb = "Entita{" + "nome='" + nome + '\'' +
                '}';
        return sb;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entita entita = (Entita) o;

        return entita.equals(nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
