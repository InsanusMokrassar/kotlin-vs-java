package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.NetworkEmulationKotlin

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.*
import kotlinx.coroutines.*
import java.util.concurrent.Executors

private const val testName = "NetworkEmulation.Kotlin"

fun main(args: Array<String>) {
    val countOfRequests = args.firstOrNull() ?.toIntOrNull() ?: 1000000
    val dispatcher = Executors.newFixedThreadPool(8).asCoroutineDispatcher()

    val network: Network = NetworkImpl()

    val points = arrayListOf<LogPoint>()
    points.add(LogPoint(testName, START_TEST))

    val requests = (0 until countOfRequests).map {
        Request(it, network)
    }
    points.add(LogPoint(testName, REQUESTS_FILLED))

    runBlocking(dispatcher) {
        requests.map {
            async {
                it.request()
            }
        }.also {
            points.add(LogPoint(testName, ALL_TASKS_CREATED))
        }.forEach {
            it.await()
        }
        points.add(LogPoint(testName, ALL_TASKS_COMPLETED))
    }
    points.add(LogPoint(testName, COMPLETE_TEST))

    dispatcher.close()
    printPoints(points)
}
