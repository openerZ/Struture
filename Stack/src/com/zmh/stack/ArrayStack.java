package com.zmh.stack;

public class ArrayStack<E> implements Stack<E>{

    private DynamicArray<E> da;

    public ArrayStack(int capacity){
        da = new DynamicArray<>(capacity);
    }
    public ArrayStack(){
        this(10);
    }

    @Override
    public int getSize(){
        return da.getSize();
    }

    @Override
    public boolean isEmpty(){
        return da.isEmpty();
    }

    @Override
    public void push(E e) {
        da.addLast(e);
    }

    @Override
    public E pop() {
        return da.removeLast();
    }

    @Override
    public E peek() {
        return da.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i = 0 ; i < da.getSize() ; i ++){
            res.append(da.get(i));
            if(i != da.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> arr = new ArrayStack<>();
        arr.push(1);
        arr.push(5);
        arr.push(6);
        System.out.println(arr.toString());
        System.out.println(arr.pop());
        System.out.println(arr.toString());
        System.out.println(arr.peek());
    }

}
