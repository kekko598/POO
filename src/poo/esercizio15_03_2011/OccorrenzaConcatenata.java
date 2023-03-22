package poo.esercizio15_03_2011;

import java.io.*;
import java.util.*;

public class OccorrenzaConcatenata implements Iterable<OccorrenzaParola>{
    private LinkedList<Parola> vocabolario = new LinkedList<>();
    private Puzzle puzzle;
    private LinkedList<OccorrenzaParola> occorrenze;
    private Match m = new Match();
    public OccorrenzaConcatenata( String percorsoPuzzle, String percorsoParole, int n, int m) throws IOException {
       puzzle= new Puzzle(n,m);
        File fPuzzle = new File(percorsoPuzzle);
        File fParole = new File(percorsoParole);
        if(!(fPuzzle.exists()) || !(fParole.exists()))
            throw new IllegalArgumentException();
        BufferedReader brParole = new BufferedReader(new FileReader(fParole));
        String lineaP="";
        String regexP="[a-zA-Z]+";
        while (true){
            lineaP= brParole.readLine();
            if(lineaP==null) break;
            if(!lineaP.matches(regexP))
                throw new RuntimeException();
            Scanner sc = new Scanner(lineaP);
            sc.useDelimiter("\\n+");
            vocabolario.add(new Parola(sc.next()));
        }
        brParole.close();
        BufferedReader brPuzzle = new BufferedReader(new FileReader(fPuzzle));
        String lineaPuzzle="";
        String carattere = "[a-zA-Z]$";
        String regexPuzzle="i=\\d+\\s+j=\\d+\\s+v="+carattere;
        while (true){
            lineaPuzzle=brPuzzle.readLine();
            if(lineaPuzzle==null)break;
            if(!lineaPuzzle.matches(regexPuzzle))
                throw new IllegalArgumentException();
            StringTokenizer st = new StringTokenizer(lineaPuzzle," =");
            int i=0;int j=0;String car = null;
            while (st.hasMoreTokens()){
                String e = st.nextToken();
                if(e.equals("i"))i= Integer.parseInt(st.nextToken());
                if(e.equals("j"))j= Integer.parseInt(st.nextToken());
                if(e.equals("v"))car= st.nextToken().toUpperCase();
            }
            assert car != null;
            puzzle.add(i,j,car.charAt(0));
        }
        brPuzzle.close();
        occorrenze= new LinkedList<>(trovaParole(puzzle.getPuzzle(),vocabolario));
    }

    @Override
    public String toString() {
        System.out.println("Vocabolario caricato:" + vocabolario);
        System.out.println("Puzzle caricato:");
        System.out.println(puzzle);
        System.out.println("Matching tra vocabolario e puzzle:");
        occorrenze.sort(Comparator.comparing(( OccorrenzaParola o ) -> o.getParola().length()).
                thenComparing(OccorrenzaParola::getX).
                thenComparing(OccorrenzaParola::getY).
                thenComparing((OccorrenzaParola::getDirezione)));
        StringBuilder sb = new StringBuilder();
        occorrenze.forEach((occorrenzaParola -> sb.append(occorrenzaParola.getParola()).append(" ").append("<").
                append(occorrenzaParola.getX()).append(",").append(occorrenzaParola.getY()).append(",")
                .append(occorrenzaParola.getDirezione()).append(">").append("\n")));
        return sb.toString();
    }

    private void caricaSingoloMatch()
    {
        for(OccorrenzaParola op:occorrenze)
            if(!m.getParoleAlpiuUnMatch().contains(op.getParola()))
                m.getParoleAlpiuUnMatch().add(op.getParola());

    }
    public String caricoSingolomatch()
    {
        caricaSingoloMatch();
        m.getParoleAlpiuUnMatch();
        StringBuilder sb = new StringBuilder();
        m.getParoleAlpiuUnMatch().forEach(parola -> sb.append(parola).append("\n"));
        return sb.toString();
    }
    private List<OccorrenzaParola> trovaParole( char [][] puzzle, List<Parola> parole)
    {
        List<OccorrenzaParola> occorrenze = new ArrayList<>();
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                for(Parola parola:parole){
                    //verifichiamo ogni direzione
                    if(Puzzle.checkNord(puzzle,i,j,parola)){
                        occorrenze.add(new OccorrenzaParola(i,j,parola,Direzione.Nord));
                        
                    } else if (Puzzle.checkNordEst(puzzle,i,j,parola)) {
                        occorrenze.add(new OccorrenzaParola(i,j,parola,Direzione.NordEst));
                        
                    } else if (Puzzle.checkNorOvest(puzzle,i,j,parola)) {
                        occorrenze.add(new OccorrenzaParola(i,j,parola,Direzione.NordOvest));

                    } else if (Puzzle.checkEst(puzzle,i,j,parola)) {
                        occorrenze.add(new OccorrenzaParola(i,j,parola,Direzione.Est));

                    }else if (Puzzle.checkSudEst(puzzle,i,j,parola)){
                        occorrenze.add(new OccorrenzaParola(i,j,parola,Direzione.SudEst));

                    }
                    else if (Puzzle.checkSudOvest(puzzle,i,j,parola)){
                        occorrenze.add(new OccorrenzaParola(i,j,parola,Direzione.SudOvest));

                    }
                    else if (Puzzle.checkSud(puzzle,i,j,parola)){
                        occorrenze.add(new OccorrenzaParola(i,j,parola,Direzione.Sud));

                    }
                    else if (Puzzle.checkOvest(puzzle,i,j,parola)){
                        occorrenze.add(new OccorrenzaParola(i,j,parola,Direzione.Ovest));

                    }
                }
            }
        }
        return occorrenze;
    }


    @Override
    public Iterator<OccorrenzaParola> iterator() {
        return occorrenze.iterator();
    }
}
