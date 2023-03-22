package poo.esercizio10_11_2017;

public class ElementoColonna<T> {
    private int j;
    private T val;

    public ElementoColonna( int j, T val ) {
        this.j = j;
        this.val = val;
    }

    public int getJ() {
        return j;
    }

    public void setJ( int j ) {
        this.j = j;
    }

    public T getVal() {
        return val;
    }

    public void setVal( T val ) {
        this.val = val;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(val);
        return sb.toString();
    }
}
