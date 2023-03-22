package poo.esercizio28_02_2017;

import java.io.*;
import java.util.*;

public class PhoneBookLinked extends PhoneBookAbstract {
    List<Persona> rubrica = new LinkedList<>();

    public PhoneBookLinked( String nomeFile ) throws IOException {

        String linea = "";
        File f = new File(nomeFile);
        if (!(f.exists())) throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String nickname = null;
        String numero = null;
        while (true) {
            linea = br.readLine();
            if (linea == null) break;
            String regex = "(NICKNAME=[a-z]+\\s+PHONE=\\d+)*|" +
                    "(PHONE=\\d+\\s+NICKNAME=[a-z]+)*";
            if (!linea.matches(regex)) throw new IllegalArgumentException();
            StringTokenizer st = new StringTokenizer(linea, " =");
            while (st.hasMoreTokens()) {
                String e = st.nextToken();
                if (e.equals("NICKNAME"))
                    nickname = st.nextToken().toUpperCase();
                if (e.equals("PHONE")) {
                    numero = st.nextToken();
                    if (numero.length() > 10)
                        throw new IllegalArgumentException("C'è il numero " + numero + " che è più lungo di 10 cifre");

                }

            }
            rubrica.add(new Persona(nickname, numero));
        }
        rubrica.sort(Comparator.comparing(Persona::getNickName));
        br.close();
    }

    public static void main( String[] args ) throws IOException {

        String s;
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci nome file -> ");
        s = sc.nextLine();
        PhoneBook pb = new PhoneBookLinked(s);
        System.out.println("File caricato con successo");
        while (true) {
            System.out.print("Se vuoi visualizzare lista digita 1\n" +
                    "per aggiungere digita 2, per rimuovere dalla rubrica solo con il nickname oppure vuoi rimuovere una serie di contatti che corrispondono a un criterio nella rubrica digita 3\n" +
                    "per cercare digita 4\n" +
                    "per rimuovere una persona specifica digita 5\n" +
                    "se vuoi uscire digita .-> ");
            s = sc.nextLine();
            if (s.contains(".")) break;
            System.out.println();
            if (s.equals("1")) {
                System.out.println("Lista telefonica:");
                System.out.println(pb);
            }
            if (s.equals("2")) {
                String nick, num;
                System.out.print("Nickname-> ");
                nick = sc.nextLine();
                System.out.print("Numero -> ");
                num = sc.nextLine();
                pb.add(new Persona(nick, num));
                System.out.println("Lista telefonica:");
                System.out.println(pb);
            }

            if (s.equals("3")) {
                System.out.print("Nickname-> ");
                pb.remove(sc.nextLine());
                System.out.println("Lista telefonica:");
                System.out.println(pb);
            }

            if (s.equals("4")) {
                String x;
                System.out.print("Nickname-> ");
                x = sc.nextLine();
                System.out.println("Matching trovato in lista telefonica:");
                System.out.println(pb.locate(x));
            }
            if (s.equals("5")) {
                String nick, num;
                System.out.print("Nickname-> ");
                nick = sc.nextLine();
                System.out.print("Numero -> ");
                num = sc.nextLine();
                pb.remove(new Persona(nick, num));
                System.out.println("Lista telefonica:");
                System.out.println(pb);
            }
            PhoneBook pb2 = new PhoneBookLinked("/Users/francesco/Desktop/rubrica2.txt");
            System.out.println(pb.equals(pb2));
        }
    }

    @Override
    public void add( Persona p ) {
        if (!p.getNickName().matches("[a-z]+") || !p.getPhone().matches("\\d+"))
            throw new IllegalArgumentException();
        rubrica.add(new Persona(p.getNickName().toUpperCase(), p.getPhone()));
        rubrica.sort(Comparator.comparing(Persona::getNickName));

    }

    @Override
    public List<Persona> remove( String s ) {
        List<Persona> tmp = new LinkedList<>(rubrica);
        for (Persona p : rubrica)
            if (p.getNickName().equals(s.toUpperCase()) || p.getNickName().contains(s.toUpperCase()))
                tmp.remove(p);
        rubrica = new LinkedList<>(tmp);
        rubrica.sort(Comparator.comparing(Persona::getNickName));
        return rubrica;
    }

    @Override
    public Persona remove( Persona p ) {
        if (!p.getNickName().matches("[a-z]+") || !p.getPhone().matches("\\d+"))
            throw new IllegalArgumentException();
        if (!rubrica.contains(new Persona(p.getNickName().toUpperCase(), p.getPhone())))
            return null;
        rubrica.remove(new Persona(p.getNickName().toUpperCase(), p.getPhone()));
        rubrica.sort(Comparator.comparing(Persona::getNickName));
        return new Persona(p.getNickName().toUpperCase(), p.getPhone());
    }

    @Override
    public List<Persona> locate( String s ) {
        List<Persona> tmp = new LinkedList<>();
        for (Persona p : rubrica)
            if (p.getNickName().equals(s.toUpperCase()) || p.getNickName().contains(s.toUpperCase()))
                tmp.add(p);
        return tmp;
    }

    @Override
    public int size() {
        return rubrica.size();
    }

    @Override
    public void clear() {
        rubrica.clear();
    }

    @Override
    public Iterator iterator() {
        return rubrica.iterator();
    }

}
