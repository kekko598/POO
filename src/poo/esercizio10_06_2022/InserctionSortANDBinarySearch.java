package poo.esercizio10_06_2022;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public final class InserctionSortANDBinarySearch {

    public static void main( String[] args ) {

        ArrayList<Integer> ls = new ArrayList<>(List.of(new Integer[]{10,20}));
        System.out.println(binarySearch(ls,20));
        System.out.println(ls);
    }

    public static <T extends Comparable<? super T>> void inserctionSort( java.util.List<T> ls) {

        if(ls.size()<=1)return;
        ListIterator<T> currentIterator = ls.listIterator();
        while ( currentIterator.hasNext()) {
            T x = currentIterator.next();
            ListIterator<T> previousIterator = ls.listIterator(currentIterator.previousIndex());
            while (previousIterator.hasPrevious()){
                T y = previousIterator.previous();
                if(y.compareTo(x)>0){
                    previousIterator.next();previousIterator.next();
                    previousIterator.set(y);
                    previousIterator.previous();previousIterator.previous();
                }
                else {
                    previousIterator.next();
                    break;
                }
                currentIterator.previous();previousIterator.set(x);
            }
        }


    }
    public static  <T extends Comparable<? super T>>   int binarySearch( java.util.List<T> ls, T x)
    {
        if(ls.size()<=1)
            throw new IllegalArgumentException("Mettici almeno 2 elementi");
        for (int i = 0; i < ls.size()-1; i++) {
            if(ls.get(i).compareTo(ls.get(i+1))>0)
                throw new RuntimeException("Array disordinato");
        }
        return binarySearch(ls,x,0,ls.size()-1);

    }
    private static  <T extends Comparable<? super T>>   int binarySearch( java.util.List<T> ls, T x,int inf,int sup)
    {
        if(inf>sup)return -1;
        int med = (inf+sup)/2;
        if(ls.get(med).equals(x))return med;
        if(ls.get(med).compareTo(x)>0)
            return binarySearch(ls,x,0,med-1);
        return binarySearch(ls,x,med+1,sup);
    }
}
