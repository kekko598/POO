package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackConcatenato<T> extends StackAstratto<T> {
    private Nodo<T> testa = null;
    private int size = 0;

    public static void main( String[] args ) {
        Stack<Integer> stack = new StackConcatenato<>();
        stack.push(1);
        stack.push(-5);
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            int x = (int) it.next();
            if (x == 1) it.remove();
        }
        System.out.println(stack);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains( T x ) {
        Nodo<T> cor = testa;
        while (cor != null) {
            if (cor.info.equals(x)) return true;
            cor = cor.next;
        }
        return false;
    }

    @Override
    public void clear() {
        testa = null;
        size = 0;
    }

    @Override
    public void push( T x ) {
        Nodo<T> n = new Nodo<>();
        n.info = x;
        n.next = testa;
        testa = n;
        size++;
    }

    @Override
    public T pop() {
        if (testa == null) throw new NoSuchElementException();
        T x = testa.info;
        testa = testa.next;
        size--;
        return x;
    }

    @Override
    public T top() {
        if (testa == null) throw new NoSuchElementException();
        T x = testa.info;

        return x;
    }


    @Override
    public boolean isEmpty() {
        return testa == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LOCIIteratore();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        Nodo<T> cor = testa;
        sb.append("{");
        while (cor != null) {
            sb.append(cor.info);
            if (cor.next != null)
                sb.append(", ");
            cor = cor.next;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StackConcatenato<T> that = (StackConcatenato<T>) o;
        if (size != that.size) return false;
        StackConcatenato.Nodo<T> cor1 = testa;
        StackConcatenato.Nodo<T> cor2 = that.testa;
        while (cor1 != null) {
            if (!cor1.info.equals(cor2.info))
                return false;
            cor1 = cor1.next;
            cor2 = cor2.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int P = 83;
        int h = 0;
        Nodo<T> cor = testa;
        while (cor != null) {
            h *= P + cor.info.hashCode();
            cor = cor.next;
        }
        return h;
    }

    private static class Nodo<E> {
        E info;
        Nodo<E> next;

    }

    private class LOCIIteratore implements Iterator<T> {

        private Nodo<T> cur = null, pre = null;

        @Override
        public boolean hasNext() {
            if (cur == null)
                return testa != null;
            return cur.next != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (cur == null)
                cur = testa; // sono in testa
            else {
                pre = cur;
                cur = cur.next;
            }
            return cur.info;
        }

        @Override
        public void remove() {
            if (pre == cur) throw new IllegalStateException();
            if (cur == testa) {
                testa = testa.next;
            } else {
                //Bypass
                pre.next = cur.next;
                //cur è isolato attualmente, allora lo porto indietro
                //in modo che evito di fare subito la remove subito dopo

                cur = pre;
                size--;
            }

        }
    }
}
