package poo.esercizio21_06_2019;

import java.util.*;

public class Espressione {
    Map<Character, Integer> precedenze = new HashMap<>();

    public Espressione() {
        String s = "";
        precedenze.put('^', 3);
        precedenze.put('*', 2);
        precedenze.put('/', 2);
        precedenze.put('%', 2);
        precedenze.put('+', 1);
        precedenze.put('-', 1);
        String regex = "(\\d+|[\\^%*+-/()])+";//cond necessaria
        Scanner sc = new Scanner(System.in);
        System.out.println("Per interrompere inserimento, inserire .");
        while (true) {
            System.out.print("Inserisci espressione->");
            s = sc.nextLine();
            if (s.equals("."))
                break;
            if (!s.matches(regex))
                throw new IllegalArgumentException();
            StringTokenizer st = new StringTokenizer(s, "^*/%+-()", true);
            System.out.println("risultato expr " + s + "=" + valutaEspressione(st));
        }

    }

    public static void main( String[] args ) {
        Espressione e = new Espressione();
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

            while (!operatori.isEmpty() && priorita(operatori, operatoreCorrente)>=0){
                char operatore = operatori.pop();
                int operando2 = operandi.pop();
                int operando1 = operandi.pop();
                switch (operatore) {
                    case '+' -> operandi.push(Math.addExact(operando1, operando2));
                    case '-' -> operandi.push(Math.subtractExact(operando1, operando2));
                    case '*' -> operandi.push(Math.multiplyExact(operando1, operando2));
                    case '/' -> operandi.push(Math.floorDiv(operando1, operando2));
                    case '%' -> operandi.push(Math.floorMod(operando1, operando2));
                    case '^' -> operandi.push((int) Math.pow(operando1, operando2));
                    default -> throw new RuntimeException("Operatore inatteso");
                }
            }
            operatori.push(operatoreCorrente);

        }
        while (!operatori.isEmpty()){
            char operatore = operatori.pop();
            int operando2 = operandi.pop();
            int operando1 = operandi.pop();
            switch (operatore) {
                case '+' -> operandi.push(Math.addExact(operando1, operando2));
                case '-' -> operandi.push(Math.subtractExact(operando1, operando2));
                case '*' -> operandi.push(Math.multiplyExact(operando1, operando2));
                case '/' -> operandi.push(Math.floorDiv(operando1, operando2));
                case '%' -> operandi.push(Math.floorMod(operando1, operando2));
                case '^' -> operandi.push((int) Math.pow(operando1, operando2));
                default -> throw new RuntimeException("Operatore inatteso");
            }
        }
        return operandi.pop();
    }

    private int  priorita( Stack<Character> operatori, char operatoreCorrente ) {
          Map<Character, Integer> precedenza = new HashMap<>() {{
            put('+', 1);
            put('-', 1);
            put('*', 2);
            put('/', 2);
        }};
          Comparator<Character> c =  Comparator.comparingInt(precedenza::get);
    return  c.compare(operatori.lastElement(),operatoreCorrente);}
}
