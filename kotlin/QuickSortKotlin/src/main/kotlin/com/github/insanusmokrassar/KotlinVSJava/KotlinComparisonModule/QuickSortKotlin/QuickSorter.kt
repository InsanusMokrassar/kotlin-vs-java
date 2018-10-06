package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.QuickSortKotlin

fun sort(source: Array<Int>, from: Int = 0, to: Int = source.size) {
    if (to - from < 2) {
        return
    }

    var rightIndex = to - 1

    var mainIndex = random.nextInt(to - from - 1) + from

    val main = source[mainIndex]

    var i = from

    while (i < mainIndex) {
        val current = source[i]
        if (current >= main) {
            if (rightIndex == mainIndex) {
                source[rightIndex] = current
                source[i] = source[mainIndex - 1]
                source[mainIndex - 1] = main

                mainIndex--
                rightIndex--
            } else {
                val right = source[rightIndex]
                source[rightIndex] = current
                source[i] = right

                rightIndex--
            }
        } else {
            i++
        }
    }

    sort(source, from, mainIndex)
    sort(source, mainIndex + 1, to)
}
