package poo.esercizio17_07_2015;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InsiemeOrdinatoArray<T extends Comparable<? super T>> extends InsiemeOrdinatoAstratto<T> {
    private T[] array;
    private int size;
    private Comparator<T> c = Comparator.naturalOrder();

    public InsiemeOrdinatoArray( int dim ) {
        if (dim < 1) throw new IllegalArgumentException();
        array = (T[]) new Comparable[dim];
    }

    public InsiemeOrdinatoArray() {

        this(17);
    }

    public InsiemeOrdinatoArray( Comparator<T> c ) {
        this(17);
        this.c = c;
    }

    public InsiemeOrdinatoArray( int dim, Comparator<T> c ) {
        if (dim < 1) throw new IllegalArgumentException();
        array = (T[]) new Comparable[dim];
        this.c = c;
    }

    public InsiemeOrdinatoArray( Insieme<T> t ) {
        size = t.size();
        array = (T[]) new Comparable[t.size()];
        int i = 0;
        for (T elem : t) {
            array[i] = t.get(elem);
            i++;
        }
    }

    public static void main( String[] args ) {
        InsiemeOrdinatoArray<Integer> i = new InsiemeOrdinatoArray<>();
        i.add(1);
        i.add(0);
        i.add(-1);
        i.add(5);
        i.add(2);
        System.out.println(i);
        System.out.println(i.first());
        System.out.println(i.last());
        System.out.println(new InsiemeOrdinatoArray<>(i));
        i.sort(Comparator.reverseOrder());
        System.out.println(i);
        InsiemeOrdinato<Integer> i3 = new InsiemeOrdinatoArray<Integer>(Comparator.reverseOrder());
        i3.add(2);
        i3.add(1);
        i3.add(5);
        i3.add(10);
        System.out.println(i3);
        System.out.println(i3.last());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add( T x ) {
        if (size == array.length)
            array = Arrays.copyOf(array, size * 2);
        array[size] = x;
        size++;
        if (size > 1)
            sort(c);
    }

    @Override
    public void sort( Comparator<T> c ) {
        boolean scambio = true;
        while (scambio) {
            scambio = false;
            for (int i = 0; i < size - 1; i++) {
                if (c.compare(array[i], array[i + 1]) > 0) {
                    T tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                    scambio = true;
                }
            }
        }
    }

    @Override
    public InsiemeOrdinato<T> factory() {
        return new InsiemeOrdinatoArray<>();
    }

    public T remove( int i ) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();
        T x = array[i];
        //left shift da i+1 a size-1
        for (int j = i + 1; j < size; ++j)
            array[j - 1] = array[j];
        size--;
        if (size < array.length / 2) {
            array = java.util.Arrays.copyOf(array, array.length / 2);
        }
        return x;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursore = -1;
            T elem;
            boolean rimuovibile = false;

            @Override
            public boolean hasNext() {
                return cursore < size - 1;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                cursore++;
                rimuovibile = true;
                elem = array[cursore];
                return elem;
            }

            @Override
            public void remove() {
                if (!(rimuovibile)) throw new IllegalStateException();
                rimuovibile = false;
                InsiemeOrdinatoArray.this.remove(cursore);
                cursore--;
            }
        };
    }
}
