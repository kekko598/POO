package poo.esercizio17_07_2015;

import java.io.IOException;
import java.util.*;

public class EspressioneRPNSenzaFILE {
    Map<Character, Integer> precedenze = new HashMap<>();

    public EspressioneRPNSenzaFILE() throws IOException {
        //14 x 8 alfa + * /
        String s;
        precedenze.put('*', 2);
        precedenze.put('/', 2);
        precedenze.put('+', 1);
        precedenze.put('-', 1);
        Scanner sc = new Scanner(System.in);
        System.out.println("Per interrompere inserimento, inserire .");
        while (true) {

            String linea = "";
            System.out.print("Inserisci espressione in RPN->");
            s = sc.nextLine();
            if (s.equals("."))
                break;
            StringTokenizer st = new StringTokenizer(s, " ");
            Stack<String> stack = new Stack<>();
            String temp = null;
            while (st.hasMoreTokens()) {
                String elem = st.nextToken();
                if (elem.equals("-") || elem.equals("+") || elem.equals("*") || elem.equals("/")) {
                    String op1 = stack.pop();
                    String op2 = stack.pop();
                    temp = "(" + op2 + elem + op1 + ")";
                    stack.push(temp);
                } else {
                    stack.push(elem);
                }
            }
            assert temp != null;
            StringTokenizer st2 = new StringTokenizer(temp, "()+*-/", true);
            System.out.println("Espressione corrispondente " + temp + "=" + valutaOperando(st2));

        }
    }

    public static void main( String[] args ) throws IOException {
        new EspressioneRPNSenzaFILE();
    }


    public int valutaOperando( StringTokenizer st ) {
        String tk = st.nextToken();
        if (tk.charAt(0) == '(')
            return valutaEspressione(st);
        return Integer.parseInt(tk);
    }

    public int valutaEspressione( StringTokenizer st ) {

        Stack<Integer> operandi = new Stack<>();
        Stack<Character> operatori = new Stack<>();
        int ris = valutaOperando(st);
        operandi.push(ris);
        while (st.hasMoreTokens()) {
            char operatoreCorrente = st.nextToken().charAt(0);
            if (operatoreCorrente == ')')
                return operandi.pop();
            int operando = valutaOperando(st);
            operandi.push(operando);
            if (operatori.isEmpty() || priorita(operatori, operatoreCorrente))
                operatori.push(operatoreCorrente);
            char operatore = operatori.pop();
            int operando2 = operandi.pop();
            int operando1 = operandi.pop();
            switch (operatore) {
                case '+' -> operandi.push(Math.addExact(operando1, operando2));
                case '-' -> operandi.push(Math.subtractExact(operando1, operando2));
                case '*' -> operandi.push(Math.multiplyExact(operando1, operando2));
                case '/' -> operandi.push(Math.floorDiv(operando1, operando2));
                default -> throw new RuntimeException("Operatore inatteso");
            }
        }

        return operandi.pop();
    }

    private boolean priorita( Stack<Character> operatori, char operatoreCorrente ) {
        return precedenze.get(operatori.lastElement()) < precedenze.get(operatoreCorrente);
    }


}
