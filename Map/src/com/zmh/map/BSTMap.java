package com.zmh.map;

import java.util.LinkedList;
import java.util.Queue;

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V>{

    private class Node{
        public K key;
        public V value;
        public Node left,right;

        public Node(K key, V value){
            this.key=key;
            this.value=value;
            this.left = null;
            this.right = null;
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return key.toString()+":"+value.toString();
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size=0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key)!=null;
    }

    @Override
    public void add(K key, V value) {
        root=add(root,key,value);
    }

    private Node add(Node node, K key, V value){
        if(node==null){
            node =  new Node(key,value);
            size++;
            return node;
        }

        if(key.compareTo(node.key)<0){
            node.left = add(node.left,key,value);
            return node;
        }else if (key.compareTo(node.key)>0){
            node.right = add(node.right,key,value);
            return node;
        }else {
            node.value = value;
            return node;
        }
    }

    private Node getNode(K key){
        return getNode(root,key);
    }

    private Node getNode(Node node,K key){

        if(node==null){
            return null;
        }

        if(key.compareTo(node.key)==0){
            return node;
        } else if(key.compareTo(node.key)<0){
            return getNode(node.left,key);
        }else {
            return getNode(node.right,key);
        }

    }

    @Override
    public V get(K key) {
        Node node= getNode(key);
        return node==null ? null : node.value;
    }

    public K minMum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minMum(root).key;
    }
    private Node minMum(Node node){
        if(node.left==null){
            return node;
        }
        return minMum(node.left);
    }

    public K removeMin(){
        K key = minMum();
        root=removeMin(root);
        return key;
    }

    private Node removeMin(Node node){
        if(node.left==null){
            Node right = node.right;
            node.right=null;
            size--;
            return right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 寻找二分搜索树的最大元素
    public K maxMum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maxMum(root).key;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maxMum(Node node){
        if( node.right == null )
            return node;

        return maxMum(node.right);
    }

    // 从二分搜索树中删除最大值所在节点
    public K removeMax(){
        K key = maxMum();
        root = removeMax(root);
        return key;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){

        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(key);
        if(node!=null){
            root=remove(root,key);
            return node.value;
        }

        return null;
    }

    private Node remove(Node node, K key){
        if(node==null)
            return null;

        if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            return node;
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            return node;
        }else {
            if(node.left==null){
                Node right = node.right;
                node.right=null;
                size--;
                return right;
            }else if (node.right==null){
                Node left = node.left;
                node.left=null;
                size--;
                return left;
            }else {
                // 待删除节点左右子树均不为空的情况

                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minMum(node.right);

                successor.right=removeMin(node.right);
                successor.left = node.left;

                node.left=node.right=null;
                return successor;
            }
        }
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if(node==null){
            throw new IllegalArgumentException("没有这个key");
        }
        node.value = newValue;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur = q.remove();
            str.append(cur.toString()+"\t");
            if(cur.left!=null)
                q.add(cur.left);
            if(cur.right!=null)
                q.add(cur.right);
        }
        return str.toString();
    }

    public static void main(String[] args) {
        BSTMap<Integer, Integer> bstm = new BSTMap<>();
        bstm.add(5,12);
        bstm.add(7,48);
        bstm.add(14,75);
        System.out.println(bstm);
        System.out.println(bstm.getSize());
        bstm.remove(14);
        System.out.println(bstm);
        System.out.println(bstm.getSize());
    }
}
