package poo.esercizio10_11_2017;


public abstract class MatriceSparsaAstratta<T> implements MatriceSparsa<T> {
    private int n;

    public MatriceSparsaAstratta( int n ) {
        this.n = n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(get(i, j)).append(" ");
            sb.append("\n");

        }
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof MatriceSparsaAstratta<?> that)) return false;
        MatriceSparsa<T> m = (MatriceSparsa<T>) o;
        if (n != m.getN()) return false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (!m.get(i, j).equals(get(i, j)))
                    return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int h = 83;
        int a = 10;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a += h * get(i, j).hashCode();
        return a;
    }


}
