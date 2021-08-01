package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *@desc  Flow VS Sequence
 *@author liuliheng
 *@time 2021/8/2  0:18
 **/
fun main() = runBlocking {

    launch {
        for (i in 1..5) {
            delay(100)
            println("I'm not blocked $i")
        }
    }

    flow {
        for (i in 1..5) {
            delay(100)
            emit(i)
        }
    }.collect {
        println(it)
    }

    println("done.")
}