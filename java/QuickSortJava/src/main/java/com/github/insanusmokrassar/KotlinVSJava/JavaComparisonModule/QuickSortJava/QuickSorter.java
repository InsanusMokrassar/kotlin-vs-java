package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.QuickSortJava;

public class QuickSorter {
    public static Integer[] sort(Integer[] source) {
        if (source.length < 2) {
            return source;
        }

        int position = QuickSort.random.nextInt(1 + source.length - 2);

        Integer main = source[position];

        int gte = 0;// count of elements grant or equal to main
        int lt = 0;// count of elements less than main

        for (int i = 0; i < source.length; i++) {
            if (i != position) {
                Integer current = source[i];
                if (main >= current) {
                    lt++;
                } else {
                    gte++;
                }
            }
        }

        Integer[] left = new Integer[lt];
        Integer[] right = new Integer[gte];

        lt = 0;// now it is index of array
        gte = 0;// now it is index of array

        for (int i = 0; i < source.length; i++) {
            if (i != position) {
                Integer current = source[i];
                if (main >= current) {
                    left[lt] = current;
                    lt++;
                } else {
                    right[gte] = current;
                    gte++;
                }
            }
        }

        if (lt > 1) {
            left = sort(left);
        }
        
        if (gte > 1) {
            right = sort(right);
        }
        
        Integer[] result = new Integer[source.length];

        System.arraycopy(left, 0, result, 0, left.length);
        result[left.length] = main;
        System.arraycopy(right, 0, result, left.length + 1, right.length);
        
        return result;
    }
}
