package poo.esercizioRetiPN;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PN {
    private Map<String, Posto> M;
    private LinkedList<Transazione> Tabilitate = new LinkedList<>();
    private LinkedList<Transazione> Tdisabilitate = new LinkedList<>();
    private List<Transazione> T;

    public PN( Map<String, Posto> m, List<Transazione> t ) {
        M = m;
        T = t;
        for (Transazione tra : T) {
            if (tra.abilitata())
                Tabilitate.add(tra);
            else
                Tdisabilitate.add(tra);
        }

    }

    public void singleStep() {

        if (Tabilitate.isEmpty()) {
            System.out.println("Deadlock!");
            return;
        }

        Collections.shuffle(Tabilitate);
        Transazione t = Tabilitate.removeFirst();
        t.sparo();
        Tdisabilitate.add(t);//pessimismo
        //Subito dopo si eliminano dalla lista abilitate tutte quelle transizioni che prima erano abilitate ma che hanno perso l’abilitazione a causa dello sparo di t.
        //Tutte queste transizioni vanno rimosse da abilitate ed inserite in disabilitate.
        for (Transazione tr : T)
            if (Tabilitate.contains(tr) && !tr.abilitata()) {
                Tabilitate.remove(tr);
                Tdisabilitate.add(tr);
            }
        //Infine, si analizzano le transizioni presenti in disabilitate, e tutte quelle che risultano ora abilitate, si rimuovono da disabilitate e si aggiungono alla lista abilitate.
        for (Transazione tr : T)
            if (Tdisabilitate.contains(tr) && tr.abilitata()) {
                Tabilitate.add(tr);
                Tdisabilitate.remove(tr);
            }

    }

    public void multipleSteps( int n ) {

        if (n < 2)
            throw new IllegalArgumentException("n deve essere maggiore di 2, altrimenti esegui un singleStep() !!!");
        for (int i = 0; i < n; i++)
            singleStep();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Posti della rete-> ").append(M.values() + "\n");
        sb.append("Transazioni globale della rete-> ").append(T + "\n");
        sb.append("Transazioni della rete abilitate-> ").append(Tabilitate + "\n");
        sb.append("Transazioni della rete disabilitate-> ").append(Tdisabilitate + "\n");

        return sb.toString();
    }
}
