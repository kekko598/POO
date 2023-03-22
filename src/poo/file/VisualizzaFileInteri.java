package poo.file;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class VisualizzaFileInteri {
    public static void main( String[] args ) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome file di interi da visualizzare: ");
        String nomeFile = sc.nextLine();
        sc.close();

        DataInputStream dis = new DataInputStream(
                new FileInputStream(nomeFile)
        );

        for (; ; ) {
            try {
                int x = dis.readInt();
                System.out.println(x);
            } catch (EOFException e) {
                break;
            }
        }
        dis.close();
    }
}
