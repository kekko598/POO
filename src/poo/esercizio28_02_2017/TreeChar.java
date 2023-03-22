package poo.esercizio28_02_2017;


import poo.util.ABR;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeChar {

    ABR<Character> albero = new ABR<>();

    public static void main( String[] args ) {
        TreeChar t = new TreeChar();
        t.build("A.B.C.....D.");
        System.out.println(t.visitaAnticipata());
        System.out.println(t.visitaSimmetrica());
        System.out.println(t.visitaPosticipata());
    }

    public void build( String clip ) {
        String regex = "[A-Z](\\.[A-Z]*)*";
        if (!clip.matches(regex))
            throw new IllegalArgumentException();
        if (clip.isEmpty() || clip == null)
            return;
        StringTokenizer st = new StringTokenizer(clip, ".");
        while (st.hasMoreTokens())
            albero.add(st.nextToken().charAt(0));
    }

    public List<Character> visitaSimmetrica() {
        List<Character> ls = new ArrayList<>();
        albero.visitaSimmetrica(ls);
        return ls;
    }

    public List<Character> visitaAnticipata() {
        List<Character> ls = new ArrayList<>();
        albero.visitaAnticipata(ls);
        return ls;
    }

    public List<Character> visitaPosticipata() {
        List<Character> ls = new ArrayList<>();
        albero.visitaPosticipata(ls);
        return ls;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof TreeChar treeChar)) return false;
        return albero.equals(treeChar.albero);
    }

    @Override
    public String toString() {
        return albero.toString();
    }

}
