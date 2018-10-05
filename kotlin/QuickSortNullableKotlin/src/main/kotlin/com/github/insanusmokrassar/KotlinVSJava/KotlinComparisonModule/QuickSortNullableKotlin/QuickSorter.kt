package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.QuickSortNullableKotlin

fun sort(source: Array<Int?>): Array<Int?> {
    if (source.size < 2) {
        return source
    }

    var main: Int
    val position: Int

    while (true) {
        val index = random.nextInt(1 + source.size - 2)
        val value = source[index]
        if (value != null) {
            position = index
            main = value
            break
        }
    }

    var lt = 0
    var gte = 0

    source.forEachIndexed {
        i, current ->
        if (i != position) {
            if (current == null || main >= current) {
                lt++
            } else {
                gte++
            }
        }
    }

    val left = arrayOfNulls<Int>(lt)
    val right = arrayOfNulls<Int>(gte)

    lt = 0
    gte = 0

    source.forEachIndexed {
        i, current ->

        if (i != position) {
            if (current == null || main >= current) {
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
