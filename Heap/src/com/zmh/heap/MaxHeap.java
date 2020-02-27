package com.zmh.heap;

import java.util.Random;

/**
 * 使用数组创建二叉堆
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        //Heapify将任意数组整理成堆的形状
        //初始找到最后一个有子节点的位置开始
        for(int i=parent(arr.length-1);i>=0;i--){
            //下沉
            siftDown(i);
        }

    }

    public MaxHeap(){
        this(10);
    }

    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }


    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index 0 doesn't have parent.");
        return (index-1)/2;
    }

    private int leftChild(int index){
        return index*2+1;
    }

    private int rightChild(int index){
        return index*2+2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }


    //上浮
    private void siftUp(int k) {
        while(k>0 && data.getData(parent(k)).compareTo(data.getData(k))<0){
            data.swap(k,parent(k));
            k=parent(k);
        }
    }

    //查找堆中最大的元素
    public E findMax(){
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.getData(0);
    }

    //取出并删除堆中最大的元素
    public E extractMax(){
        E ret = findMax();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return ret;
    }


    /**
     * 先找出该节点的左右节点比较大的子节点，然后与该节点进行比较，比该节点答交换位置，继续下沉比较，
     * 否则终止
     * @param k
     */
    private void siftDown(int k) {
        // k节点已经是叶子节点没有孩子了肯定不用下沉了。k的左孩子索引=size时已经越界了，肯定没有值了。
        while(leftChild(k)<data.getSize()){
            int j = leftChild(k);
            //判断是否有右子树时
            //如果有右子树并且右子树比左子树大
            if(j+1<data.getSize() && data.getData(j).compareTo(data.getData(j+1))<0) {
                j++; //data[j] 为右子树
            }
            //无论右子树存不存在，此时data[j]存储的是最大的子树节点
            //data[j] 与data[k]比较
            if(data.getData(k).compareTo(data.getData(j))>=0){
                break;
            }
            data.swap(k,j);
            k=j;
        }
    }

    public E replace(E e){
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }

}
