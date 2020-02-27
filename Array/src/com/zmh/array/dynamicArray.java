package com.zmh.array;

public class dynamicArray<E> {

    private E[] data;
    private int size;

    public dynamicArray(int capacity){
        data=(E[])new Object[capacity];
        size=0;
    }

    public dynamicArray(){
        this(10);
    }

    public int getSize(){
        return size;
    }

    public int getLength(){
        return data.length;
    }

    public boolean isEmpty(){

        return size==0;
    }


    public void add(int index,E e){


        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        if (size == data.length){
            resize(2*data.length);
        }


        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;

    }

    //扩容
    private void resize(int length){
        //创建一个更大的新数组
        E[] newData = (E[]) new Object[length];

        //将原数组复制到新数组中
        for(int i=0; i<size; i++){
            newData[i]=data[i];
        }

        //data指向新数组
        data = newData;
    }



    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    public E getData(int index){
        if(index<0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }

        return data[index];

    }

    public void set(int index,E e){
        if(index<0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        data[index]=e;
    }

    public boolean contains(E e){
        for(int i=0; i<size;i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    public int find(E e){
        for(int i=0; i<size;i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index){
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        E del = data[index];

        for(int i=index;i<size-1;i++){
            data[i]=data[i+1];
        }

        size--;
        data[size]=null;
        if(size==data.length/4){
            resize(data.length/4);
        }
        return del;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public static void main(String[] args) {

        dynamicArray<Integer> arr = new dynamicArray<>();
        for(int i = 0 ; i < 10 ; i ++)
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeLast();
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);
    }

}
