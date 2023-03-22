package poo.esercizio23_07_2019;

import java.io.*;

public class Esercizio2Sol2 {
    public static void main( String[] args ) throws IOException {
        DataInputStream dis = new DataInputStream(
                new FileInputStream("/Users/francesco/Desktop/topo"));
        DataOutputStream tmp = new DataOutputStream(
                new FileOutputStream("/Users/francesco/Desktop/tmp"));
        int x = 1, y = 0;
        boolean flag = false;
        for (; ; ) {
            try {
                y = dis.readInt();
                if (y < x) tmp.writeInt(y);
                else {
                    flag = true;
                    break;
                }
            } catch (IOException e) {
                break;
            }
        }
        tmp.writeInt(x); //una certezza
        if (flag) {
            tmp.writeInt(y); //y stava aspettando
            for (; ; ) {
                try {
                    y = dis.readInt();
                    tmp.writeInt(y);
                } catch (EOFException e) {
                    break;
                }
            }
        }
        //chiudi i file e vieni via
        dis.close();
        tmp.close();
    }
}
