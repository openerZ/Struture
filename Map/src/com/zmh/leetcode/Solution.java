package com.zmh.leetcode;

import java.util.TreeSet;

public class Solution {

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet set = new TreeSet();
        for(String word : words){
            StringBuilder str = new StringBuilder();
            for(int i =0;i<word.length();i++){
                str.append(codes[word.charAt(i)-'a']);
            }
            set.add(str.toString());
        }
        return set.size();
    }



}
