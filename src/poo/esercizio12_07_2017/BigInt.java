package poo.esercizio12_07_2017;

import java.util.ArrayList;
import java.util.List;

public class BigInt implements Comparable {
    private List<Integer> numero = new ArrayList<>();
    private boolean positivo = true;

    public BigInt( String s ) {
        if (!s.matches("\\-?\\d+"))
            throw new RuntimeException();
        String x = s.trim();
        if (x.charAt(0) == '-') {
            positivo = false;
            x = x.substring(1);
        }
        for (int i = x.length() - 1; i >= 0; i--)
            numero.add(x.charAt(i) - '0');

    }

    public BigInt( BigInt b ) {
        numero = new ArrayList<>(b.numero);
    }

    public static BigInt ZERO() {
        return new BigInt("0");
    }

    public static BigInt UNO() {
        return new BigInt("1");
    }

    public static BigInt DUE() {
        return new BigInt("2");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = numero.size() - 1; i >= 0; i--)
            sb.append(numero.get(i));
        return sb.toString();
    }

    public BigInt sum( BigInt b ) {
        BigInt a = this;
        BigInt result = new BigInt("0");
        result.numero.clear();
        if (a.positivo == b.positivo) {
            result.positivo = a.positivo;
            int carry = 0;
            for (int i = 0; i < Math.max(a.numero.size(), b.numero.size()) || carry > 0; i++) {
                if (i < a.numero.size())
                    carry += a.numero.get(i);
                if (i < b.numero.size())
                    carry += b.numero.get(i);
                result.numero.add(carry % 10);
                carry /= 10;
            }
        } else {
            if (a.positivo)
                result = a.sub(new BigInt(b.toString()));
            else
                result = b.sub(new BigInt(a.toString()));
        }

        return result;
    }

    public BigInt sub( BigInt b ) {

        BigInt a = this;
        BigInt result = new BigInt("0");
        result.numero.clear();
        if (a.positivo == b.positivo) {
            if (a.compareTo(b) >= 0) {
                int borrow = 0;
                for (int i = 0; i < a.numero.size(); i++) {
                    borrow = a.numero.get(i) - borrow;
                    if (i < b.numero.size())
                        borrow -= b.numero.get(i);
                    result.numero.add((borrow + 10) % 10);
                    if (borrow < 0)
                        borrow = 1;
                    else
                        borrow = 0;
                }
            } else {
                result = b.sub(a);
                result.positivo = !result.positivo;
            }
        } else {
            result.positivo = a.positivo;
            result = a.sum(new BigInt(b.toString()));
        }

        return result;
    }

    public BigInt mul( BigInt b ) {
        BigInt result = new BigInt("0");
        result.numero.clear();
        for (int i = 0; i < Integer.parseInt(b.toString()); i++)
            result = this.sum(result);
        return result;
    }

    public BigInt div( BigInt divisore ) {
        BigInt dividendo = new BigInt(this);
        if (dividendo.compareTo(divisore) < 0 || divisore.equals(new BigInt("0")))
            throw new IllegalArgumentException();
        int quoziente = 0;
        while (dividendo.compareTo(divisore) >= 0) {
            dividendo = dividendo.sub(divisore);
            quoziente++;
        }
        return new BigInt(String.valueOf(quoziente));
    }

    @Override
    public int compareTo( Object o ) {
        BigInt a = this;
        BigInt b = (BigInt) o;
        int x = Integer.parseInt(a.toString());
        int y = Integer.parseInt(b.toString());
        if (x > y) return 1;
        if (x < y) return -1;
        return 0;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof BigInt)) return false;
        return toString().equals(o.toString());
    }

}
