package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.NetworkEmulationKotlin

interface Network {
    suspend fun makeRequest(data: Int): Int
}