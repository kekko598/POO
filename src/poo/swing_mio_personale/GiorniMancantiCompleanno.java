package poo.swing_mio_personale;

import poo.date.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiorniMancantiCompleanno {
    private JSpinner spGiorno;
    private JSpinner spMese;
    private JButton calcolaQuantiGiorniMancanoButton;
    private JPanel PannelloPrincipale;

    public GiorniMancantiCompleanno() {
        calcolaQuantiGiorniMancanoButton.addActionListener(e -> {
            Data annoCorrente = new Data();
            Data d = new Data((Integer) spGiorno.getValue(), (Integer) spMese.getValue(), annoCorrente.get(Data.Cosa.ANNO));
            JOptionPane.showMessageDialog(null, "Mancano " + d.distanza(new Data()) + " giorni, rispetto alla data di oggi che è il " + new Data());
        });
    }

    public static void creaFinestra() {
        JFrame f = new JFrame();
        f.setContentPane(new GiorniMancantiCompleanno().PannelloPrincipale);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public static void main( String[] args ) {
        creaFinestra();
    }
}
