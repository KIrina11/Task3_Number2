package ru.vsu.cs.kislova_i_v;

public interface SimpleQueue<T> {
    void addElement(T value);

    T removeFirstElement() throws Exception;

    T removeLastElement() throws Exception;

    T getFirstElement() throws Exception;

    T getLastElement() throws Exception;

    int count();

    boolean isEmpty();
}
