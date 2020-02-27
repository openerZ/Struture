package com.zmh.queue;

public class ArrayQueue<E> implements Queue<E>{

    private Array<E> arr;

    public ArrayQueue(int capacity){
        arr = new Array<>(capacity);

    }

    public ArrayQueue(){
        this(10);
    }

    @Override
    public int getSize() {
        return arr.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arr.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        arr.addLast(e);
    }

    @Override
    public E dequeue() {
        return arr.removeFirst();
    }

    @Override
    public E getFront() {
        return arr.getFirst();
    }

    public int getCapacity(){
        return arr.getCapacity();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0 ; i < arr.getSize() ; i ++){
            res.append(arr.get(i));
            if(i != arr.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
        System.out.println(queue.getFront());
    }

}
