package poo.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ABExpr {
    private Nodo radice = null;

    public ABExpr( String expr ) {
        String EXPR = "(\\d+|[+\\-*/()])+"; //SOLO Cond necessaria, numero decimale seguito da operando ed e da parentesi
        if (!expr.matches(EXPR)) throw new RuntimeException(expr + " espressione invalida.");
        StringTokenizer st = new StringTokenizer(expr, "+-*/()", true);
        radice = buildEspressione(st);
    }

    public static void main( String[] args ) {
        String expr = "5+(2*7)-(12/8)*3";
        ABExpr abe = new ABExpr(expr);
        System.out.println("Contenuto inOrder (toString)");
        System.out.println(abe);
        List<String> lista = new LinkedList<>();
        abe.visitaAnticipata(lista);
        System.out.println("Contenuto pre-order");
        Iterator<String> it = lista.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();
        System.out.println("Contenuto post-order o RPN");
        lista.clear();
        abe.visitaPosticipata(lista);
        it = lista.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();
        System.out.println(expr + "=" + abe.valore());
    }//main

    private Nodo buildOperando( StringTokenizer st ) {
        String tk = st.nextToken();
        if (tk.charAt(0) == '(') return buildEspressione(st);
        NodoOperando nopnd = new NodoOperando();
        nopnd.val = Integer.parseInt(tk);
        nopnd.fS = null;
        nopnd.fD = null;
        return nopnd;
    }//buildOperando

    private Nodo buildEspressione( StringTokenizer st ) {
        Nodo radice = buildOperando(st);
        while (st.hasMoreTokens()) {
            char op = st.nextToken().charAt(0);
            if (op == ')') return radice;
            if (!("" + op).matches("[+\\-*/]"))
                throw new RuntimeException("Espressione malformata.");
            Nodo opnd = buildOperando(st);
            NodoOperatore nop = new NodoOperatore();
            nop.op = op;
            nop.fS = radice;
            nop.fD = opnd;
            radice = nop;
        }
        return radice;
    }//buildEspressione

    public void visitaSimmetrica( List<String> ls ) {
        if (radice == null) throw new IllegalArgumentException("Espressione assente.");
        visitaSimmetrica(radice, ls);
    }//inOrder

    private void visitaSimmetrica( Nodo radice, List<String> ls ) {
        if (radice == null) return;
        if (radice instanceof NodoOperatore) ls.add("(");
        visitaSimmetrica(radice.fS, ls);
        ls.add(radice.toString()); //visita radice
        visitaSimmetrica(radice.fD, ls);
        if (radice instanceof NodoOperatore) ls.add(")");
    }//inOrder

    public void visitaAnticipata( List<String> ls ) {
        if (radice == null) throw new IllegalArgumentException("Espressione assente.");
        visitaAnticipata(radice, ls);
    }//preOrder

    private void visitaAnticipata( Nodo radice, List<String> ls ) {
        if (radice == null) return;
        ls.add(radice.toString() + " "); //visita la radice
        visitaAnticipata(radice.fS, ls);
        visitaAnticipata(radice.fD, ls);
    }//preOrder

    public void visitaPosticipata( List<String> ls ) {
        if (radice == null) throw new IllegalArgumentException("Espressione assente.");
        visitaPosticipata(radice, ls);
    }//preOrder

    private void visitaPosticipata( Nodo radice, List<String> ls ) {
        if (radice == null) return;
        visitaPosticipata(radice.fS, ls);
        visitaPosticipata(radice.fD, ls);
        ls.add(radice.toString() + " "); //visita la radice
    }//postOrder

    public int valore() {
        if (radice == null) throw new IllegalArgumentException("Espressione assente.");
        return valore(radice);
    }//valore

    private int valore( Nodo radice ) {
        if (radice instanceof NodoOperando)
            return ((NodoOperando) radice).val;
        char op = ((NodoOperatore) radice).op;
        int v1 = valore(radice.fS);
        int v2 = valore(radice.fD);
        return switch (op) {
            case '+' -> v1 + v2;
            case '-' -> v1 - v2;
            case '*' -> v1 * v2;
            default -> v1 / v2;
        };
    }//valore

    public String toString() {
        List<String> l = new LinkedList<>();
        visitaSimmetrica(l);
        Iterator<String> it = l.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }//toString

    private static class Nodo {
        Nodo fS, fD;
    }

    private static class NodoOperando extends Nodo {
        int val;

        public String toString() {
            return "" + val;
        }
    }

    private static class NodoOperatore extends Nodo {
        char op;

        public String toString() {
            return "" + op;
        }
    }

}//Expr
