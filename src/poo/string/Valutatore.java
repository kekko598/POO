package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Valutatore {
    private final String expr;
    //verifica che xpr sia ben formata

    public Valutatore( String expr ) {
        this.expr = expr;
    }

    public static void main( String[] args ) {
        System.out.print("inserisci espression->");
        Valutatore v = new Valutatore(new Scanner(System.in).nextLine());
        System.out.println(v.valuta());
    }

    public int valuta() {
        StringTokenizer st = new StringTokenizer(expr, "*/+-", true);
        int ris = 0;
        ris = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {
            char operatore = st.nextToken().charAt(0);
            int operando = Integer.parseInt(st.nextToken());
            switch (operatore) {
                case '+':
                    ris += operando;
                    break;
                case '-':
                    ris -= operando;
                    break;
                case '*':
                    ris *= operando;
                    break;
                case '/':
                    ris /= operando;
                    break;
                default:
                    throw new RuntimeException("Operatore sconosciuto");

            }
        }
        return ris;
    }
}

