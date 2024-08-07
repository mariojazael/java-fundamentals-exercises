package com.mramirez;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BufferingIterator<T> implements Iterator<List<T>> {

    private final Iterator<T> iterator;
    private int bufferSize;

    public BufferingIterator(Iterator<T> iterator, int bufferSize) {
        this.iterator = iterator;
        this.bufferSize = bufferSize;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public List<T> next() {
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            if(list.size() < bufferSize) list.add(iterator.next());
            else break;
        }
        return list;
    }
}
