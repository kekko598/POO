package poo.esercizioDenaro;

import poo.util.ListaOrdinataConcatenata;

import java.util.Iterator;


public class SoldiLC extends SoldiAbstract implements Cloneable {

    private ListaOrdinataConcatenata<Denaro> soldi = new ListaOrdinataConcatenata<>();
    private double TotaleSoldi;


    public static void main( String[] args ) {

        /*
         Soldi cassa = new SoldiLC();
        cassa.add(new Denaro(200.00, 1));
        Soldi cliente = new SoldiLC();
        cliente.add(new Denaro(20.00, 1));
        Soldi pagamento = new SoldiLC();
        pagamento.add(new Denaro(10.00, 1));
        pagamento.add(new Denaro(2.00, 1));
        pagamento.add(new Denaro(1.00, 1));
        pagamento.add(new Denaro(0.50, 1));

        System.out.println("Soldi cassa->" + cassa);
        System.out.println("Soldi cliente->" + cliente);
        System.out.println("Pagamento prodotto/i->" + pagamento);
        System.out.println("Resto da dare: " + cassa.sub(pagamento, cliente));
        System.out.println("Soldi cassa attuali->" + cassa);
        cassa.add(new Denaro(50.00,1));
        Soldi prestito = new SoldiLC(); prestito.add(new Denaro(10.00,1));
        cassa.add(prestito);
        System.out.println("Soldi cassa dopo prestito->" + cassa);
        Soldi cliente2 = new SoldiLC(); cliente2.add(cliente);
        System.out.println("Soldi cliente 2->"+cliente2);
        System.out.println("Cliente paga "+ pagamento+", resto da dare: " + cassa.sub(pagamento, cliente2));
        System.out.println("Soldi cassa attuali->" + cassa);
         */
        Soldi cassa = new SoldiLC();
        cassa.add(new Denaro(100.00, 1));
        Soldi cliente = new SoldiLC();
        cliente.add(new Denaro(50.00, 1));
        Soldi pagamento = new SoldiLC();
        pagamento.add(new Denaro(20.00, 1));
        pagamento.add(new Denaro(5.00, 1));
        System.out.println("Soldi cassa->" + cassa);
        System.out.println("Soldi cliente->" + cliente);
        System.out.println("Pagamento prodotto/i->" + pagamento);
        System.out.println("Resto da dare: " + cassa.sub(pagamento, cliente));
        System.out.println("Soldi cassa attuali->" + cassa);
        cassa.add(pagamento);
        System.out.println("Soldi cassa attuali->" + cassa);
        cassa.add(cliente);
        System.out.println("Soldi cassa attuali->" + cassa);
    }

    @Override
    public double totale() {
        double s = 0;
        for (Denaro x : soldi)
            s += (x.getValore() * x.getQuantita());
        return s + TotaleSoldi;
    }

    @Override
    public void add( Denaro d ) {
        soldi.add(d);
    }

    @Override
    public void add( Soldi s ) {
        TotaleSoldi += s.totale();
    }

    //s1 pagamento con una data somma s2

    @Override
    public Double sub( Soldi pagamento, Soldi sommmaDiDenaro ) {
        if (sommmaDiDenaro.compareTo(pagamento) < 0)
            throw new IllegalStateException("Non puoi pagare con " + sommmaDiDenaro.totale() + " se il pagamento ne richede " + pagamento.totale());
        double valCassaOrigine = totale();
        this.add(sommmaDiDenaro);
        double resto = sommmaDiDenaro.totale() - pagamento.totale();
        if (resto >= sommmaDiDenaro.totale() || resto > valCassaOrigine)
            throw new IllegalStateException("Non puoi dare il resto");
        TotaleSoldi = totale() - resto;
        soldi.clear();
        return resto;
    }

    @Override
    public int compareTo( Object o ) {
        Soldi s = (Soldi) o;
        if (totale() > s.totale()) return 1;
        if (totale() < s.totale()) return -1;
        return 0;
    }

    @Override
    public Iterator iterator() {
        return soldi.iterator();
    }

    @Override
    public SoldiLC clone() {
        try {
            SoldiLC clone = (SoldiLC) super.clone();
            clone.soldi = new ListaOrdinataConcatenata<>();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}


