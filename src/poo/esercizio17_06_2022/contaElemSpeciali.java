package poo.esercizio17_06_2022;

import java.util.LinkedList;
import java.util.List;

public class contaElemSpeciali {
    public static void main( String[] args ) {
        LinkedList<Integer> ls = new LinkedList<>();
        ls.add(5);ls.add(3);ls.add(1);ls.add(0);ls.add(2);ls.add(-2);
        ls.add(4);ls.add(6);ls.add(-9);
        System.out.println(ls);
        System.out.println(contaElementiSpeciali(10,ls));
    }
    public static int contaElementiSpeciali( int b, List<Integer> ls )
    {
        if(ls.size()<=1)
            throw new IllegalArgumentException();
        int c=0;
        for (int i = 2; i < ls.size(); i++) {
            int pDX = ls.get(i);
            int pSX = b - contaElementiSpeciali(ls,i);
            if(pDX>pSX)
                c++;
        }
        return c;
    }

    private static int contaElementiSpeciali(List<Integer> ls, int size ) {
        if(size==0)return 0;
        int cur=(ls.get(size-1));
        return  (cur + contaElementiSpeciali(ls,size-1));
    }
}
