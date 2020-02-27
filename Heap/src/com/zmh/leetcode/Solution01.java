package com.zmh.leetcode;

import java.util.*;

public class Solution01 {

    private class Freq {

        private int e;  //数值
        private int freq;  //频次

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
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

        PriorityQueue<Integer> pq = new PriorityQueue(
                (a,b) -> tm.get(a)-tm.get(b)
        );
        for(int key: tm.keySet()){
            if(pq.size()<k){ //表示没有存够
                pq.add(key);
            }else if(tm.get(key)>tm.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()){
            list.add(pq.remove());
        }
        return list;
    }
}
