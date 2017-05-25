package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;

/**
 * Created by engelsun on 5/25/2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    private final static Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max(16, (int)(collection.size() / .75f + 1)));
        this.addAll(collection);
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
