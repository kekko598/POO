package poo.app;

import java.io.*;

public class InserctionSortSuFile {
    public static void main( String[] args ) throws IOException {
        DataInputStream dis = new DataInputStream(
                new FileInputStream("interi.dat"));
        DataOutputStream tmp = new DataOutputStream(
                new FileOutputStream("tmp"));
        int x = 15;
        int y = 0;
        boolean flag = false;
        while (true) {
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
        if (flag)//sono alla fine del file
        {
            tmp.writeInt(y); //y aspettava
            while (true) {
                try {
                    y = dis.readInt();
                    tmp.writeInt(y);
                } catch (EOFException e) {
                    break;
                }
            }
        }
        dis.close();
        tmp.close();
        File f1 = new File("finteri.dat");
        File f2 = new File("tmp");
        f2.renameTo(f1);
    }
}
