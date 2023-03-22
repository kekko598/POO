//*********************************************************
//
//  file FinestraChiudibile0.java
//
//  Primo esempio di finestra creabile con Java poo.swing
//  e che risponde all'evento di chiusura (si clicca
//  sul pulsante X o si digita ALT-F4 etc.).
//  L'esempio intende illustrare il ruolo di "ascoltatore"
//  (listener) che e' un oggetto deputato (delegato)
//  a ricevere  e trattare gli eventi evocati
//  sulla finestra.
//  In FinestraChiudibile0 l'ascoltatore coincide
//  con lo stesso oggetto finestra.
//  Da notare che l'implementazione dell'interfaccia
//  WindowListener impone l'obbligo di implementare
//  tutti i suoi metodi.
//*********************************************************
package poo.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FinestraChiudibile0
        extends JFrame implements WindowListener {

    public FinestraChiudibile0() {
        setTitle("Finestra Chiudibile");
        setSize(300, 200);
        setLocation(50, 200);//opzionale
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
    }

    public static void main( String[] args ) {
        Frame f = new FinestraChiudibile0();
        f.setVisible(true);
    }

    public void windowActivated( WindowEvent e ) {
    }

    public void windowClosed( WindowEvent e ) {
    }

    public void windowClosing( WindowEvent e ) {
        System.exit(0);
    }

    public void windowDeactivated( WindowEvent e ) {
    }

    public void windowIconified( WindowEvent e ) {
    }

    public void windowDeiconified( WindowEvent e ) {
    }

    public void windowOpened( WindowEvent e ) {
    }
}//FinestraChiudibile0
