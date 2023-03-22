package poo.esercizio09_01_2020;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Schema implements Cruciverba {
    private char[][] cruciverba;

    public Schema( char[][] cruciverba ) {
        this.cruciverba = cruciverba;
    }

    public static void main( String[] args ) {
        //spazi bianchi = caratteri vuoti
        char[][] grid = {
                {'c', 'i', 'a', 'o'},
                {' ', ' ', 't', ' '},
                {'h', ' ', 'u', ' '},
                {'o', ' ', 't', ' '},
                {'l', ' ', 't', ' '},
                {'a', ' ', 'e', ' '},
        };
        char[][] grid2 = {
                {'c', 'l', 'a', 's', 's', 's'},
                {'o', 'b', 'j', 'e', 'c', 't'},
                {'i', 'n', 't', 'e', 'r', 'f', 'a', 'c', 'e'},
                {'e', 'x', 't', 'e', 'n', 'd', 's'},
                {'i', 'm', 'p', 'o', 'r', 't'}};

        Schema s = new Schema(grid);
        Schema s2 = new Schema(grid2);
        System.out.println(s.equals(s2));
        System.out.println(s2.getNumeroRighe());
        System.out.println(s2.getNumeroColonne());
        System.out.println(s2);
        System.out.println(s2.paroleOrizzontali());
        System.out.println(s2.paroleVericatili());
        System.out.println(s);
        System.out.println(s.paroleOrizzontali());
        System.out.println(s.paroleVericatili());
    }

    @Override
    public int getNumeroRighe() {
        return cruciverba.length;
    }

    @Override
    public int getNumeroColonne() {
        return cruciverba[0].length;
    }

    @Override
    public boolean contains( String parola ) {
        return paroleOrizzontali().contains(parola) || paroleVericatili().contains(parola);
    }

    @Override
    public List<String> paroleOrizzontali() {
        List<String> tmp = new LinkedList<>();
        for (int i = 0; i < cruciverba.length; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < cruciverba[i].length; j++) {
                if (cruciverba[i][j] != ' ')
                    s.append(cruciverba[i][j]);
            }
            if (s.length() > 0)
                tmp.add(s.toString());
        }
        Collections.sort(tmp, ( o1, o2 ) -> {
            if (o1.length() > o2.length()) return 1;
            if (o1.length() < o2.length()) return -1;
            return o1.compareTo(o2);
        });
        return tmp;
    }

    @Override
    public List<String> paroleVericatili() {
        List<String> tmp = new LinkedList<>();
        for (int j = 0; j < cruciverba[0].length; j++) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < cruciverba.length; i++) {
                if (cruciverba[i][j] != ' ')
                    s.append(cruciverba[i][j]);
            }
            if (s.length() > 0)
                tmp.add(s.toString());
        }
        Collections.sort(tmp, ( o1, o2 ) -> {
            if (o1.length() > o2.length()) return 1;
            if (o1.length() < o2.length()) return -1;
            return o1.compareTo(o2);
        });
        return tmp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cruciverba.length; i++) {
            for (int j = 0; j < cruciverba[i].length; j++)
                sb.append(cruciverba[i][j] + " ");
            sb.append("\n");
        }


        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schema schema = (Schema) o;
        return Arrays.deepEquals(cruciverba, schema.cruciverba);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cruciverba);
    }
}
