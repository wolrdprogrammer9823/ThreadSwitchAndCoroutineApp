package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 *@desc  Flow与Channel比较2
 *@author liuliheng
 *@time 2021/8/1  23:47
 **/
@ExperimentalCoroutinesApi
fun main() = runBlocking {

    val time = measureTimeMillis {
        channelFlow {
            for (a in 1..5) {

                delay(100)
                send(a)
            }
        }.collect {

            delay(100)
            println("value:${it}")
        }
    }

    println("cost:${time}")
}