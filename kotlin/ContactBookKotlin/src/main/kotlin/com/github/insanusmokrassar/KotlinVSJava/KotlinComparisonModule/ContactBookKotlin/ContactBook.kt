package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin

import kotlinx.coroutines.experimental.*
import java.util.*

fun main(args: Array<String>) {
    runBlocking {
        val inLaunch = launch {
            val scanner = Scanner(System.`in`)
            while (isActive) {
                val command = scanner.nextLine()
                println(command)
            }
        }
        val outLaunch = launch(start = CoroutineStart.LAZY) {
            System.out.println("ggwp")
        }

        outLaunch.join()
        inLaunch.join()
    }
}
