package com.mramirez;

import util.ObjectsHolder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiFunction;

public class ZippingIterator implements Iterator<ObjectsHolder<?, ?>> {

    Queue<ObjectsHolder<?, ?>> queue = new LinkedList<>();

    public <T, R> ZippingIterator(Iterator<T> itA, Iterator<R> itB, BiFunction<T, R, ObjectsHolder<T, R>> combinner) {
        initQueue(itA, itB, combinner);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public ObjectsHolder<?, ?> next() {
        return queue.remove();
    }

    private <T, R> void initQueue(Iterator<T> itA, Iterator<R> itB, BiFunction<T, R, ObjectsHolder<T, R>> combinner) {
        while (itA.hasNext() && itB.hasNext()) {
            queue.add(combinner.apply(itA.next(), itB.next()));
        }
    }
}
