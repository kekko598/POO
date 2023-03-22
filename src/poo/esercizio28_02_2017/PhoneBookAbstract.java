package poo.esercizio28_02_2017;

import java.util.Iterator;

public abstract class PhoneBookAbstract implements PhoneBook {
    @Override
    public String toString() {
        Iterator it = iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next() + "\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof PhoneBook)) return false;
        if (o == this) return true;
        if(size()!=((PhoneBook) o).size()) return false;
        Iterator i1 = iterator();
        Iterator i2 = ((PhoneBook) o).iterator();
        while (i1.hasNext()) {
            if (!i1.next().equals(i2.next()))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int M = 83;
        int h = 0;
        Iterator i1 = iterator();
        while (i1.hasNext())
            h *= M + iterator().next().hashCode();
        return h;
    }

}
