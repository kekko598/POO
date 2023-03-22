package poo.esercizio18_01_2023.esercizio1;

import java.util.List;

public interface Aereo{
    default Posto prenotaPosto(String passeggero, String bagaglio)
    {

        for(Posto p:postiAereo())
            if(postoDelPasseggiero(p)==null){
                prenotaPosto(passeggero,p,bagaglio);
                return p;
            }
        return null;
    };
    void prenotaPosto( String passeggero, Posto po, String bagaglio) throws IllegalArgumentException;

    String postoDelPasseggiero( Posto p);
    String cancellaPrenotazione(Posto p);
    String bagaglioDelPasseggero(String passeggero) throws IllegalArgumentException;
    List<String> passeggeri();
    List<String> bagagli();
    List<Posto> postiAereo();
    Aereo factory(Posto p);
}
