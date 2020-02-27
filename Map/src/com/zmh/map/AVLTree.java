package com.zmh.map;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        private int height;

        public Node(K key, V value){
            this.key=key;
            this.value=value;
            this.left = null;
            this.right = null;
            height=1;
        }

        @Override
        public String toString() {
            return key.toString()+":"+value.toString();
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root=null;
        size=0;
    }

    public int getSize(){
        return size;
    }


    public boolean isEmpty() {
        return size==0;
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

    public boolean contains(K key) {
        return getNode(key)!=null;
    }

    //得到节点的高度
    private int getHeight(Node node){
        if(node==null){
            return 0;
        }

        return node.height;
    }

    //计算平衡因子 ：当前节点的左子树的高度—当前节点的右子树的高度
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    //右旋
    private Node rightRotate(Node node){
        Node x = node.left;
        Node t = x.right;

        x.right = node;
        node.left = t;

        node.height =1 + Math.max(getHeight(node.left),getHeight(node.right));
        x.height = 1+ Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    //左旋
    private Node leftRotate(Node y){
        Node x = y.right;
        Node t =x.left;

        x.left = y;
        y.right = t;

        y.height =1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1+ Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    public void add(K key, V value) {

        root=add(root,key,value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        // 上面条件不满足，说明还得继续往下找左右子树为null可以挂载上的节点
        if (key.compareTo(node.key) < 0)
            // 如果e小于node.e，那么继续往它的左子树添加该节点,这里插入结果可能根发生了变化。
            node.left = add(node.left, key, value); // 新节点赋值给node.left,改变了二叉树
        else if (key.compareTo(node.key) > 0)
            // 大于，往右子树添加。
            node.right = add(node.right, key, value);
            // 如果相等
        else
            node.value = value;
        // 更新height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        //当前节点的平衡因子
        int balanceFactor = getBalanceFactor(node);
        //LL
        if (balanceFactor >1 && getBalanceFactor(node.left) >= 0){
            //进行右旋
            return rightRotate(node);
        }else if(balanceFactor<-1 && getBalanceFactor(node.right) <= 0){  //RR
            return leftRotate(node);
        }else if(balanceFactor >1 && getBalanceFactor(node.left)<0){  //LR
            node.left=leftRotate(node.left);
            return rightRotate(node);
        }else if(balanceFactor < -1 && getBalanceFactor(node.right) >0){  //RL
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public  boolean isBST(){
        ArrayList<K> list = new ArrayList<>();
        inOrder(root,list);

        for(int i=1;i<list.size();i++){
            if(list.get(i-1).compareTo(list.get(i))>0){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断该二叉树是否是一棵平衡二叉树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
     * @param node
     * @return
     */
    private boolean isBalanced(Node node){

        if(node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void inOrder(Node node, ArrayList<K> list) {
        if(node==null){
            return;
        }

        inOrder(node.left,list);
        list.add(node.key);
        inOrder(node.right,list);

    }

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

        Node retNode;

        if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            retNode= node;
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            retNode= node;
        }else {
            if(node.left==null){
                Node right = node.right;
                node.right=null;
                size--;
                retNode= right;
            }else if (node.right==null){
                Node left = node.left;
                node.left=null;
                size--;
                retNode = left;
            }else {
                // 待删除节点左右子树均不为空的情况

                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minMum(node.right);

                successor.right=remove(node.right,successor.key);
                successor.left = node.left;

                node.left=node.right=null;
                retNode = successor;
            }
        }

        if(retNode==null){
            return null;
        }

        //更新当前节点的高度
        retNode.height = 1+ Math.max(getHeight(retNode.left),getHeight(retNode.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        if (balanceFactor >1 && getBalanceFactor(retNode.left) >= 0){ //LL
            //进行右旋
            return rightRotate(retNode);
        }else if(balanceFactor<-1 && getBalanceFactor(retNode.right) <= 0){  //RR
            return leftRotate(retNode);
        }else if(balanceFactor >1 && getBalanceFactor(retNode.left)<0){  //LR
            retNode.left=leftRotate(retNode.left);
            return rightRotate(retNode);
        }else if(balanceFactor < -1 && getBalanceFactor(retNode.right) >0){  //RL
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }


        return retNode;
    }

    public void set(K key, V newValue) {
        Node node = getNode(key);
        if(node==null){
            throw new IllegalArgumentException("没有这个key");
        }
        node.value = newValue;
    }

    public static void main(String[] args) {
        AVLTree<Integer ,Integer> avl = new AVLTree<>();
        for(int i=0;i<10;i++){
            avl.add(i,i);
        }

        System.out.println(avl.isBST());
        System.out.println(avl.isBalanced());

        for(int i=0;i<10;i++){
            avl.remove(i);
            if(!avl.isBalanced() || !avl.isBST())
                throw new RuntimeException("错误");
        }

        System.out.println("avl完成");
    }
}
