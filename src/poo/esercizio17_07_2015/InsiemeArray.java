package poo.esercizio17_07_2015;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InsiemeArray<T> extends InsiemeAstratto<T> {
    private T[] array;
    private int size;

    public InsiemeArray( int dim ) {
        if (dim < 1) throw new IllegalArgumentException();
        array = (T[]) new Object[dim];
    }

    public InsiemeArray() {
        this(17);
    }

    public InsiemeArray( Insieme<T> t ) {

        size = t.size();
        array = (T[]) new Object[t.size()];
        int i = 0;
        for (T elem : t) {
            array[i] = t.get(elem);
            i++;
        }
    }

    public static void main( String[] args ) {
        InsiemeArray<Integer> i = new InsiemeArray<>();
        i.add(1);
        i.add(0);
        i.add(-1);
        Insieme<Integer> i2 = new InsiemeArray<>(i);
        i2.add(5);
        i2.remove(1);
        i2.remove(0);
        System.out.println(i);
        System.out.println("Elementi effettivi " + i.size());
        System.out.println(i2);
        System.out.println("Elementi effettivi " + i2.size());
        i.sort(Comparator.naturalOrder());
        System.out.println(i);

    }

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
    public int size() {
        return size;
    }

    @Override
    public void add( T x ) {
        if (size == array.length)
            array = Arrays.copyOf(array, size * 2);
        array[size] = x;
        size++;
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
                InsiemeArray.this.remove(cursore);
                cursore--;
            }
        };
    }
}
