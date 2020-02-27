package segmentTree;

public class segmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public segmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;

        data =(E[]) new Object[arr.length];
        for(int i=0; i<arr.length;i++){
            data[i] = arr[i];
        }

        tree =(E[]) new Object[4 * arr.length];

        //构建线段树
        bulidSegmentTree(0,0,data.length-1);
    }

    private void bulidSegmentTree(int treeIndex, int l, int r) {
        if(r==l){
            tree[treeIndex]=data[l];
            return;
        }

        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        int mid = l + (r-l)/2;

        bulidSegmentTree(leftIndex,l,mid);
        bulidSegmentTree(rightIndex,mid+1,r);

        tree[treeIndex] = merger.merger(tree[leftIndex],tree[rightIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }

        return data[index];
    }

    public int leftChild(int index){
        return 2 * index+1;
    }
    public int rightChild(int index){
        return 2 * index+2;
    }

    public E query(int queryL, int queryR){
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0,0,data.length-1,queryL,queryR);

    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if(queryL==l && queryR==r){
            return tree[treeIndex];
        }

        int mid = l + (r-l)/2;

        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        if(queryR<=mid){
            return query(leftIndex,l,mid,queryL,queryR);
        }else if(queryL>mid){
            return query(rightIndex,mid+1,r,queryL,queryR);
        }

        //ql<=mid<qr
        E leftValue = query(leftIndex,l,mid,queryL,mid);
        E rightValue = query(rightIndex,mid+1,r,mid+1,queryR);

        return merger.merger(leftValue,rightValue);
    }

    public void set(int index, E e){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");

        data[index] = e; // index位置换新值
        set(0,0,data.length-1,index,e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if(l==r){
            tree[treeIndex]=e;
            return;
        }

        int mid = l +(r-l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        if(index>=mid+1){
            set(rightIndex,mid+1,r,index,e);
        }else {
            set(leftIndex,l,mid,index,e);
        }

        tree[treeIndex] = merger.merger(tree[leftIndex],tree[rightIndex]);


    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] nums =  {-2, 0, 3, -5, 2, -1};
        segmentTree<Integer> s = new segmentTree<Integer>(nums,(a,b) -> a+b);
        System.out.println(s.query(0, 2)); //1
        System.out.println(s.query(2, 5)); //-1
        System.out.println(s.query(0, 5)); //-3
        System.out.println(s.query(0,1)); //-2

    }
}
