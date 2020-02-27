package segmentTree;

//函数对象：对线段树的集合操作
public interface Merger<E> {

    E merger(E a, E b);
}
