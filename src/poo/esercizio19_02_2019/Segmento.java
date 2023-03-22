package poo.esercizio19_02_2019;

public class Segmento {
    private int x, yi, yf;

    public Segmento( int x, int yi, int yf ) {
        if (x < 0 || yi < 0 || yf < 0 || yi > yf)
            throw new IllegalArgumentException();
        this.x = x;
        this.yi = yi;
        this.yf = yf;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("<");
        sb.append("x=").append(x);
        sb.append(",yi=").append(yi);
        sb.append(",yf=").append(yf);
        sb.append('>');
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof Segmento segmento)) return false;
        return yi == segmento.yi && yf == segmento.yf && x == segmento.x;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + yi;
        result = 31 * result + yf;
        return result;
    }

    public int getX() {
        return x;
    }

    public int getYi() {
        return yi;
    }

    public int getYf() {
        return yf;
    }

    public boolean consecutivo( Segmento s ) {
        if (Math.abs(s.x - this.x) > 5) return false;
        if (this.yf < s.yi || s.yf < this.yi) return false;
        return true;
    }

}
