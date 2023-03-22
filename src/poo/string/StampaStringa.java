package poo.string;

public final class StampaStringa {
    public int size;
    public double[] a;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(String.format(("%1.2f"), a[i]));
        }
        sb.append(']');
        return sb.toString();
    }
}
