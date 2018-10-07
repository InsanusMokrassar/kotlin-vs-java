package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ImageHandlingKotlin

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.utils.getFolderFiles
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.awt.image.Raster
import java.io.File
import java.util.*
import javax.imageio.ImageIO
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

private val checkArray: IntArray = IntArray(1)

private const val transparent = 0

private var Rectangle.x1: Int
    get() = x
    set(value) {
        width += x1 - value
        x = value
    }

private var Rectangle.y1: Int
    get() = y
    set(value) {
        height += y1 - value
        y = value
    }

private var Rectangle.x2: Int
    get() = x1 + width - 1
    set(value) {
        width += value - x2
    }

private var Rectangle.y2: Int
    get() = y1 + height - 1
    set(value) {
        height += value - y2
    }

private fun Rectangle.include(x: Int, y: Int) {
    if (x1 > x) {
        x1 = x
    }
    if (y1 > y) {
        y1 = y
    }
    if (x2 < x) {
        x2 = x
    }
    if (y2 < y) {
        y2 = y
    }
}

private fun Rectangle.include(pair: Pair<Int, Int>) = include(pair.first, pair.second)

private fun Raster.isTransparent(x: Int, y: Int): Boolean {
    return (getPixel(x, y, checkArray)[0] == transparent).also {
        checkArray[0] = 0
    }
}

private fun calculateSpriteRect(
    alphaRaster: Raster,
    startX: Int,
    startY: Int
): Rectangle {
    val rectangle = Rectangle(startX, startY, 1, 1)
    val chordsQueue: Queue<Pair<Int, Int>> = ArrayDeque()
    val watched = ArrayList<Pair<Int, Int>>()
    chordsQueue.offer(startX to startY)

    while (chordsQueue.isNotEmpty()) {
        val current = chordsQueue.poll()

        if (!alphaRaster.isTransparent(current.first, current.second)) {
            rectangle.include(current)

            val leftBound = if (current.first > 0) {
                current.first - 1
            } else {
                0
            }
            val rightBound = if (current.first < alphaRaster.width - 1) {
                current.first + 1
            } else {
                alphaRaster.width - 1
            }
            val topBound = if (current.second > 0) {
                current.second - 1
            } else {
                0
            }
            val bottomBound = if (current.second < alphaRaster.height - 1) {
                current.second + 1
            } else {
                alphaRaster.height - 1
            }

            (topBound .. bottomBound).forEach {
                y ->
                (leftBound .. rightBound).forEach {
                    x ->
                    val pair = x to y
                    if (!watched.contains(pair)) {
                        watched.add(pair)
                        chordsQueue.offer(pair)
                    }
                }
            }
        }
    }
    return rectangle
}

private fun Collection<Rectangle>.unique(): Set<Rectangle> {
    val result = HashSet<Rectangle>()

    val leftToCheck = ArrayList<Rectangle>(this)

    while (leftToCheck.isNotEmpty()) {
        var current = leftToCheck.removeAt(0)

        val toCompare = ArrayList<Rectangle>(leftToCheck)

        for (currentToCompare in toCompare) {
            if (current.intersects(currentToCompare)) {
                current = current.union(currentToCompare)

                leftToCheck.remove(currentToCompare)
            }
        }
        result.add(current)
    }
    return result
}

private fun findSprites(alphaRaster: Raster): List<Rectangle> {
    val result = ArrayList<Rectangle>()

    var x = 0
    var y = 0

    while (y < alphaRaster.height) {
        while (x < alphaRaster.width) {
            if (!alphaRaster.isTransparent(x, y)) {
                result.firstOrNull {
                    it.contains(x, y)
                } ?.let {
                    x = it.x2
                } ?: result.add(
                    calculateSpriteRect(alphaRaster, x, y).also {
                        x = it.x2
                    }
                )
            }
            x++
        }
        y++
        x = 0
    }

    return result
}

fun calculateSprites(from: List<File>): List<BufferedImage> {
    return from.flatMap {
        file ->
        val image = ImageIO.read(file)
        findSprites(
            image.alphaRaster
        ).unique().map {
            rect ->
            image.getSubimage(rect.x, rect.y, rect.width, rect.height)
        }
    }
}

fun calculateSprites(from: File): List<BufferedImage> {
    return calculateSprites(from.getFolderFiles())
}

fun calculateSprites(from: String): List<BufferedImage> {
    return calculateSprites(File(from))
}
