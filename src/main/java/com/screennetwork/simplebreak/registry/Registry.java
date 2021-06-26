package com.screennetwork.simplebreak.registry;

import java.util.*;
import java.util.function.Predicate;

public abstract class Registry<V> {

    private List<V> elements;

    public Registry() {
        this.elements = new LinkedList<>();
    }

    public List<V> getAll() {
        return this.elements;
    }

    public boolean contains(V element) {
        return this.elements.contains(element);
    }

    public void add(V element) {
        this.elements.add(element);
    }

    @SafeVarargs
    public final void add(V... elementArg) {
        this.elements.addAll(Arrays.asList(elementArg));
    }

    public void remove(V element) {
        this.elements.remove(element);
    }

    public void clear() {
        this.elements.clear();
    }

    @SafeVarargs
    public final boolean remove(V... elementArg) {
        return this.elements.removeAll(Arrays.asList(elementArg));
    }

    public V getCached(Predicate<V> predicate) {
        if (this.elements == null) this.elements = new LinkedList<>();

        for (V element : this.elements) {
            if (predicate.test(element)) return element;
        }
        return null;
    }

    public Optional<V> findCached(Predicate<V> predicate) {
        return Optional.ofNullable(getCached(predicate));
    }

    public Optional<V> findAndRemove(Predicate<V> predicate) {
        final Optional<V> optional = findCached(predicate);
        optional.ifPresent(this::remove);
        return optional;
    }

    public Iterator<V> iterator() {
        return this.elements.iterator();
    }

    public int size() {
        return this.elements.size();
    }

    public void removeIf(Predicate<V> predicate) {
        for (V element : elements) {
            if (predicate.test(element)) remove(element);
        }
    }



}
