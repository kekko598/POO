package poo.util;

import java.util.Iterator;

public abstract class StackAstratto<T> implements Stack<T> {

    public String toString() {
        StringBuilder sb = new StringBuilder(300);
        sb.append("[");
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T e = it.next();
            sb.append(e);
            if (it.hasNext()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }//toString

    public boolean equals( Object o ) {
        if (!(o instanceof Stack)) return false;
        if (o == this) return true;
        Stack<T> v = (Stack<T>) o;
        if (this.size() != v.size()) return false;
        Iterator<T> i1 = this.iterator(), i2 = v.iterator();
        while (i1.hasNext()) {
            T e1 = i1.next();
            T e2 = i2.next();
            if (!e1.equals(e2)) return false;
        }
        return true;
    }//equals

    public int hashCode() {
        final int M = 83;
        int h = 0;
        for (T e : this)
            h = h * M + e.hashCode();
        return h;
    }//hashCode

}//StackAstratto
