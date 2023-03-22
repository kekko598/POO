package poo.util;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public final class Array {
    private Array() {
    }

    public static <T extends Comparable<? super T>> int ricercaLineare( Vector<T> a, T x ) {
        //array non ordinato per forza
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).equals(x)) return i;
        }
        return -1;
    }

    ;

    public static <T extends Comparable<? super T>> int ricercaBinaria( Vector<T> a, T x ) {
        //PRE array deve essere ordinato s

        int inf = 0, sup = a.size() - 1;
        while (inf <= sup) {
            int med = (inf + sup) / 2;
            if (a.get(med).equals(x)) return med;
            if (a.get(med).compareTo(x) > 0) sup = med - 1;
            else inf = med + 1;
        }
        return -1;
    }

    public static <T extends Comparable<? super T>> boolean ordinato( Vector<T> v ) {
        Vector<T> tmp = new ArrayVector<>(v);
        bubbleSortV3(tmp);
        int i = 0;
        for (T x : tmp) {
            if (x.equals(v.get(i)))
                return true;
            i++;
        }
        return false;
    }

    public static <T extends Comparable<? super T>> void selectionSort( Vector<T> v ) {
        for (int j = v.size() - 1; j > 0; j--) {
            int indMax = 0; // assumo che in pos zero ci sia il max valore
            for (int i = 1; i <= j; i++) {
                if (v.get(i).compareTo(v.get(indMax)) > 0)
                    indMax = i;
                //scambia v[j] con v[indmax]
                T park = v.get(j);
                v.set(j, v.get(indMax));
                v.set(indMax, park);

            }
        }

    }

    /*
        public static void bubbleSortV1(int[] v) {
            boolean scambio = true;
            while (scambio) {
                scambio = false;
                for (int i = 0; i < v.length - 1; i++) {
                    if (v[i] > v[i + 1]) {
                        int tmp = v[i];
                        v[i] = v[i + 1];
                        v[i + 1] = tmp;
                        scambio = true;
                    }
                }
            }
        }

        public static void bubbleSortV2(int[] v) {
            boolean scambio = true;
            int limite = v.length - 1;
            while (scambio) {
                scambio = false;
                limite--;
                // se il max è in ultima pos, quello non si tocca mai più "CORRETTEZZA FUNZIONALE"
                for (int i = 0; i < limite; i++) {
                    if (v[i] > v[i + 1]) {
                        int tmp = v[i];
                        v[i] = v[i + 1];
                        v[i + 1] = tmp;
                        scambio = true;
                    }
                }
            }
        }
    */
    public static <T extends Comparable<? super T>> void bubbleSortV3( Vector<T> v ) {
        boolean scambio = true;
        int limite = v.size() - 1;
        int indiceUltimoScambio = -1;
        while (scambio) {
            scambio = false; //ottimismo Watson!

            // se il max è in ultima pos, quello non si tocca mai più "CORRETTEZZA FUNZIONALE"

            for (int i = 0; i < limite; i++) {
                if (v.get(i).compareTo(v.get(i + 1)) > 0) {
                    T tmp = v.get(i);
                    v.set(i, v.get(i + 1));
                    v.set(i + 1, tmp);
                    indiceUltimoScambio = i;
                    scambio = true;
                }
            }
            limite = indiceUltimoScambio;
        }
    }

    public static <T extends Comparable<? super T>> void insertionSort( Vector<T> v ) {
        for (int i = 0; i < v.size(); ++i) {
            T x = v.get(i); //togli v[i]
            int j = i;
            while (j > 0 && v.get(j - 1).compareTo(x) > 0) {
                //sposta in avanti v[j-1]
                v.set(j, v.get(j - 1));
                j--;
            }
            //poni x in v[j]
            v.set(j, x);
        }
    }

    public static int prodottoScalare( Vector<Integer> a, Vector<Integer> b ) {
        if (a.size() != b.size()) throw new IllegalArgumentException("vettori con dimensione diversa");
        int p, s = 0;
        for (int i = 0; i < a.size() && i < b.size(); i++) {
            p = a.get(i) * b.get(i);
            s += p;
        }
        return s;
    }

    public static <T extends Comparable<? super T>> T max( Vector<T> v ) {
        T max = v.get(0);
        int i = 0;
        for (T val : v) {
            if (val.compareTo(max) > 0)
                max = v.get(i);
            i++;
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T min( Vector<T> v ) {
        T min = v.get(0);
        int i = 0;
        for (T val : v) {
            if (val.compareTo(min) < 0)
                min = v.get(i);
            i++;
        }
        return min;
    }

    private static <T extends Comparable<? super T>> void mergeSort( T[] v ) {
        if (v.length == 0) throw new IllegalArgumentException("Dimensioni uguale a 0");
        mergeSortITE(v);
    }

    private static <T extends Comparable<? super T>> void mergeSort( T[] v, int inf, int sup ) {

        if (inf < sup) {
            int med = (inf + sup) / 2;
            mergeSort(v, inf, med);
            mergeSort(v, med + 1, sup);
            merge(v, inf, med, sup);

        }
    }

    private static <T extends Comparable<? super T>> void mergeSortITE( T[] v ) { //Da controllare ancora
        class AreaDati {
            T[] v;
            int inf;
            int med;
            int sup;
            Operazione op;

            public AreaDati( T[] v, int inf, int med, int sup, Operazione op ) {
                this.v = v;
                this.inf = inf;
                this.med = med;
                this.sup = sup;
                this.op = op;
            }
        }
        ;
        Stack<AreaDati> stack = new Stack<>();
        stack.push(new AreaDati(v, 0, 0, v.length - 1, Operazione.MERGESORT));
        while (!stack.isEmpty()) {
            AreaDati ad = stack.pop();
            if (ad.op == Operazione.MERGE)
                merge(ad.v, ad.inf, ad.med, ad.sup);
            else {
                if (ad.inf < ad.sup) {
                    ad.med = (ad.inf + ad.sup) / 2;
                    stack.push(new AreaDati(v, ad.inf, ad.med, ad.sup, Operazione.MERGE));
                    stack.push(new AreaDati(v, ad.inf, 0, ad.med, Operazione.MERGESORT));
                    stack.push(new AreaDati(v, ad.med + 1, 0, ad.sup, Operazione.MERGESORT));

                }
            }
        }
    }

    private static <T extends Comparable<? super T>> void merge( T[] v, int inf, int med, int sup ) {
        T[] aux = (T[]) new Comparable[((sup - inf) + 1)];
        int i = inf, j = med + 1, k = 0;
        while (i <= med && j <= sup) {
            if (v[i].compareTo(v[j]) < 0) {
                aux[k] = v[i];
                i++;
            } else {
                aux[k] = v[j];
                j++;
            }
            k++;
        }
        //gestione residuo su primo o secondo segmento, quindi solo uno di questi cicli verrà eseguito
        while (i <= med) {
            aux[k] = v[i];
            i++;
            k++;
        }
        while (j <= sup) {
            aux[k] = v[j];
            j++;
            k++;
        }
        //scrivi aux su v
        for (int h = 0; h < aux.length; h++) {
            v[h + inf] = aux[h];
        }
    }

    public static <T extends Comparable<? super T>> void heapSort( T[] v ) {
        Heap<T> h = new Heap<>(v.length);
        //riempimento heap
        for (T t : v) h.add(t);
        //svuotamento heap
        for (int i = 0; i < v.length; i++)
            v[i] = h.remove();
    }

    private enum Operazione {MERGESORT, MERGE}

    public static void main( String[] args ) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int [] v = {10,2,3,4,5,10,2,6,90,2};
        for (int i : v) heap.add(i);
        for (int i = 0; i < v.length; i++) v[i]=heap.remove();
        System.out.println(Arrays.toString(v));
    }
}