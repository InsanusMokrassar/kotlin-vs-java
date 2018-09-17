package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.QuickSort

import java.util.ArrayList

fun sort(source: Array<Int>): Array<Int> {
    if (source.size < 2) {
        return source
    }

    val position = random.nextInt(1 + source.size - 2)

    val leftList = ArrayList<Int>()
    val rightList = ArrayList<Int>()

    val main = source[position]

    source.forEachIndexed {
        i, current ->

        if (i != position) {
            if (main >= current) {
                leftList.add(current)
            } else {
                rightList.add(current)
            }
        }
    }

    var left = leftList.toTypedArray()
    var right = rightList.toTypedArray()

    if (left.size > 1) {
        left = sort(left)
    }

    if (right.size > 1) {
        right = sort(right)
    }

    return left.plus(main).plus(right)
}
