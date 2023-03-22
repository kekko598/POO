package poo.file;

import java.io.*;
import java.util.Scanner;

public class TestFiles {
    public static void main( String[] args ) throws IOException {
        DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("interi.dat"));
        System.out.println("Fornisci uno per linea interi sino al primo 0");
        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            int x = sc.nextInt();
            sc.nextLine();
            if (x == 0) break;
            dos.writeInt(x);
        }
        dos.close();
        DataInputStream dis = new DataInputStream(
                new FileInputStream("interi.dat"));
        for (; ; ) {
            try {
                int dato = dis.readInt();
                System.out.println(dato);
            } catch (EOFException e) {
                break;
            }
        }
        dis.close();
    }
}
