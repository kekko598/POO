package poo.esercizio10_11_2017;

import java.util.LinkedList;

public class ElementoRiga<T> {
    private int i;
    private LinkedList<ElementoColonna<T>> rigaGenericaSuColonnaJ; //si identifica come <i,v> con il tipo ElementoColonna, diventa <i,j,val>

    public ElementoRiga( int i ) {
        this.i = i;
        rigaGenericaSuColonnaJ = new LinkedList<>();
    }

    public int getI() {
        return i;
    }

    public void setI( int i ) {
        this.i = i;
    }

    public LinkedList<ElementoColonna<T>> getRigaGenericaSuColonnaJ() {
        return rigaGenericaSuColonnaJ;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(rigaGenericaSuColonnaJ + "\n");
        return sb.toString();
    }
}
