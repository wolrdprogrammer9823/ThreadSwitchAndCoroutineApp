package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *@desc  Flow VS Sequence
 *@author liuliheng
 *@time 2021/8/2  0:23
 **/
fun main() = runBlocking {

    launch {
        for (i in 1..5) {

            delay(100)
            println("I'm blocked $i")
        }
    }

    sequence {
        for (i in 1..5) {

            Thread.sleep(100)
            yield(i)
        }
    }.forEach {

        println(it)
    }

    println("done.")
}