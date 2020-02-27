package com.zmh.set;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e=e;
            left=null;
            right=null;
        }
    }


    private Node root;
    private int size;

    public BST(){
        root=null;
        size=0;
    }
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        if(root==null){
            root = new Node(e);
            size++;
        }else {
            add(root,e);
        }

    }
    //向Node为根的二分搜索树种插入元素e,递归算法
    private Node add(Node node,E e){

        //递归终止条件
        if(node==null){
            size++;
            return new Node(e);
        }

        //递归体
        if(e.compareTo(node.e)<0){
            node.left=add(node.left,e);
        }else {
            node.right=add(node.right,e);
        }
        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node,E e){

        if(node==null)
            return false;

        if(e.compareTo(node.e)==0){
            return true;
        }else if(e.compareTo(node.e)<0){
            return contains(node.left,e);
        }else {
            return contains(node.right,e);
        }
    }

    //前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node==null){
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node==null){
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //后序遍历
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node==null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }


    //非递归前序遍历
    public void noRePreOrder(){
        Stack<Node> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()){
            Node cur = s.pop();
            System.out.println(cur.e);
            if(cur.right!=null)
                s.push(cur.right);
            if(cur.left!=null)
                s.push(cur.left);
        }

    }

    //层序遍历
    public void cx(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if(cur.left!=null)
                q.add(cur.left);
            if(cur.right!=null)
                q.add(cur.right);
        }
    }


    public E minMum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minMum(root).e;
    }
    private Node minMum(Node node){
       if(node.left==null){
           return node;
       }
       return minMum(node.left);
    }

    public E removeMin(){
        E ret = minMum();
        root=removeMin(root);
        return ret;
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
    public E maxMum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maxMum(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maxMum(Node node){
        if( node.right == null )
            return node;

        return maxMum(node.right);
    }

    // 从二分搜索树中删除最大值所在节点
    public E removeMax(){
        E ret = maxMum();
        root = removeMax(root);
        return ret;
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

    public void remove(E e){
        remove(root,e);
    }

    private Node remove(Node node, E e) {
        if(node==null)
            return null;

        if(e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right = remove(node.right,e);
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
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] num = {18,5,2,7,20,6,12,19,21};
        for(int i=0;i<num.length;i++){
            bst.add(num[i]);
        }
//        bst.preOrder();
//        bst.inOrder();
//        bst.postOrder();
//        bst.noRePreOrder();
//        bst.cx();
//        System.out.println(bst.minMum());
//        System.out.println(bst.maxMum());
      /*  bst.inOrder();
        System.out.println("===========");
        bst.removeMin();
        bst.inOrder();*/

      /*  BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;

        // test removeMin
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");


        // test removeMax
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMax());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");*/

      bst.inOrder();
      bst.remove(7);
      bst.remove(2);
        System.out.println("====");
      bst.inOrder();
    }


}
