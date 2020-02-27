import java.util.HashMap;
import java.util.TreeMap;

//使用一个TreeMap数组
public class HashTable<K, V> {

    private static final int upperTol=10;
    private static final int lowerTol=2;
    private static int initCapacity=7;

    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;

    public HashTable(int M){
        size=0;
        this.M=M;
        hashtable = new TreeMap[M];
        for(int i=0;i<M; i++)
            hashtable[i] = new TreeMap<>();

    }

    public HashTable(){
        this(initCapacity);
    }

    //hash函数
    private int hash(K key){
        int h = (key.hashCode() & 0x7fffffff) % M;
        return h;
    }

    public void add(K key, V value){
        //通过hash函数计算该应该放在数组的那个TreeMap中
       TreeMap<K, V> map= hashtable[hash(key)];
       if(map.containsKey(key)){
           map.put(key,value);
       }else {
           map.put(key,value);
           size++;

           if(size >= M * upperTol){
               resize(M*2);
           }
       }
    }

    public V remove(K key){
        V ret = null;
        TreeMap<K, V> map= hashtable[hash(key)];

        if(map.containsKey(key)){
            ret = map.remove(key);
            size--;

            if(size < lowerTol * M & M/2 >=initCapacity){
                resize(M/2);
            }
        }
        return ret;
    }

    private void resize(int newM) {
        TreeMap<K,V>[] newhashtable = new TreeMap[newM];
        for(int i=0;i<newM;i++)
            newhashtable[i] = new TreeMap<>();

        int oldM=this.M;
        this.M=newM;

        for(int i=0;i<oldM;i++){
            TreeMap<K,V> map=hashtable[i];
            for(K key:map.keySet()){
               newhashtable[hash(key)].put(key,map.get(key));
            }
        }

        this.hashtable = newhashtable;

    }

    public void set(K key, V value){
        TreeMap<K, V> map= hashtable[hash(key)];
        if(!map.containsKey(key)){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }else {
            map.put(key,value);
        }
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }
    public V get(K key){

        return hashtable[hash(key)].get(key);
    }

    public static void main(String[] args) {
        HashTable<Integer, Integer> hash = new HashTable<>();
        for(int i=0;i<10;i++){
            hash.add(i,i+1);
            System.out.println(hash.remove(i));
        }

        HashMap<Integer, Integer> map = new HashMap<>();
    }
}
