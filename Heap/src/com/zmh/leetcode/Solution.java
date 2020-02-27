package com.zmh.leetcode;

import com.zmh.priorityqueue.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Solution {

    private class Freq implements Comparable<Freq> {

        private int e;  //数值
        private int freq;  //频次

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        /**
         * 自定义比较规则：频次低的优先级高
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Freq o) {
            if(this.freq<o.freq){
                return 1;
            }else if(this.freq>o.freq){
                return -1;
            }else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int num : nums) {
            if (tm.containsKey(num)) {
                tm.put(num, tm.get(num) + 1);
            } else {
                tm.put(num, 1);
            }
        }

        PriorityQueue<Freq> pq = new PriorityQueue();
        for(int key: tm.keySet()){
            if(pq.getSize()<k){ //表示没有存够
                pq.enqueue(new Freq(key,tm.get(key)));
            }else if(tm.get(key)>pq.getFront().freq){
                pq.dequeue();
                pq.enqueue(new Freq(key,tm.get(key)));
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()){
            list.add(pq.dequeue().e);
        }
        return list;
    }

    private static void printList(List<Integer> nums) {
        for (Integer num : nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        printList((new Solution()).topKFrequent(nums, k));
    }
}
