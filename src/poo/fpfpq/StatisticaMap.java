package poo.fpfpq;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StatisticaMap implements Statistica {

    private final Map<String, Map<String, Integer>> fpq = new TreeMap<>(
            ( s1, s2 ) -> {
                if (s1.length() < s2.length()) return -1;
                if (s1.length() > s2.length()) return 1;
                return s1.compareTo(s2);
            }
    );
    private Map<String, Integer> fp = new TreeMap<>(
            ( s1, s2 ) -> {
                if (s1.length() < s2.length()) return -1;
                if (s1.length() > s2.length()) return 1;
                return s1.compareTo(s2);
            }
    );

    public void arrivoParola( String p ) {
        fp.putIfAbsent(p, 0);
        fp.put(p, fp.get(p) + 1);
    }//arrivoParola

    public void paroleConsecutive( String p, String q ) {
        if (fp.get(p) == null || fp.get(q) == null)
            throw new IllegalArgumentException();
        fpq.computeIfAbsent(p, k -> new HashMap<>());
        Map<String, Integer> ad = fpq.get(p);
        ad.putIfAbsent(q, 0);
        ad.put(q, ad.get(q) + 1);
    }//paroleConsecutive

    public int numTotaleParole() {
        int c = 0;
        for (String p : fp.keySet())
            c = c + fp.get(p);
        return c;
    }//numTotaleParole

    public int frequenza( String p ) {
        if (fp.get(p) == null) return 0;
        return fp.get(p);
    }//frequenza

    public int frequenzaCoppia( String p, String q ) {
        if (fp.get(p) == null || fp.get(q) == null) throw new IllegalArgumentException();
        Map<String, Integer> ad = fpq.get(p);
        if (ad.get(q) == null) return 0;
        return ad.get(q);
    }//frequenzaCoppia

    public String parolaCheSeguePiuFrequente( String target ) {
        if (fp.get(target) == null)
            throw new IllegalArgumentException(target + " inesistente");
        Map<String, Integer> ad = fpq.get(target);
        int fmax = 0;
        String ppf = null;
        for (String q : ad.keySet())
            if (ad.get(q) > fmax) {
                fmax = ad.get(q);
                ppf = q;
            }
        return ppf;
    }//parolaCheSeguePiuFrequente

    public String parolaCheSegueMenoFrequente( String target ) {
        if (fp.get(target) == null)
            throw new IllegalArgumentException(target + " inesistente");
        Map<String, Integer> ad = fpq.get(target);
        int fmin = Integer.MAX_VALUE;
        String pmf = null;
        for (String q : ad.keySet())
            if (ad.get(q) < fmin) {
                fmin = ad.get(q);
                pmf = q;
            }
        return pmf;
    }//parolaCheSegueMenoFrequente

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String p : fp.keySet()) {
            sb.append(p).append(" : fp=").append(String.format("%1.3f%n", (double) frequenza(p) / numTotaleParole()));
            sb.append("\t");
            Map<String, Integer> ad = fpq.get(p);
            if (ad != null) {
                for (String q : ad.keySet()) {
                    double fr = (double) ad.get(q) / fp.get(p);
                    sb.append(q).append(" : fpq=").append(String.format("%1.2f ", fr));
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }//toString

}//StatisticaMap

