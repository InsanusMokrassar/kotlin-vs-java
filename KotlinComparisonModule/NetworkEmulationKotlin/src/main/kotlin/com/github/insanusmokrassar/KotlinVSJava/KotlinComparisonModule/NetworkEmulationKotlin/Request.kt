package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.NetworkEmulationKotlin

class Request(
    private val inputData: Int,
    private val network: Network
) {
    suspend fun request(): Int = network.makeRequest(inputData)
}