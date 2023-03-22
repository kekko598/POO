package poo.string;

import poo.polinomi.Polinomio;
import poo.polinomi.PolinomioLL;

import java.util.Scanner;

public class InputPolinomio {


    public static void main( String[] args ) {
        Scanner sn = new Scanner(System.in);
        System.out.println("inserisci un polinomio");
        while (true) {
            try {
                Polinomio p = new PolinomioLL();
                System.out.print("->");
                String linea = sn.nextLine();
                if (linea.length() == 0) break;
                Polinomio.parse(linea, p);
                System.out.println(p);
            } catch (Exception e) {
                System.out.println("Riprova a inserire");
            }

        }
    }
}

