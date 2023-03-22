package poo.figure;


public class TrapezioIsoscele extends Figura {
    private final double altezza, baseMaggiore, latoObliquo;

    protected TrapezioIsoscele( double altezza, double baseMaggiore, double latoObliquo ) {
        super(baseMaggiore);
        this.latoObliquo = latoObliquo;
        if (altezza <= 0) throw new IllegalArgumentException();
        this.altezza = altezza;
        this.baseMaggiore = baseMaggiore;
    }

    public static void main( String[] args ) {
        Figura f = new TrapezioIsoscele(2, 10, 4);
        System.out.println(f.area());
    }

    @Override
    public String toString() {
        return "Rombo con base maggiore=" + getBaseMaggiore() +
                " base minore=" + getbaseMinore() +
                " lato obliquo=" + getLatoObliquo() +
                " proiezione lato obliquo=" + getproiezioneLatoOliquo() +
                " altezza=" + getAltezza();
    }

    private double getAltezza() {
        return altezza;
    }

    private double getLatoObliquo() {
        return latoObliquo;

    }

    private double getproiezioneLatoOliquo() {
        return Math.sqrt(getLatoObliquo() * getLatoObliquo()
                - altezza * altezza);
    }

    private double getbaseMinore() {
        return getBaseMaggiore() - getproiezioneLatoOliquo() * 2;

    }

    private double getBaseMaggiore() {
        return baseMaggiore;
    }

    @Override
    public double perimetro() {
        return getBaseMaggiore() + getbaseMinore() + 2 * getLatoObliquo();
    }

    @Override
    public double area() {
        return ((getBaseMaggiore() + getbaseMinore()) * getAltezza()) / 2;
    }
}
