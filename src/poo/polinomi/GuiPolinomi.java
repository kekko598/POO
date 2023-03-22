package poo.polinomi;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiPolinomi {

    private JFrame frmCalcoloPolinomi;
    private JTextField txtInput;
    //gestione dinamica per il Jlist per aggiungere ed eliminare polinomi dal JList
    private DefaultListModel<String> aggiungiAListaPolinomi;
    private JList<String> list;


    /**
     * Costruttore
     */
    public GuiPolinomi() {
        inizializza();
    }

    /**
     * main.
     */
    public static void main( String[] args ) {

        GuiPolinomi window = new GuiPolinomi();
        window.frmCalcoloPolinomi.setVisible(true);


    }

    /**
     * Inizializazzione e implementazione del frame
     */
    private void inizializza() {


        frmCalcoloPolinomi = new JFrame();
        frmCalcoloPolinomi.setTitle("Calcolo polinomi");
        frmCalcoloPolinomi.setBounds(100, 100, 836, 800);
        frmCalcoloPolinomi.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel lblInserisciPolinomio = new JLabel("inserisci polinomio:");
        txtInput = new JTextField();
        txtInput.setColumns(10);


        //bottone per trovare derivata prima dal JTextField o dal JList
        JButton trovaDerivataButton = new JButton("trova derivata prima (scrivi sulla textbox oppure selezionalo dalla lista)");
        trovaDerivataButton.addActionListener(e -> {
            try {

                if (list.getSelectedValuesList().size() > 1) { //gestione selezione polinomio, al più uno
                    JOptionPane.showMessageDialog(null, "Seleziona una solo polinomio!");

                } else { //se invece è stato selezionato un solo polinomio controlla se l'input proviene dal Jlist oppure dal JTextField
                    Polinomio p = new PolinomioLL();
                    if (list.getSelectedIndex() != -1) {
                        Polinomio.parse(list.getSelectedValue(), p);
                    } else {
                        Polinomio.parse(txtInput.getText(), p);
                    }
                    //calcolo derivata
                    Polinomio derivata = p.derivata();
                    if (derivata.toString().equals("")) //gestione derivata uguale a zero
                        JOptionPane.showMessageDialog(null, "Derivata: 0");
                    else { //derivata diversa da zero
                        String s = String.valueOf(p.derivata()).replace(" ", "");
                        if (s.charAt(0) == '+') {

                            aggiungiAListaPolinomi.addElement(s.substring(1));
                            JOptionPane.showMessageDialog(null, "Derivata: " + s);

                        } else {

                            aggiungiAListaPolinomi.addElement(s);
                            JOptionPane.showMessageDialog(null, "Derivata: " + s);


                        }
                    }

                }


            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Inserisci bene i dati");

            }

        });
        //bottone per trovare derivata n-esima dal JTextField o dal JList
        JButton trovaLaDerivataNButton = new JButton("trova la derivata n-esima (scrivi sulla textbox oppure selezionalo dalla lista)");
        trovaLaDerivataNButton.addActionListener(e -> {
            try {
                if (list.getSelectedValuesList().size() > 1) {//gestione selezione polinomio, al più uno
                    JOptionPane.showMessageDialog(null, "Seleziona una solo polinomio!");

                } else {//se invece è stato selezionato un solo polinomio controlla se l'input proviene dal Jlist oppure dal JTextField
                    Polinomio p = new PolinomioLL();
                    ArrayList<Polinomio> der = new ArrayList<>();
                    if (list.getSelectedIndex() != -1) {
                        Polinomio.parse(list.getSelectedValue(), p);
                    } else {
                        Polinomio.parse(txtInput.getText(), p);
                    }
                    //calcolo derivata, selezionando il massimo grado di derivazione
                    int nDerivate = Integer.parseInt(JOptionPane.showInputDialog(null, "Quante volte vuoi derivare?"));
                    if (nDerivate < 1)
                        JOptionPane.showMessageDialog(null, "Inserisci un valore maggiore o uguale a 1");
                    else {
                        Polinomio derivata = p.derivata();
                        if (derivata.toString().equals(""))//gestione derivata uguale a zero
                            JOptionPane.showMessageDialog(null, "Derivata: 0");
                        else {
                            //gestione derivata diversa da zero
                            List<Integer> gradi = new ArrayList<>();
                            for (Monomio m : p)
                                gradi.add(m.getGRADO());
                            int max = Collections.max(gradi);
                            if (max >= nDerivate) {
                                for (int i = 0; i < nDerivate; i++) {
                                    p = p.derivata();
                                    der.add(p);
                                }

                                String s = String.valueOf(p).replace(" ", "");
                                if (s.charAt(0) == '+') {
                                    aggiungiAListaPolinomi.addElement(s.substring(1));
                                } else {
                                    aggiungiAListaPolinomi.addElement(s);

                                }


                                JOptionPane.showMessageDialog(null, "Derivata trovata: " + p + "\n" + "Altre derivate: " + der);
                            } else {
                                JOptionPane.showMessageDialog(null, "Non puoi trovare la derivata di " + p + " con valore " + nDerivate + " se il suo grado massimo è " + max + " !");
                            }
                        }
                    }
                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Inserisci bene i dati");
            }
        });

        //bottone di copia per copiare il polinomio nella clipboard del sistema operativo
        JButton btnCopia = new JButton("copia nella clipboard per esportarlo fuori dal programma");
        btnCopia.addActionListener(e -> {
            try {
                if (aggiungiAListaPolinomi.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Lista vuota");
                } else {
                    if (list.getSelectedValuesList().size() == 1) {
                        String s = list.getSelectedValue();
                        copiaAppunti(s);//implementazione vera e propria della copia
                        JOptionPane.showMessageDialog(null, "Copiato " + s);

                    } else {
                        JOptionPane.showMessageDialog(null, "seleziona un solo polinomio!");
                    }

                }

            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Errore di copia");
            }

        });

        //implementazione scrollpane in verticale del Jlist per permettere lo scorrimento della lista
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //bottone per sommare e/o sottrarre fra di loro i polinomi
        JButton btnSommaEoSottrai = new JButton("somma e/o sottrai polinomi (selezionane più di uno)");
        btnSommaEoSottrai.addActionListener(e -> {
            try {
                List<String> p = list.getSelectedValuesList();
                if (p.size() > 1) { //se si selezionano più di 2 polinomi, sommali tutti
                    Polinomio somma = new PolinomioLL();
                    for (String sp : p) {
                        Polinomio tmp = new PolinomioLL();
                        Polinomio.parse(sp, tmp);
                        somma = somma.add(tmp);
                    }

                    if (String.valueOf(somma).equals("")) { //se la somma da zero
                        JOptionPane.showMessageDialog(null, "0");
                    } else {//se la somma è diversa da zero
                        JOptionPane.showMessageDialog(null, somma);
                        String s = String.valueOf(somma).replace(" ", "");
                        if (s.charAt(0) == '+') {
                            aggiungiAListaPolinomi.addElement(s.substring(1));
                        } else {
                            aggiungiAListaPolinomi.addElement(s);

                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "seleziona più di un polinomio");
                }


            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Errore di somma");

            }
        });

        //bottone per prodotto fra polinomi
        JButton btnProdottoPolinomiselezionane = new JButton("prodotto polinomi (selezionane più di uno)");
        btnProdottoPolinomiselezionane.addActionListener(e -> {
            try {

                //gestione con gli if simile alla somma di polinomi
                List<String> p = list.getSelectedValuesList();
                if (p.size() > 1) {

                    Polinomio prod;
                    Polinomio primoP = new PolinomioLL();
                    primoP.add(new Monomio(1, 0));
                    prod = primoP;
                    for (String sp : p) {
                        Polinomio tmp = new PolinomioLL();
                        Polinomio.parse(sp, tmp);
                        prod = prod.mul(tmp);
                    }

                    JOptionPane.showMessageDialog(null, prod);
                    String s = String.valueOf(prod).replace(" ", "");
                    if (s.charAt(0) == '+') {
                        aggiungiAListaPolinomi.addElement(s.substring(1));
                    } else {
                        aggiungiAListaPolinomi.addElement(s);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "seleziona più di un polinomio");

                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Errore di prodotto");

            }
        });


        //bottone per aggiungere i polinomi nel Jlist
        JButton btnAggiungiPolinomio = new JButton("inserisci polinomio in lista (usa la textbox di sopra)");
        btnAggiungiPolinomio.addActionListener(e -> {
            try {
                String s = txtInput.getText();
                Polinomio p = new PolinomioLL();
                Polinomio.parse(s, p);
                String polinomio = p.toString();
                aggiungiAListaPolinomi.addElement(polinomio);
                txtInput.setText(null);
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Errore di inserimento");
            }


        });

        //bottone per eliminare i polinomio dal Jlist
        JButton btnEliminaPolinomioIn = new JButton("elimina polinomio in lista");
        btnEliminaPolinomioIn.addActionListener(e -> {

            try {
                aggiungiAListaPolinomi.remove(list.getSelectedIndex());//elimina un polinomio selezionandone solo uno
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Errore di eliminazione");
            }

        });

        //bottone per svuotare la lista ad un colpo solo
        JButton btnClean = new JButton("Svuota lista di polinomi");
        btnClean.addActionListener(e -> {

            try {
                aggiungiAListaPolinomi.clear();

            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Errore di svuotamento");
            }

        });


        /*
        Con il groupLayout si sono definiti tutti i margini di ogni componente
        del Jframe in modo tale che ci sia resize dinamico di tutti
        i componenti al loro interno al ridimensionamento della finestra in qualunque posizione
         */
        GroupLayout groupLayout = new GroupLayout(frmCalcoloPolinomi.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(5)
                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(7)
                                                .addComponent(lblInserisciPolinomio, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(txtInput, GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnAggiungiPolinomio, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnEliminaPolinomioIn, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnClean, GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnCopia, GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(btnSommaEoSottrai, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                                                        .addComponent(btnProdottoPolinomiselezionane, GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                                                        .addComponent(trovaLaDerivataNButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                                                        .addComponent(trovaDerivataButton, GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.RELATED)))
                                .addGap(5))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(25)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtInput, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                                        .addComponent(lblInserisciPolinomio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(trovaDerivataButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(trovaLaDerivataNButton, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnSommaEoSottrai, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addGap(12)
                                .addComponent(btnProdottoPolinomiselezionane, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addGap(12)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAggiungiPolinomio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEliminaPolinomioIn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnClean, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addGap(11)
                                .addComponent(btnCopia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(55))
        );



        //implementazione del DefaultListModel con il Jlist e il scrollPane
        aggiungiAListaPolinomi = new DefaultListModel<>();
        list = new JList<>(aggiungiAListaPolinomi);
        scrollPane.setViewportView(list);
        frmCalcoloPolinomi.getContentPane().setLayout(groupLayout);
        JMenuBar menuBar = new JMenuBar(); //menu bar per la gestione degli altri sotto menu e bottoni come gestione salvataggio file, operazione alternative e about
        frmCalcoloPolinomi.setJMenuBar(menuBar);

        //creo anche il sottomenu file e aggiungo al Jmenu tutti i bottoni implementati come salva e apri
        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        //per salvare e aprire il file sono stati creati i due metodi appositi salva e crea, richiamati dal actionListener
        //dei relativi bottoni

        JButton btnSalva = new JButton("Salva");
        btnSalva.addActionListener(e -> salva());

        JButton btnApri = new JButton("Apri");
        btnApri.addActionListener(e -> apri());
        mnFile.add(btnApri);
        mnFile.add(btnSalva);

        //sotto menu che contiene operazioni alternative di somma e prodotto, nel senso che la gestione
        //di somma e prodotto viene fatta tramite il jtextfield e volendo anche dal Jlist
        //inoltre, contiene anche il calcola valore della x di un polinomio (solo dal Jlist)
        JMenu mnOperazioni = new JMenu("Operazioni alternative");
        menuBar.add(mnOperazioni);

        JButton sommaPolinomioButton = new JButton("somma e/o sottrai polinomio (si apre una finestra a parte per aggiungere altri polinomi in modo da sommare il polinomio della textbox con gli altri inseriti e il risultato lo metterà\n in lista)");
        mnOperazioni.add(sommaPolinomioButton);

        JButton moltiplicaPolinomioButton = new JButton("moltiplica polinomio  (si apre una finestra a parte per aggiungere altri polinomi in modo da moltiplicare il polinomio della textbox con gli altri inseriti e il risultato lo metterà\n in lista)");
        mnOperazioni.add(moltiplicaPolinomioButton);

        //bottone che calcola il valore della x di un polinomio
        JButton btnCalcolaX = new JButton("calcola il valore della x sul polinomio (seleziona un elemento nella lista)");
        btnCalcolaX.addActionListener(e -> {
            try {
                //gestione di selezioni polinomi
                if (list.getSelectedValuesList().size() > 1) {
                    JOptionPane.showMessageDialog(null, "Seleziona un solo polinomio!");
                } else {
                    //calcolo vero e proprio della x
                    Polinomio P = new PolinomioLL();
                    String s = list.getSelectedValue();
                    Polinomio.parse(s, P);
                    double val = Double.parseDouble(JOptionPane.showInputDialog("inserisci il valore x da calcolare"));
                    JOptionPane.showMessageDialog(null, "Il polinomio " + P + " vale in x:" + P.valore(val));

                }


            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Errore nel calcolo della x");
            }

        });
        mnOperazioni.add(btnCalcolaX);
        //bottone che calcola il prodotto tra 2 o più polinomi da JtextField oppure da Jlist
        //si seleziona il primo polinomio da Jlist oppure da JtextField in modo tale da fare il prodotto
        //con tutti gli altri polinomi aggiunti da showInputDialog
        moltiplicaPolinomioButton.addActionListener(e -> {
            try {
                int polinomiDaOperare = Integer.parseInt(JOptionPane.showInputDialog("Quanti polinomi vuoi moltiplicare?"));
                if(polinomiDaOperare<1)
                    JOptionPane.showMessageDialog(null,"Aggiungi un valore superiore a 1");
                else{

                    Polinomio primoPol = new PolinomioLL();
                    if (list.getSelectedIndex() != -1) { //inserimento da Jlist
                        Polinomio.parse(list.getSelectedValue(), primoPol);
                    } else { //inserimento da JtextField
                        Polinomio.parse(txtInput.getText(), primoPol);
                    }
                    Polinomio prod = new PolinomioLL();
                    prod = prod.add(primoPol);
                    for (int i = 0; i < polinomiDaOperare; i++) {//aggiungi un polinomio uno dopo l'altro dal showInputDialog
                        Polinomio tmp = new PolinomioLL();
                        Polinomio.parse(String.valueOf(JOptionPane.showInputDialog("aggiungi prossimo polinomio")), tmp);
                        prod = prod.mul(tmp);
                    }
                    if (String.valueOf(prod).equals("")) //se prodotto uguale a 0
                        JOptionPane.showMessageDialog(null, "0");
                    else {//se prodotto è diverso da zero
                        JOptionPane.showMessageDialog(null, prod);
                        String s = String.valueOf(prod).replace(" ", "");
                        if (s.charAt(0) == '+') {
                            aggiungiAListaPolinomi.addElement(s.substring(1));
                        } else {
                            aggiungiAListaPolinomi.addElement(s);
                        }
                    }
                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Inserisci bene i dati");

            }
        });
        //bottone che calcola la somma tra 2 o più polinomi da JtextField oppure da Jlist
        //si seleziona il primo polinomio da Jlist oppure da JtextField in modo tale da fare la somma
        //con tutti gli altri polinomi aggiunti da showInputDialog
        sommaPolinomioButton.addActionListener(e -> {

            //gestione if simile al prodotto dei polinomi
                    try {

                        int polinomiDaOperare = Integer.parseInt(JOptionPane.showInputDialog("Quanti polinomi vuoi sommare?"));
                        if(polinomiDaOperare<1)
                            JOptionPane.showMessageDialog(null,"Aggiungi un valore superiore a 1");
                        else {
                            Polinomio primoPol = new PolinomioLL();
                            if (list.getSelectedIndex() != -1) {
                                Polinomio.parse(list.getSelectedValue(), primoPol);

                            } else {
                                Polinomio.parse(txtInput.getText(), primoPol);
                            }

                            Polinomio somma = new PolinomioLL();
                            somma = somma.add(primoPol);
                            for (int i = 0; i < polinomiDaOperare; i++) {
                                Polinomio tmp = new PolinomioLL();
                                Polinomio.parse(String.valueOf(JOptionPane.showInputDialog("aggiungi prossimo polinomio")), tmp);
                                somma = somma.add(tmp);
                            }
                            if (String.valueOf(somma).equals("")) {
                                JOptionPane.showMessageDialog(null, "0");
                            } else {

                                JOptionPane.showMessageDialog(null, somma);
                                String s = String.valueOf(somma).replace(" ", "");
                                if (s.charAt(0) == '+') {
                                    aggiungiAListaPolinomi.addElement(s.substring(1));
                                } else {
                                    aggiungiAListaPolinomi.addElement(s);

                                }
                            }
                        }
                    } catch (Exception a) {
                        JOptionPane.showMessageDialog(null, "Inserisci bene i dati");
                    }

                }
        );

        //aggiungo un jmenu con bottoni sulle regole del programma e il bottone exit e infine il bottone di about
        JMenu nmOtherSettings = new JMenu("Altre impostazioni");
        menuBar.add(nmOtherSettings);

        JButton btnRegole = new JButton("Regole del programma");
        btnRegole.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "seleziona con CTRL + click mouse SX più di 2 polinomi per somma e prodotti\n"
                        + "seleziona con CTRL + click mouse SX SOLO un polinomio per la derivazione\n" +
                        "per deselezionare il polinomio nella lista basta fare CTRL + click mouse SX sul polinomio selezionato in modo da abilitare la textbox"));


        nmOtherSettings.add(btnRegole);
        //gestione uscita dal programma per il salvataggio
        JButton btnEsci = new JButton("Esci");
        btnEsci.addActionListener(e -> {
            if (!aggiungiAListaPolinomi.isEmpty()) {
                String message = "La lista ha polinomi sei sicuro di non voler salvare?";
                Object[] params = {message};
                int n = JOptionPane.showConfirmDialog(null, params, "Conferma di salvataggio", JOptionPane.YES_NO_OPTION);
                if (n == 0)
                    System.exit(0);
                else {
                    salva();
                    System.exit(0);
                }

            } else {
                System.exit(0);
            }


        });


        JButton btnCreato = new JButton("About");
        btnCreato.addActionListener(e -> JOptionPane.showMessageDialog(null, "App creata da Francesco Cavallo V1.0" + "\n" + "e-mail: cavallo.francesco@live.it"));

        nmOtherSettings.add(btnCreato);
        nmOtherSettings.add(btnEsci);


    }

    private void copiaAppunti( String s ) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(s), null);
    }

    private void salva() {
        /*
        si gestisce il salvataggio del file mediante l'utilizzo del JfileSchooser con estensione .txt
         */
        try {
            if (aggiungiAListaPolinomi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Non hai scritto nessun polinomio nella lista!");
            } else {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                fileChooser.setFileFilter(filter);
                fileChooser.setDialogTitle("Specifica il file dove lo vuoi salvare");
                int selezioneUtente = fileChooser.showSaveDialog(frmCalcoloPolinomi);
                if (selezioneUtente == JFileChooser.APPROVE_OPTION) {
                    File fileDaSalvare = fileChooser.getSelectedFile();
                    String nomeFile = fileDaSalvare.getAbsolutePath() + ".txt";
                    PrintWriter w = new PrintWriter(new FileWriter(nomeFile));
                    for (int i = 0; i < aggiungiAListaPolinomi.size(); i++) {
                        if (aggiungiAListaPolinomi.get(i) != null)
                            w.write(aggiungiAListaPolinomi.get(i) + "\n");
                    }

                    JOptionPane.showMessageDialog(null, "File Salvato su " + fileDaSalvare);
                    w.close();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore salvataggio del file");

        }


    }

    private void apri() {
        /*
        si gestisce l'apertura del file mediante l'utilizzo del JfileSchooser con estensione .txt
        impostando come pagina corrente di default la user.home del sistema operativo in uso
         */
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setDialogTitle("Specifica il percorso per aprire il file");
            int risultato = fileChooser.showOpenDialog(fileChooser);
            if (risultato == JFileChooser.APPROVE_OPTION) {
                File fileDaSelezionare = fileChooser.getSelectedFile();
                TxtFilter txtfilter = new TxtFilter();
                if (txtfilter.accept(fileDaSelezionare)) {
                    String nomeFile = fileDaSelezionare.getAbsolutePath();
                    BufferedReader r = new BufferedReader(new FileReader(nomeFile));
                    String s = "";
                    try {
                        while (s != null) {
                            s = r.readLine();
                            if (s != null){
                                Polinomio.parse(s, new PolinomioLL());
                                aggiungiAListaPolinomi.addElement(s);
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "File non compatibile il formato deve essere:\n"
                                + "-2x+3" + "\n"
                                + "x^4-4  e cosi via)");
                    }
                    finally {
                        r.close();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "accetto solo file .txt");

                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File non trovato");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore nell'apertura del file");
        }
    }


    //inner class per la gestione con un metodo boolean del filtraggio dei file .txt
     private class TxtFilter implements FileFilter {

        public boolean accept( File f ) {
            return f.getName().toLowerCase().endsWith(".txt") || f.isDirectory();
        }
    }
}