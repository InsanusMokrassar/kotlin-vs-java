package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.QuickSort;

import java.util.*;

public class QuickSorter {
    public Integer[] sort(Integer[] source) {
        int position = QuickSort.random.nextInt(source.length - 2);
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        Integer main = source[position];
        for (int i = 0; i < source.length; i++) {
            if (i == position) {
                continue;
            }
            Integer current = source[i];
            if (main.compareTo(current) < 0) {
                leftList.add(current);
            } else {
                rightList.add(current);
            }
        }
        Integer[] left = new Integer[leftList.size()];
        leftList.toArray(left);
        Integer[] right = new Integer[rightList.size()];
        rightList.toArray(right);
        
        if (left.length > 1) {
            left = sort(left);
        }
        
        if (right.length > 1) {
            right = sort(right);
        }
        
        Integer[] result = new Integer[source.length];

        System.arraycopy(left, 0, result, 0, left.length);
        result[position] = main;
        System.arraycopy(right, 0, result, position + 1, right.length);
        
        return result;
    }
}
