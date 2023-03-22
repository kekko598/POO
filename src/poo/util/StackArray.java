package poo.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackArray<T> extends StackAstratto<T> {

    private T[] array;
    private int size;

    public StackArray( int n ) {
        if (n <= 0) throw new IllegalArgumentException();
        array = (T[]) new Object[n];
    }

    public StackArray() {
        this(13);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push( T x ) {
        //aggiungo sempre in cima, cioè dal basso a l'alto
        if (size == array.length)
            array = Arrays.copyOf(array, size * 2);
        array[size] = x;
        size++;
    }

    @Override
    public T pop() {
        if (size == 0) throw new NoSuchElementException();
        T x = array[size - 1]; //size -1 perché size punta al primo libero e size-1 è l'ultimo occupato
        size--;
        array[size] = null;
        return x;
    }

    @Override
    public T top() {
        if (size == 0) throw new NoSuchElementException();
        T x = array[size - 1];
        return x;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackArrayIterator();
    }

    private class StackArrayIterator implements Iterator<T> {
        private int cur = size; //size punta al primo libero
        private boolean rimuovibile = false;

        @Override
        public boolean hasNext() {
            return cur > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            cur--;
            rimuovibile = true;
            return array[cur];
        }

        @Override
        public void remove() {
            if (!rimuovibile) throw new IllegalStateException();
            rimuovibile = false;
            for (int j = cur + 1; j < size; j++)
                array[j - 1] = array[j];//sposto tutto a sinistra di un posto
            size--;
            array[size] = null; //curr già punta ad un elemento consumato
        }


    }
}
