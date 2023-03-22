package poo.esercizio12_01_2005;

public class Main {
    public static void main( String[] args ) {
        Azienda a = new AziendaConcreta();
        Stanza s1 = new StanzaConcreta("0984471431");
        Stanza s2 = new StanzaConcreta("0984471433");
        Persona p1 = new Persona("Cavallo","Francesco");
        Persona p2 = new Persona("Cavallo","Giuseppe");
        Persona p3 = new Persona("Aiello","Anna");
        Persona p4 = new Persona("Argentino","Teddy");
        a.aggiungiImpiegato(p1,s1);
        a.aggiungiImpiegato(p2,s2);
        a.aggiungiImpiegato(p3,s1);
        a.aggiungiImpiegato(p4,s2);
        a.aggiungiImpiegato(new Persona("Pastorex","Gius"),new StanzaConcreta("34234234"));
        a.aggiungiImpiegato(new Persona("Pastorex","Gius"),new StanzaConcreta("0984471433"));

        System.out.println(a);
    }
}
