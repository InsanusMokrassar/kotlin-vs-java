package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.QuickSortJava;

public class QuickSorter {
    public static void sort(Integer[] source) {// to is exclude
        sort(source, 0, source.length);
    }


    public static void sort(Integer[] source, int from, int to) {// to is exclude
        if (to - from < 2) {
            return;
        }

        int rightIndex = to - 1;

        int mainIndex = QuickSort.random.nextInt(to - from - 1) + from;

        int main = source[mainIndex];

        int i = from;

        while (i < mainIndex) {
            int current = source[i];
            if (current >= main) {
                if (rightIndex == mainIndex) {
                    source[rightIndex] = current;
                    source[i] = source[mainIndex - 1];
                    source[mainIndex - 1] = main;

                    mainIndex--;
                    rightIndex--;
                } else {
                    int right = source[rightIndex];
                    source[rightIndex] = current;
                    source[i] = right;

                    rightIndex--;
                }
            } else {
                i++;
            }
        }

        sort(source, from, mainIndex);
        sort(source, mainIndex + 1, to);
    }
}
