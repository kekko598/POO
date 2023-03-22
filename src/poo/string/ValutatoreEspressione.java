package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class ValutatoreEspressione {

    private String OPND = "\\d+";
    private String OPRT = "[\\+\\-\\*/\\(\\)]";
    private String EXPR = "(" + OPND + "|(" + OPRT + "|" + OPND + ")*)";

    private String expr;

    public ValutatoreEspressione( String expr ) {
        if (!expr.matches(EXPR))
            throw new IllegalArgumentException(expr + " e' malformata.");
        this.expr = expr;
    }

    public static void main( String[] args ) {
        System.out.println("Valutatore di espressioni aritmetiche intere con " +
                " operandi interi senza segno e operatori +-*/()");
        Scanner sn = new Scanner(System.in);
        ValutatoreEspressione v = null;
        while (true) {
            System.out.print("Expr> ");
            String expr = sn.nextLine();
            try {
                v = new ValutatoreEspressione(expr);
                int r = v.valuta();
                System.out.println(expr + "=" + r);
            } catch (RuntimeException e) {
                System.out.println("Espressione malformata.");
                continue;
            }
            System.out.print("Continuare (S/N) ?");
            String ans = sn.nextLine();
            if (ans.charAt(0) != 's' && ans.charAt(0) != 'S')
                break;
        }
        System.out.println("Bye.");
        sn.close();
    }//main

    private int valutaOperando( StringTokenizer st ) {
        String tk = st.nextToken();
        if (tk.charAt(0) == '(')
            return valutaEspressione(st);
        else
            return Integer.parseInt(tk);
    }//valutaOperando

    private int valutaEspressione( StringTokenizer st ) {
        int ris = valutaOperando(st);
        while (st.hasMoreTokens()) {
            char op = st.nextToken().charAt(0);
            if (op == ')') return ris;
            int opnd = valutaOperando(st);
            switch (op) {
                case '+':
                    ris = ris + opnd;
                    break;
                case '-':
                    ris = ris - opnd;
                    break;
                case '*':
                    ris = ris * opnd;
                    break;
                case '/':
                    ris = ris / opnd;
                    break;
                default:
                    throw new RuntimeException("Operatore inatteso.");
            }
        }
        return ris;
    }//valuta

    public int valuta() {
        StringTokenizer st = new StringTokenizer(expr, "+-*/()", true);
        return valutaEspressione(st);
    }//valuta
}
