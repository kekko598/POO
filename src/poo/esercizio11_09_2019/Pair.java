package poo.esercizio11_09_2019;

public class Pair<T, U> {
    private T first;
    private U second;

    public Pair( T first, U second ) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<")
                .append(first);
        sb.append(",")
                .append(second);
        sb.append(">");

        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<T, U> pair = (Pair<T, U>) o;
        return second.equals(pair.second) && first.equals(pair.first);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }
}