package poo.esercizio17_07_2015;

public abstract class InsiemeOrdinatoAstratto<T extends Comparable<? super T>> extends InsiemeAstratto<T> implements InsiemeOrdinato<T> {
    public InsiemeOrdinato<T> headSet( T x ) {
        InsiemeOrdinato<T> sottoInsieme = factory();
        for (T elem : this)
            if (elem.compareTo(x) <= 0)
                sottoInsieme.add(elem);
        return sottoInsieme;
    }

    public InsiemeOrdinato<T> tailSet( T x ) {
        InsiemeOrdinato<T> sottoInsieme = factory();
        for (T elem : this)
            if (elem.compareTo(x) >= 0)
                sottoInsieme.add(elem);
        return sottoInsieme;
    }

}
