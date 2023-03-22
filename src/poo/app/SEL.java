package poo.app;

import poo.sistema.Gauss;
import poo.sistema.Sistema;
import poo.util.Matrix;

import java.util.Arrays;
import java.util.Scanner;

public class SEL {
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        System.out.print("n= ");
        int n = sc.nextInt();
        sc.nextLine();
        double[][] a = new double[n][n];
        double[] y = new double[n];
        System.out.println("Fornisci la matrice per righe " + n + "*" + n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("a[" + i + "," + j + "]->");
                a[i][j] = sc.nextDouble();
                sc.nextLine();
            }
        }
        System.out.println("Fornisci i termini noti " + n);
        for (int i = 0; i < y.length; i++) {
            System.out.print("y[" + i + "]->");
            y[i] = sc.nextDouble();
            sc.nextLine();
        }
        try {
            Sistema s = new Gauss(a, y);
            //  Sistema s1  = new GaussDiagonale(a,y);
            System.out.println("Matrice originale:");
            System.out.println(s);
            s.risolvi();
            System.out.println();
            System.out.println("Matrice ridottà con Gauss:");
            System.out.println(s);
            System.out.println("Il determinante è " + Matrix.detGauss(a) + " calcolo effettuato escludendo i termini noti!");
            System.out.println();
            double[] x = s.risolvi();
            System.out.println("Soluzioni del sistema : " + Arrays.toString(x));
            // System.out.println("Matrice ridottà con Gauss diagonale:");
            // System.out.println(s1);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Sistema singolare!");
        } finally {
            sc.close();
        }
    }
}
