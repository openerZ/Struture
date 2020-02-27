package com.zmh.array;

/**
 * 自定义数组
 */
public class Array {

    private int[] data;
    private int size;

    public Array(int capacity){
        data=new int[capacity];
        size=0;
    }

    public Array(){
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

    public void add(int index, int e){
        if(size == data.length)
            throw new IllegalArgumentException("Add failed. Array is full.");

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        for(int i=size ;i>index;i--){
            data[i]=data[i-1];
        }
        data[index]=e;
        size++;
    }

    public void addFirst(int e){
        add(0,e);
    }

    public void addLast(int e){
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

    public int getData(int index){
        if(index<0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }

        return data[index];

    }

    public void set(int index,int e){
        if(index<0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        data[index]=e;
    }

    public boolean contains(int e){
        for(int i=0; i<size;i++){
            if(data[i]==e){
                return true;
            }
        }
        return false;
    }

    public int find(int e){
        for(int i=0; i<size;i++){
            if(data[i]==e){
                return i;
            }
        }
        return -1;
    }

    public int remove(int index){
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }
        int del = data[index];

        for(int i=index;i<size-1;i++){
            data[i]=data[i+1];
        }

        size--;
        return del;
    }

    public int removeFirst(){
        return remove(0);
    }

    public int removeLast(){
        return remove(size-1);
    }
    public static void main(String[] args) {
        Array a = new Array(10);
        for(int i =0;i<10;i++){
            a.addLast(i);
        }
        System.out.println(a.find(5));
        a.set(6,100);
        System.out.println(a.toString());

        int remove = a.remove(6);
        System.out.println(a.toString());
        System.out.println(remove);

    }
}
