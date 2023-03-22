package poo.esercizio15_03_2011;

public class OccorrenzaParola{
    private int x;
    private int y;
    private Parola parola;
    private Direzione direzione;

    public OccorrenzaParola( int x, int y, Parola parola, Direzione direzione ) {
        this.x = x;
        this.y = y;
        this.parola = parola;
        this.direzione = direzione;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Parola getParola() {
        return parola;
    }

    public Direzione getDirezione() {
        return direzione;
    }

    @Override
    public String toString() {
        return "OccorrenzaParola{" +
                "x=" + x +
                ", y=" + y +
                ", parola=" + parola +
                ", direzione=" + direzione +
                '}';
    }

}
