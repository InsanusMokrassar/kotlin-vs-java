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

        int leftIndex = from;

        while (leftIndex < mainIndex) {
            int current = source[leftIndex];
            if (current >= main) {
                if (rightIndex == mainIndex) {
                    source[rightIndex] = current;
                    source[leftIndex] = source[mainIndex - 1];

                    mainIndex--;
                    source[mainIndex] = main;
                } else {
                    int right = source[rightIndex];
                    source[rightIndex] = current;
                    source[leftIndex] = right;
                }
                rightIndex--;
            } else {
                leftIndex++;
            }
        }

        while (mainIndex < rightIndex) {
            int current = source[rightIndex];
            if (current < main) {
                source[mainIndex] = current;
                source[rightIndex] = source[mainIndex + 1];

                mainIndex++;
                source[mainIndex] = main;
            } else {
                rightIndex--;
            }
        }

        sort(source, from, mainIndex);
        sort(source, mainIndex + 1, to);
    }
}
