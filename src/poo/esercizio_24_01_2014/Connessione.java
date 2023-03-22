package poo.esercizio_24_01_2014;

import poo.grafo.*;

import java.io.*;
import java.util.*;

public class Connessione {
    private final Map<String, Peso> transitivePathConPesi = new HashMap<>();
    private final Map<ArcoPesato<String>, String> grafoMap = new LinkedHashMap<>();
    private final GrafoOrientato<String> grafo = new GrafoOrientatoImpl<>();
    private final Map<String, List<String>> transitivePaths = new HashMap<>();

    public Connessione( String s ) throws IOException {
        File f = new File(s);
        if (!f.exists())
            throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea = "";
        String nodo = "[a-z]*";
        String peso = "\\d+";
        String percorso = "\\w+";
        String regex = nodo + "\\s+" + nodo + "\\s+" + percorso + "\\s+" + peso;
        while (true) {
            linea = br.readLine();
            if (linea == null) break;
            if (!linea.matches(regex))
                throw new RuntimeException("Pattern errato deve essere nodo1 nodo2 percorso pesoANumeroIntero");
            Scanner sc = new Scanner(linea);
            sc.useDelimiter("\\s+");
            while (sc.hasNext()) {
                String n1 = sc.next();
                String n2 = sc.next();
                String per = sc.next();
                int pes = Integer.parseInt(sc.next());
                for (ArcoPesato<String> arco : grafoMap.keySet())
                    if (arco.getOrigine().equals(n1) && grafoMap.get(arco).equals(per))
                        throw new RuntimeException("controlla che da uno stesso nodo non fuoriescano connessioni multiple appartenenti ad uno stesso percorso");
                add(n1, n2, per, pes);
            }

        }
        br.close();

    }

    public static void main( String[] args ) throws IOException {

        Connessione c = new Connessione("/Users/francesco/Desktop/collegamenti");
        System.out.println("Grafo ->\n"+c);
        System.out.println("Connessione transitive del grafo ->\n"+c.connessioni());

    }

    public String connessioni() {

        if (!Grafi.aciclico(grafo))
            throw new RuntimeException("Grafo ciclico");
        findTransitivePaths();

        StringBuilder sb = new StringBuilder();
        for (ArcoPesato<String> arco : grafoMap.keySet()) {
            String x = arco.getOrigine() + "-" + arco.getDestinazione();
            transitivePathConPesi.remove(x);
            transitivePaths.remove(x);
        }
        for (String nodiCollegati : transitivePathConPesi.keySet())
            sb.append("<<").append(nodiCollegati).append(">").append(",").append(transitivePathConPesi.get(nodiCollegati)).append(">").append(transitivePaths.get(nodiCollegati)).append("\n");

        return sb.toString();
    }

    private void findTransitivePaths() {
        // per ogni nodo di partenza
        for (String start : getNodes()) {
            // per ogni nodo di arrivo
            for (String end : getNodes()) {
                // per ogni percorso
                if (!start.equals(end)) {
                    for (String path : new HashSet<>(grafoMap.values())) {
                        if (isTransitive(start, end, path)) {
                            // trova il peso del percorso
                            Peso weight = getPathWeight(start, end, path);
                            // aggiungi il percorso alla mappa transitivePaths e transitivePathWeights
                            String key = start + "-" + end;
                            if (!transitivePaths.containsKey(key)) {
                                transitivePaths.put(key, new ArrayList<>());
                                transitivePathConPesi.put(key, weight);
                            }
                            transitivePaths.get(key).add(path);
                        }
                    }
                }
            }
        }
    }

    private Peso getPathWeight( String start, String end, String path ) {
        Peso weight = new Peso(0);
        Set<String> visited = new HashSet<>();
        return dfs(start, end, path, weight, visited);
    }

    private Set<String> getNodes() {
        Set<String> nodes = new HashSet<>();
        for (ArcoPesato<String> edge : grafoMap.keySet()) {
            nodes.add(edge.getOrigine());
            nodes.add(edge.getDestinazione());
        }
        return nodes;
    }

    private boolean isTransitive( String start, String end, String path ) {
        Set<String> visited = new HashSet<>();
        return dfs(start, end, path, visited);
    }

    private Peso dfs( String current, String end, String path, Peso weight, Set<String> visited ) {
        if (current.equals(end)) {
            return weight;
        }
        visited.add(current);
        for (ArcoPesato<String> edge : grafoMap.keySet()) {
            if (edge.getOrigine().equals(current) && grafoMap.get(edge).equals(path) && !visited.contains(edge.getDestinazione())) {
                weight = (weight.piu(edge.getPeso()));
                Peso newWeight = dfs(edge.getDestinazione(), end, path, weight, visited);
                if (newWeight.compareTo(new Peso(0)) > 0)
                    return newWeight;
            }
        }
        return new Peso(-1);
    }

    private boolean dfs( String current, String end, String path, Set<String> visited ) {
        if (current.equals(end)) {
            return true;
        }
        visited.add(current);
        for (ArcoPesato<String> edge : grafoMap.keySet()) {
            if (edge.getOrigine().equals(current) && grafoMap.get(edge).equals(path) && !visited.contains(edge.getDestinazione())) {
                if (dfs(edge.getDestinazione(), end, path, visited)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArcoPesato<String> arco : grafoMap.keySet())
            sb.append(arco).append(" , ").append(grafoMap.get(arco)).append(">").append("\n");
        sb.append("\n").append(grafo);
        return sb.toString();
    }

    public void add( String origine, String destinazione, String percorso, int peso ) {
        grafoMap.put(new ArcoPesato<>(origine, destinazione, new Peso(peso)), percorso);
        if (!grafo.esisteNodo(origine))
            grafo.insNodo(origine);
        if (!grafo.esisteNodo(destinazione))
            grafo.insNodo(destinazione);
        grafo.insArco(origine, destinazione);
    }
}
