package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayVector<T> extends AbstractVector<T> {
    private T[] array;
    private int size;

    public ArrayVector( int capacita ) {
        if (capacita <= 0)
            throw new IllegalArgumentException();
        array = (T[]) new Object[capacita];
    }

    public ArrayVector() {
        this(17);
    }

    public ArrayVector( Vector<T> v ) {
        size = v.size();//costruttore di copia
        array = (T[]) new Object[v.size()];
        for (int i = 0; i < v.size(); ++i)
            array[i] = v.get(i);

    }

    public static void main( String[] args ) {
        Vector<Integer> a = new ArrayVector<>();
        a.add(10);
        a.add(20);
        Iterator<Integer> it = a.iterator();
        while (it.hasNext()) {
            int x = it.next();
            if (x == 10) it.remove();
        }
        System.out.println(a);
    }

    public int size() {
        return size;
    }//size

    public int indexOf( T e ) {
        for (int i = 0; i < size; ++i)
            if (array[i].equals(e)) return i;
        return -1;
    }//indexOf

    public boolean contains( T e ) {
        return indexOf(e) != -1;
    }//contains

    public T get( int i ) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();
        return array[i];
    }//get

    public T set( int i, T e ) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();
        T x = array[i];
        array[i] = e;
        return x;
    }//set

    public void add( T e ) {
        if (size == array.length) {
            array = java.util.Arrays.copyOf(array, size * 2);
        }
        array[size] = e;
        size++;
    }//add

    public void add( int i, T e ) {
        if (size == array.length) {
            array = java.util.Arrays.copyOf(array, size * 2);
        }
        //right shift da i a size-1
        for (int j = size - 1; j >= i; --j)
            array[j + 1] = array[j];
        array[i] = e;
        size++;
    }//add

    public void remove( T e ) {
        int i = indexOf(e);
        if (i == -1) return;
        //left shift da i+1 a size-1
        for (int j = i + 1; j < size; ++j)
            array[j - 1] = array[j];
        size--;
        if (size < array.length / 2) {
            array = java.util.Arrays.copyOf(array, array.length / 2);
        }
    }//remove

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
    }//remove

    public void clear() {
        for (int i = 0; i < size; ++i) array[i] = null;
        size = 0;
    }//clear

    public boolean isEmpty() {
        return size == 0;
    }//isEmpty

    public Vector<T> subVector( int da, int a ) {
        if (da < 0 || da >= size || a < da || a > size)
            throw new IllegalArgumentException();
        ArrayVector v = new ArrayVector(a - da);
        for (int i = da; i < a; ++i)
            v.add(array[i]);
        //v.size già ok
        return v;
    }//subVector

    @Override
    public Iterator<T> iterator() {
        return new VectorIterator();
    }

    public class VectorIterator implements Iterator<T> {

        private int cursore = -1;
        private boolean rimuovibile = true;

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
            return array[cursore];

        }

        @Override
        public void remove() {
            if (!(rimuovibile)) throw new IllegalStateException();
            rimuovibile = false;
            ArrayVector.this.remove(cursore);
            cursore--;
        }
    }
}
