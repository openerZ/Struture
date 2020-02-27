package com.zmh.map;

public interface Map<K, V> {

    int getSize();
    boolean isEmpty();
    boolean contains(K key);
    void add(K key,V value);
    V get(K key);
    V remove(K key);
    void set(K key,V newValue);

}
