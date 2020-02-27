import java.util.TreeMap;

public class Trie {

    private class Node{
        boolean isWord;
        TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;

            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size=0;
    }

    public int getSize(){
        return size;
    }

    /**
     * 向Trie添加单词
     * @param words
     */
    public void add(String words){
        Node cur = root;
        for(int i=0;i<words.length();i++){
            Character c = words.charAt(i);

            if(cur.next.get(c)==null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }


        //这个节点之前不存在或者不表示一个单词
        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String words){
        Node cur = root;
        for(int i=0;i<words.length();i++){
            Character c= words.charAt(i);
            if(cur.next.get(c)==null){
                return false;
            }
            cur = cur.next.get(c);
        }

        // 比如trie中有panda，查pan。虽然到节点，但是并没有这个单词。
        return cur.isWord;

    }

    public boolean isPrefix(String prefix){

        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i ++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true; // 单词本身也是该单词的前缀。
    }
}
