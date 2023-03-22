package poo.esercizio12_01_2005;

public abstract class Stanza implements Comparable<Stanza>,Iterable<Persona>{
    private String telefono;
    public Stanza(String telefono){
        this.telefono=telefono;
    }
    protected abstract void aggiungiPersona(Persona p);
    public String getTelefono() {
        return telefono;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Persona persona : this)
            sb.append(persona).append(", telefono=").append(telefono).append("\n");
        return sb.toString();
    }

    @Override
    public int compareTo( Stanza o ) {
        return telefono.compareTo(o.telefono);
    }
}
