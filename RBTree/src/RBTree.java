
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{

        private K key;
        private V value;
        private Node left, right;
        //初始节点的颜色为红色
        private boolean color;


        public Node(K key, V value){
            this.key =key;
            this.value =value;
            left =null;
            right = null;
            color =RED;
        }
    }

    //根节点
    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size=0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //判断是否为红色节点
    private boolean isRed(Node node){
        if(node==null){
            return BLACK;
        }
        return node.color;
    }

    //左旋转
    private Node leftRotate(Node node){
        Node x = node.right;
        Node t = x.left;

        node.right = t;
        x.left = node;

        x.color = node.color;
        node.color =RED;
        return x;
    }

    //右旋转
    private Node rightRotate(Node node){
        Node x =node.left;

        node.left = x.right;
        x.right = node;

        x.color =node.color;
        node.color =RED;

        return x;

    }

    //颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;

    }

    public void add(K key, V value) {

        root=add(root,key,value);
        root.color = BLACK; // 根节点始终保持为黑色

    }

    private Node add(Node node, K key, V value){
        if(node==null){
            node =  new Node(key,value);
            size++;
            return node;
        }

        if(key.compareTo(node.key)<0){
            node.left = add(node.left,key,value);
        }else if (key.compareTo(node.key)>0){
            node.right = add(node.right,key,value);
        }else {
            node.value = value;
        }


        if(isRed(node.right) && !isRed(node)){
            //左旋
            node = leftRotate(node);
        }

        if(isRed(node.left) && isRed(node.left.left)){
            //右旋转
            node=rightRotate(node);
        }

        if( isRed(node.left) && isRed(node.right)){
            //颜色翻转
           flipColors(node);
        }

        return node;

    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

}
