package com.mramirez;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class HelperIterator<T> implements Iterator<T> {
    Queue<T> elements = new LinkedList<>();

    public HelperIterator(Collection<T> collection) {
        elements.addAll(collection);
    }

    @Override
    public boolean hasNext() {
        return !elements.isEmpty();
    }

    @Override
    public T next() {
        return elements.poll();
    }
}
