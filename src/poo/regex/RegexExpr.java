package poo.regex;

import java.util.Scanner;

public class RegexExpr {
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        String OPND = "\\d+";
        String OPRT = "[\\+\\-\\*/\\(\\)]";
        String EXPR = "(" + OPND + "|(" + OPRT + "|" + OPND + ")*)";
        for (; ; ) {
            System.out.print("expr> ");
            String linea = sc.nextLine();
            if (linea.length() == 0) break;
            if (linea.matches(EXPR)) {
                System.out.println("Expr=" + linea);
            } else System.out.println(linea + " non e' una espressione valida");
        }
        System.out.println("Bye.");
        sc.close();
    }
}//RegexExpr
