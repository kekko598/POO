package poo.esercizio10_11_2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main( String[] args ) throws IOException {

        String nomeA, NomeB;
        Scanner sc = new Scanner(System.in);
        System.out.print("Primo file->");
        nomeA = sc.nextLine();
        System.out.print("Secondo file->");
        NomeB = sc.nextLine();
        File fa = new File(nomeA);
        File fb = new File(NomeB);
        sc.close();
        if (!fa.exists() || !fb.exists())
            throw new IllegalArgumentException("File non trovato/i");
        BufferedReader brA = new BufferedReader(new FileReader(fa));
        BufferedReader brB = new BufferedReader(new FileReader(fa));
        String regex = "^(i=\\d+ j=\\d+ v=\\d+|v=\\d+ i=\\d+ j=\\d+)$";
        String lineaA = null;
        String lineaB = null;
        MatriceSparsaMap<Integer> mA = new MatriceSparsaMap<>(2);
        MatriceSparsaMap<Integer> mB = new MatriceSparsaMap<>(2);

        while (true) {
            lineaA = brA.readLine();
            lineaB = brB.readLine();
            if (lineaA == null || lineaB == null)
                break;
            if (!lineaA.matches(regex) || !lineaB.matches(regex))
                throw new IllegalArgumentException();
            String[] partsA = lineaA.split(" ");
            String[] partsB = lineaB.split(" ");

            int iA = 0, iB = 0, jA = 0, jB = 0, valA = 0, valB = 0;

            for (int a = 0; a < partsA.length - 2 && a < partsB.length - 2; a++) {
                if (partsA[a].charAt(0) == 'i')
                    iA = Integer.parseInt(partsA[a].substring(2));
                if (partsA[a + 1].charAt(0) == 'j')
                    jA = Integer.parseInt(partsA[a + 1].substring(2));
                if (partsA[a + 2].charAt(0) == 'v')
                    valA = Integer.parseInt(partsA[a + 2].substring(2));
                if (partsA[a].charAt(0) == 'v')
                    valA = Integer.parseInt(partsA[a].substring(2));
                if (partsA[a + 1].charAt(0) == 'i')
                    iA = Integer.parseInt(partsA[a + 1].substring(2));
                if (partsA[a + 2].charAt(0) == 'j')
                    jA = Integer.parseInt(partsA[a + 2].substring(2));
                mA.set(iA, jA, valA);


                if (partsB[a].charAt(0) == 'i')
                    iB = Integer.parseInt(partsB[a].substring(2));
                if (partsB[a + 1].charAt(0) == 'j')
                    jB = Integer.parseInt(partsB[a + 1].substring(2));
                if (partsB[a + 2].charAt(0) == 'v')
                    valB = Integer.parseInt(partsB[a + 2].substring(2));
                if (partsB[a].charAt(0) == 'v')
                    valB = Integer.parseInt(partsB[a].substring(2));
                if (partsB[a + 1].charAt(0) == 'i')
                    iB = Integer.parseInt(partsB[a + 1].substring(2));
                if (partsB[a + 2].charAt(0) == 'j')
                    jB = Integer.parseInt(partsB[a + 2].substring(2));
                mB.set(iB, jB, valB);

            }


        }

        System.out.println(mA);
        System.out.println(mB);
        System.out.println(mA.addInt(mB));
        System.out.println(mA.mulInt(mB));

        brA.close();
        brB.close();

    }
}
