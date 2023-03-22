package poo.app;

import poo.util.Stack;
import poo.util.StackArray;

import java.util.Scanner;

public class TestPalindroma {
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Fonisci una stringa come str1$str2 : ");
        String linea = sc.nextLine();
        String INPUT = "\\w+\\$\\w+";
        if (!linea.matches(INPUT)) {
            System.out.println(linea + " stringa invalida.");
            System.exit(-1);
        }
        Stack<Character> stack = new StackArray<>();
        int i = 0;
        while (linea.charAt(i) != '$') {
            stack.push(linea.charAt(i));
            i++;
        }
        i++; //supera $
        boolean palindroma = true; //ottimismo
        while (i < linea.length()) {
            char c1 = stack.pop();
            char c2 = linea.charAt(i);
            if (c1 != c2) {
                palindroma = false;
                break;
            }
            i++;
        }
        if (!palindroma || i == linea.length() && !stack.isEmpty()) palindroma = false;
        System.out.println(linea + " e' palindroma? " + palindroma);


    }//main
}//TestPalindrom
