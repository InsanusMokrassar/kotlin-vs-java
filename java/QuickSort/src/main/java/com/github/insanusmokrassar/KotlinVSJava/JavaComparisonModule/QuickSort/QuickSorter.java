package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.QuickSort;

import java.util.*;

public class QuickSorter {
    public static Integer[] sort(Integer[] source) {
        if (source.length < 2) {
            return source;
        }
        int position = QuickSort.random.nextInt(1 + source.length - 2);
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        Integer main = source[position];
        for (int i = 0; i < source.length; i++) {
            if (i == position) {
                continue;
            }
            Integer current = source[i];
            if (main >= current) {
                leftList.add(current);
            } else {
                rightList.add(current);
            }
        }
        Integer[] left = leftList.toArray(new Integer[0]);
        Integer[] right = rightList.toArray(new Integer[0]);
        
        if (left.length > 1) {
            left = sort(left);
        }
        
        if (right.length > 1) {
            right = sort(right);
        }
        
        Integer[] result = new Integer[source.length];

        System.arraycopy(left, 0, result, 0, left.length);
        result[left.length] = main;
        System.arraycopy(right, 0, result, left.length + 1, right.length);
        
        return result;
    }
}
