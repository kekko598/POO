package poo.esercizio17_06_2022;

import java.util.*;

public class Sistema {
    private final LinkedList<Volo> voli = new LinkedList<>();
    private final LinkedList<Prenotazione> prenotazioni = new LinkedList<>();

    public static void main( String[] args ) {
        Sistema s = new Sistema();
        Volo v1 = new Volo("roma", "milano", 300, 150);
        Volo v2 = new Volo("lamezia", "roma", 200, 120);//prenotato
        Volo v3 = new Volo("roma", "londra", 450, 250);
        Volo v4 = new Volo("milano", "parigi", 350, 200);//prenotato
        s.getVoli().add(v1);
        s.getVoli().add(v2);
        s.getVoli().add(v3);
        s.getVoli().add(v4);
        Prenotazione p1 = new Prenotazione(new LinkedList<>(List.of("lamezia", "roma", "londra")), "rossi", "business");
        Prenotazione p2 = new Prenotazione(new LinkedList<>(List.of("roma", "milano", "parigi")), "rossi", "business");
        Prenotazione p3 = new Prenotazione(new LinkedList<>(List.of("milano", "parigi")), "bianchi", "economica");
        Prenotazione p4 = new Prenotazione(new LinkedList<>(List.of("lamezia", "milano", "parigi")), "bianchi", "economica");
        Prenotazione p5 = new Prenotazione(new LinkedList<>(List.of("lamezia", "roma")), "verdi", "economica");
        s.getPrenotazioni().add(p1);
        s.getPrenotazioni().add(p2);
        s.getPrenotazioni().add(p3);
        s.getPrenotazioni().add(p4);
        s.getPrenotazioni().add(p5);
        //System.out.println(s);
        System.out.println(s.verificaPrenotazioni());
        System.out.println(s.voloMax());
        System.out.println(s.destinazioneComune("rossi"));
    }

    public boolean verificaPrenotazioni() {
        for (Prenotazione p : prenotazioni) {
            List<String> percorso = p.percorso();
            boolean trovato = false;
            for (int i = 0; i < percorso.size() - 1; i++) {
                String partenza = percorso.get(i);
                String arrivo = percorso.get(i + 1);
                for (Volo v : voli) {
                    if (partenza.equals(v.partenza()) && arrivo.equals(v.arrivo())) {
                        trovato = true;
                        break;
                    }
                }
            }
            if (!trovato) return false;
        }
        return true;
    }

    public Volo voloMax() {

        Map<Volo, Integer> costoVoli = new HashMap<>();
        Volo vRis = null;
        int nPrenBusiness = 0, nPrenEconomica = 0;
        for (Prenotazione p : prenotazioni) {
            List<String> percorso = p.percorso();
            for (Volo v : voli) {
                for (int i = 0; i < percorso.size() - 1; i++) {
                    String partenza = percorso.get(i);
                    String arrivo = percorso.get(i + 1);
                    if (partenza.equals(v.partenza()) && arrivo.equals(v.arrivo())) {
                        if (p.classe().equals("economica"))
                            nPrenEconomica++;
                        if (p.classe().equals("business"))
                            nPrenBusiness++;
                        int formula = (nPrenBusiness * v.prezzoBusiness()) + (nPrenEconomica * v.prezzoEconomia());
                        costoVoli.put(v, formula);
                    }

                }

            }
        }
        int max = Collections.max(costoVoli.values());
        for (Volo v : costoVoli.keySet()) {
            if (max == costoVoli.get(v)) {
                vRis = v;
                break;
            }

        }
        return vRis;
    }

    public LinkedList<String> destinazioneComune( String cliente ) {
        LinkedList<Prenotazione> prenotazioniCliente2 = new LinkedList<>();
        LinkedList<String> clienti = new LinkedList<>();
        for (Prenotazione p : prenotazioni)
            if (p.cliente().equals(cliente))
                prenotazioniCliente2.add(p);
        for (Prenotazione p2 : prenotazioniCliente2) {
            for (Prenotazione p : prenotazioni) {
                if (!p2.cliente().equals(p.cliente())) {
                    if (p2.percorso().get(p2.percorso().size() - 1).equals(p.percorso().get(p.percorso().size() - 1))) {
                        clienti.add(p.cliente());
                        break;
                    }
                }
            }
        }
        return clienti;
    }

    public LinkedList<Volo> getVoli() {
        return voli;
    }

    public LinkedList<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    @Override
    public String toString() {
        return "Sistema{" +
                "voli=" + voli +
                ", prenotazioni=" + prenotazioni +
                '}';
    }
}
