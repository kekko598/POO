package poo.esercizioVerificaParola;

import poo.backtracking.Backtracking;

import java.util.*;

public class Verifica extends Backtracking<Integer, String> {
    private Set<String> dizionario;
    private String s;
    private String[] aux;

    public Verifica( Set<String> dizionario, String s ) {
        this.dizionario = dizionario;
        this.s = s;
        aux = new String[dizionario.size()];

    }

    public static void main( String[] args ) {
        Set<String> dizionario = new HashSet<>();
        dizionario.add("il");
        dizionario.add("dado");
        dizionario.add("la");
        dizionario.add("cane");
        dizionario.add("corre");
        dizionario.add("zebra");
        dizionario.add("veloce");
        dizionario.add("gatto");
        dizionario.add("grigio");
        dizionario.add("treno");
        dizionario.add("salta");
        new Verifica(dizionario, "ilgattovelocesaltailcane").risolvi();
    }

    @Override
    protected boolean assegnabile( Integer s, String s2 ) {
        return dizionario.contains(s2);
    }

    @Override
    protected void assegna( Integer ps, String s ) {
        aux[ps] = s;
    }

    @Override
    protected void deassegna( Integer ps, String s ) {
        return;
    }

    @Override
    protected void scriviSoluzione( Integer integer ) {
        for (int i = 0; i <= integer; i++)
            System.out.print(aux[i] + " ");
    }

    @Override
    protected boolean esisteSoluzione( Integer integer ) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i <= integer; i++)
            s.append(aux[i]);
        return this.s.equals(s.toString());
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < aux.length; i++)
            ls.add(i);
        return ls;
    }

    @Override
    protected Collection<String> scelte( Integer s ) {
        List<String> ls = new ArrayList<>();
        int a = 0;
        for (int i = 0; i < s; i++)
            a += aux[i].length(); // determino lunghezza di ogni parola che mi viene da this.s salvata in aux in pos ie sommo ad a
        for (int k = a; k <= this.s.length(); k++)//ciclo su this.s per paragone su dizionario ogni parola che sta in pos aux[i]
        {
            String parola = this.s.substring(a, k);
            if (dizionario.contains(parola))
                ls.add(parola);
        }
        return ls;
    }

    @Override
    public void risolvi() {
        super.risolvi();
    }
}
