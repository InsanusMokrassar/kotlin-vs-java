package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.NetworkEmulationKotlin

import kotlinx.coroutines.delay

class NetworkImpl(
    private val defaultDelay: Long = 1000L
) : Network {
    override suspend fun makeRequest(data: Int): Int {
        delay(defaultDelay)
        return data
    }
}