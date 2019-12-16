package xyz.n490808114.shopWeb.adapter;

import java.util.Enumeration;
import java.util.Iterator;


public class IteratorEnumeration<T> implements Enumeration<T> {
    Iterator<T> iterator;
    public IteratorEnumeration(Iterator<T> iterator){
        this.iterator = iterator;
    }
    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public T nextElement() {
        return iterator.next();
    }
    
}