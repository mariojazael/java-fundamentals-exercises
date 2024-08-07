package com.mramirez;

import java.util.*;

public class FlatteningIterator<T> implements Iterator<T> {

    private final Iterator<T> mainIterator;

    @SafeVarargs
    public FlatteningIterator(Iterator<T>... iterators) {
        mainIterator = buildMainIterator(List.of(iterators));
    }

    @Override
    public boolean hasNext() {
        return mainIterator.hasNext();
    }

    @Override
    public T next() {
        return mainIterator.next();
    }

    private Iterator<T> buildMainIterator(Collection<Iterator<T>> iterators) {
        Iterator<T> newMainIterator;
        List<T> elements = new ArrayList<>();
        for (Iterator<T> it : iterators) {
            while (it.hasNext()) {
                elements.add(it.next());
            }
        }
        newMainIterator = new HelperIterator<>(elements);
        return newMainIterator;
    }

}
