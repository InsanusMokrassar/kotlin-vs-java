package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.QuickSortKotlin

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

    val left = leftList.toTypedArray()
    val right = rightList.toTypedArray()

    return arrayOf(
        *if (left.size > 1) {
            sort(left)
        } else {
            left
        },
        main,
        *if (right.size > 1) {
            sort(right)
        } else {
            right
        }
    )
}
