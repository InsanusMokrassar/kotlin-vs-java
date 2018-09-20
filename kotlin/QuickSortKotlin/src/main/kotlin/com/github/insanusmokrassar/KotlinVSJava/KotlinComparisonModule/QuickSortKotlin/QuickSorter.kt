package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.QuickSortKotlin

import java.util.ArrayList

fun sort(source: Array<Int>): Array<Int> {
    if (source.size < 2) {
        return source
    }

    val position = random.nextInt(1 + source.size - 2)

    var lt: Int = 0
    var gte: Int = 0

    val main = source[position]

    source.forEachIndexed {
        i, current ->
        if (i != position) {
            if (main >= current) {
                lt++
            } else {
                gte++
            }
        }
    }

    val left = Array(lt) { 0 }
    val right = Array(gte) { 0 }

    lt = 0
    gte = 0

    source.forEachIndexed {
        i, current ->

        if (i != position) {
            if (main >= current) {
                left[lt] = current
                lt++
            } else {
                right[gte] = current
                gte++
            }
        }
    }

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
