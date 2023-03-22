package poo.esercizio18_01_2023.esercizio1;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class AereoImpl extends AereoAbstract{

    private Posto p;
    private Map<Posto,String> posto_passeggiero = new HashMap<>();
    private Map<String,String> passeggiero_bagaglio = new HashMap<>();

    public AereoImpl( Posto p ) {
        super(p);
        this.p = p;
        for(int i=0;i<p.Fila();i++)
            for(int j=0;j<p.Sedile();j++)
                posto_passeggiero.put(new Posto(i,j),null);
    }

    @Override
    public Aereo factory( Posto posto ) {
        return new AereoImpl(p);
    }
    @Override
    public void prenotaPosto( String passeggero, Posto po, String bagaglio ) {

        if(posto_passeggiero.containsValue(passeggero) &&  passeggiero_bagaglio.containsKey(passeggero))
            throw new IllegalArgumentException("E' già nell'aereo "+ passeggero);
        posto_passeggiero.putIfAbsent(po,passeggero);
        passeggiero_bagaglio.putIfAbsent(passeggero,bagaglio);
        if(!posto_passeggiero.containsValue(passeggero))
            throw new IllegalArgumentException("Posto occupato");


    }
    public boolean postoDisponibile(Posto p)
    {
        return postiAereo().contains(p);
    }
    @Override
    public List<Posto> postiAereo() {
        return new ArrayList<>(posto_passeggiero.keySet());
    }

    @Override
    public List<String> passeggeri() {
        return new ArrayList<>(posto_passeggiero.values());
    }

    @Override
    public List<String> bagagli() {
        List<String> tmp = new ArrayList<>();
        for(String x:passeggiero_bagaglio.keySet())
            if(passeggiero_bagaglio.get(x)!=null)tmp.add(passeggiero_bagaglio.get(x));
        return tmp;
    }

    @Override
    public String bagaglioDelPasseggero( String passeggero ) throws IllegalArgumentException {
        String bagaglio;
        if(passeggiero_bagaglio.containsKey(passeggero))
            bagaglio=passeggiero_bagaglio.get(passeggero);
        else
            throw new IllegalArgumentException("Non c'è il passeggiero in aereo");
        return bagaglio;
    }

    @Override
    public String postoDelPasseggiero( Posto p ) {
        return posto_passeggiero.get(p);
    }

    @Override
    public String cancellaPrenotazione( Posto p ) {
        String passeggiero=posto_passeggiero.replace(p,null);
        passeggiero_bagaglio.remove(passeggiero);
        return passeggiero;
    }


    public static void main( String[] args ) {
        Aereo a = new AereoImpl(new Posto(2,3));
        a.prenotaPosto("Francesco","b1");
        a.prenotaPosto("Giuseppe","b2");
        a.prenotaPosto("Anna","b1.2");
        a.prenotaPosto("Maria",null);

     // System.out.println(a.postiAereo());
     //  System.out.println(a.bagagli());
      // System.out.println(a.passeggeri());
      //  System.out.println(a.passeggeroDelPosto(new Posto(0,1)));
       // System.out.println(a.bagaglioDelPasseggero("Maria"));
       // System.out.println();
        //a.cancellaPrenotazione(new Posto(0,1));
         a.prenotaPosto("Mario",new Posto(0,2),"gucci");
        a.prenotaPosto("Marias",new Posto(0,3),"gucci");
        a.prenotaPosto("Ziuta",new Posto(1,0),"gucci");

        System.out.println(a);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Posto p:posto_passeggiero.keySet())
            sb.append(p).append(", passeggiero: ").append(posto_passeggiero.get(p)).append(" valigia: ").append(passeggiero_bagaglio.get(posto_passeggiero.get(p))).append("\n");
        System.out.println(passeggiero_bagaglio);
        return sb.toString();
    }
}
