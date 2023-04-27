package com.example.coroutinetest

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

fun main() = runBlocking {

    val flowA = (1..6).asFlow()
    val flowB = flowOf("one", "two", "three", "four", "five").onEach { delay(100) }

    val time = measureTimeMillis {
        flowA.zip(flowB) { a, b -> "$a and $b" }
            .collect { println(it) }
    }

    println("Cost $time ms")
}