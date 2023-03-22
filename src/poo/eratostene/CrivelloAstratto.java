package poo.eratostene;

import java.util.Iterator;

public abstract class CrivelloAstratto implements Crivello {
    @Override
    public String toString() {
        int c = 0;
        StringBuilder sb = new StringBuilder();
        for (int x : this) {
            sb.append(String.format("%8d", x)); //riserva 8 spazi
            c++;
            if (c % 10 == 0) sb.append("\n"); //ogni 10 numeri stampati vai da capo
        }
        return sb.toString();
    }
}
