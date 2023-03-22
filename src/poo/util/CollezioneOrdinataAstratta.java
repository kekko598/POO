package poo.util;

import java.util.Iterator;

public abstract class CollezioneOrdinataAstratta<T extends Comparable<? super T>> implements CollezioneOrdinata<T> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T e = it.next();
            sb.append(e);
            if (it.hasNext())
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof CollezioneOrdinata)) return false;
        if (o == this) return true;
        CollezioneOrdinata<T> v = (CollezioneOrdinata<T>) o;
        if (this.size() != v.size()) return false;
        Iterator<T> i1 = this.iterator(), i2 = v.iterator();
        while (i1.hasNext()) {
            if (!(i1.next().equals(i2.next()))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int m = 83;
        int h = 0;
        for (T e : this)
            h *= m + e.hashCode();
        return h;
    }
}
