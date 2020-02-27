package com.zmh.linked;

public class LinkedListStack<E> implements Stack<E>{
    private LinkedList01<E> list;

    public LinkedListStack(){
        list = new LinkedList01<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }
}
