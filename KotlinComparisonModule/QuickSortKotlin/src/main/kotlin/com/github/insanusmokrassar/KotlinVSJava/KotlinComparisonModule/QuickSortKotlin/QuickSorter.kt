package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.QuickSortKotlin

fun sort(source: IntArray, from: Int = 0, to: Int = source.size) {
    if (to - from < 2) {
        return
    }

    var rightIndex = to - 1

    var mainIndex = random.nextInt(to - from - 1) + from

    val main = source[mainIndex]

    var leftIndex = from

    while (leftIndex < mainIndex) {
        val current = source[leftIndex]
        if (current >= main) {
            if (rightIndex == mainIndex) {
                source[rightIndex] = current
                source[leftIndex] = source[mainIndex - 1]

                mainIndex--
                source[mainIndex] = main
            } else {
                val right = source[rightIndex]
                source[rightIndex] = current
                source[leftIndex] = right
            }
            rightIndex--
        } else {
            leftIndex++
        }
    }

    while (mainIndex < rightIndex) {
        val current = source[rightIndex]
        if (current < main) {
            source[mainIndex] = current
            source[rightIndex] = source[mainIndex + 1]

            mainIndex++
            source[mainIndex] = main
        } else {
            rightIndex--
        }
    }

    sort(source, from, mainIndex)
    sort(source, mainIndex + 1, to)
}
