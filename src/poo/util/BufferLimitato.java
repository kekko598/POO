package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BufferLimitato<T> extends CodaAstratta<T> {
    private T[] buffer;
    private int out, in, n, size;

    public BufferLimitato( int n ) {
        if (n <= 0) throw new IllegalArgumentException();
        buffer = (T[]) new Object[n];
        this.n = n;
        in = out = size = 0;
    }

    //si ridefiniscono solo alcuni metodi

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void poll( T x ) {
        if (size == n) throw new RuntimeException("Buffer pieno.");
        buffer[in] = x;
        in = (in + 1) % n;
        size++;
    }//add

    public T remove() {
        if (size == 0) throw new RuntimeException("Buffer vuoto.");
        T x = buffer[out];
        out = (out + 1) % n;
        size--;
        return x;
    }//remove

    public T peek() {
        if (size == 0) throw new RuntimeException("Buffer vuoto.");
        return buffer[out];
    }//peek

    public boolean contains( T x ) {
        for (int i = out; i != in; i = (i + 1) % n)
            if (buffer[i].equals(x)) return true;
        return false;
    }//contains

    public void clear() {
        for (int i = out; i != in; i = (i + 1) % n) buffer[i] = null;
        out = in = size = 0;
    }//clear

    public boolean isFull() {
        return size == n;
    }

    public Iterator<T> iterator() {
        return new BLIterator();
    }//iterator

    private class BLIterator implements Iterator<T> {
        private int cur = -1;
        private boolean rimuovibile = false;

        public boolean hasNext() {
            if (cur == -1) return size > 0;
            return (cur + 1) % n != in;
        }//hasNext

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (cur == -1) cur = out;
            else cur = (cur + 1) % n;
            rimuovibile = true;
            return buffer[cur];
        }//next

        public void remove() {
            if (!rimuovibile) throw new IllegalStateException();
            rimuovibile = false;
            int j = (cur + 1) % n;
            for (int i = j; i != in; i = (i + 1) % n) {
                buffer[(j - 1 + n) % n] = buffer[j];
            }
            cur = (cur - 1 + n) % n; //arretriamo cur
            in = (in - 1 + n) % n; //arretriamo in
            size--;
        }//remove

    }//BLIterator

}//BufferLimitato

