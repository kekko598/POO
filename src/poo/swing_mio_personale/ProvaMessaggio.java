package poo.swing_mio_personale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProvaMessaggio {
    JPanel PannelloPrincipale;
    private JButton btnClick;
    private JTextField txtNome;


    public ProvaMessaggio() {
        //click bottone invocato dal costruttore
        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                JOptionPane.showMessageDialog(null, "Ciao " + txtNome.getText() + " !");
            }
        });
    }

    public static void creaFinestra() {
        JFrame f = new JFrame();
        f.setContentPane(new ProvaMessaggio().PannelloPrincipale);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public static void main( String[] args ) {

        creaFinestra();

    }
}
