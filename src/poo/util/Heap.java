package poo.util;

import java.util.*;

public class Heap<T extends Comparable<? super T>> implements Iterable<T> {
    private T[] heap;
    private int n, size;

    public Heap( int n ) {
        if (n < 1) throw new IllegalArgumentException();
        this.n = n;
        heap = (T[]) new Comparable[n + 1];
    }

    public Heap() {
        this(11);
    }


    public static void main( String[] args ) {
        Heap<Integer> heap = new Heap<>();
        /* heap.add(10);
        heap.add(4);
        heap.add(2);
        heap.add(12);
        heap.add(70);
        heap.add(6);
        heap.add(5);
        heap.add(20);
        heap.add(18);
        heap.remove(20);
         */
        /*
         heap.add(34);
        heap.add(-2);
        heap.add(14);
        heap.add(18);
        heap.add(7);
        heap.add(40);
        heap.add(3);
        heap.add(6);
        heap.add(17);
        heap.add(-5);
        heap.add(37);
        heap.remove();
         */
        /*
         heap.add(10);
        heap.add(2);
        heap.add(5);
        heap.add(-3);
        heap.add(-1);
        heap.add(7);
        heap.add(12);
        heap.add(4);
        heap.remove(-1);
         */
        heap.add(16);heap.add(10);heap.add(12);heap.add(-3);heap.add(-5);heap.add(4);heap.add(7);heap.add(-1);heap.add(17);heap.add(13);
        heap.remove();
        System.out.println(heap);


    }

    public int size() {
        return size;
    }

    public boolean contains( T x ) {
        if (size == 0 || x.compareTo(heap[1]) > 0) return false;
        for (int i = 1; i <= size; i++)
            if (x.equals(heap[i])) return true;
        return false;
    }

    public void add( T x ) {
        if (size == n) {
            Arrays.copyOf(heap, 2 * n + 1);
            n = 2 * n; //aggiorno la grandezza dell'array
        }
        size++;
        heap[size] = x;
        //riaggiustiamo l'heap verso l'alto
        int i = size; //ultimo arrivato
        while (i > 1) {
            //contronto il figlio con il padre per scambio
            if (heap[i].compareTo(heap[i / 2]) < 0) {
                T park = heap[i];
                heap[i] = heap[i / 2];
                heap[i / 2] = park;
                i = i / 2;
            } else
                //figlio più grande del padre
                break;

        }
    }

    public void offer( T x ) {
        add(x);
    }

    public T peek() {
        if (size == 0) throw new NoSuchElementException();
        return heap[1];
    }

    public T remove() {
        if (size == 0) return null;
        T x = heap[1]; //minimo
        heap[1] = heap[size]; //promozione, sposto l'ultimo elemento più a dx in prima posizione nella radice
        heap[size] = null;
        size--;
        int i = 1;
        while (i <= size / 2) //size/2  vuol dire che arrivo a size/2 per confronto per 2i e 2i+1 siccome devo vedermi con i figli
        {
            int j = 2 * i;
            int k = 2 * i + 1;
            int z = j; //ipotesi del minimo
            if (k <= size && heap[k].compareTo(heap[z]) < 0) //scambio sx con dx
                z = k;
            if (heap[i].compareTo(heap[z]) > 0) {//scambio figlio con padre
                //i è il padre z è il minimo trovato
                T park = heap[i];
                heap[i] = heap[z];
                heap[z] = park;
                i = z;
            } else break;
        }
        return x;
    }

    public void remove( T x ) {
        int i = 1;
        for (; i <= size; i++) //incremento i fin quando non trovo l'elemento da rimuovere
            if (heap[i].equals(x)) break;
        if (i > size) return;
        if (i == 1) {
            remove();
            return;
        }
        int limite = size;
        size = i - 1;
        for (int j = i + 1; j <= limite; j++)
            add(heap[j]);

    }

    public T poll() {
        return remove();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new HeapIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 1; i <= size; i++) {
            sb.append(heap[i]);
            if (i < size)
                sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    private class HeapIterator implements Iterator<T> {
        //ipotesi gli elementi sono tutti distinti
        private int cor = 0;
        private boolean rimuovibile = false;
        private Set<T> elementiConsumati = new HashSet<>();

        @Override
        public boolean hasNext() {
            return cor < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            cor++;
            rimuovibile = true;
            while (elementiConsumati.contains(heap[cor])) cor++;
            return heap[cor];
        }

        @Override
        public void remove() {
            if (!rimuovibile) throw new IllegalStateException();
            rimuovibile = false;
            Heap.this.remove(heap[cor]);
            elementiConsumati.remove(heap[cor]);
            cor = 0;//tanto se incontro elementi consumati li salto quindi parto da capo cmq
        }
    }
}
