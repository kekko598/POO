package poo.file;

import java.io.*;

public class InsertionSortSuFile {
    public static void main( String[] args ) throws IOException {
        DataInputStream dis = new DataInputStream(
                new FileInputStream("interi.dat"));
        DataOutputStream tmp = new DataOutputStream(
                new FileOutputStream("tmp"));
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
            } catch (EOFException e) {
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

        File f1 = new File("interi.dat");

        f1.delete();
        File f2 = new File("tmp");
        f2.renameTo(f1);

    }
}
