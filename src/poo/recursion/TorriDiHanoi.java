package poo.recursion;

import java.util.Stack;

public class TorriDiHanoi {
    private int n;

    public static void main( String[] args ) {
        new TorriDiHanoi().muovi(3);
    }

    ;

    public void muovi( int n ) {
        if (n < 2) throw new IllegalArgumentException();
        System.out.println("----metodo iterativo----");
        muoviITE(n, Paletto.Sinistro, Paletto.Centrale, Paletto.Destro);
        System.out.println("----metodo ricorsivo----");
        muovi(n, Paletto.Sinistro, Paletto.Centrale, Paletto.Destro);

    }

    private void muovi( int n, Paletto src, Paletto aux, Paletto dest ) {
        if (n == 1)
            Sposta1Disco(src, dest);
        else {
            muovi(n - 1, src, dest, aux);
            Sposta1Disco(src, dest);
            muovi(n - 1, aux, src, dest);

        }
    }

    private void muoviITE( int n, Paletto src, Paletto aux, Paletto dest ) {
        record AreaDati(int n, Paletto src, Paletto aux, Paletto dest) {
        }
        Stack<AreaDati> stack = new java.util.Stack<>();
        stack.push(new AreaDati(n, src, aux, dest));
        while (!stack.isEmpty()) {
            AreaDati ad = stack.pop();
            if (ad.n == 1)
                Sposta1Disco(ad.src, ad.dest);
            else {

                /*
               schedulo l'azione ricorsiva in mod inverso
               per attuare politica LIFO
                 */
                stack.push(new AreaDati(ad.n - 1, ad.aux, ad.src, ad.dest));//2 ricorsione
                stack.push(new AreaDati(1, ad.src, ad.aux, ad.dest));//chiamata intermedia
                stack.push(new AreaDati(ad.n - 1, ad.src, ad.dest, ad.aux));//1 ricorsione
            }
        }
    }

    private void Sposta1Disco( Paletto src, Paletto dest ) {
        System.out.println("Sposta 1 disco da " + src + " a " + dest);
    }

    enum Paletto {Sinistro, Centrale, Destro}
}
