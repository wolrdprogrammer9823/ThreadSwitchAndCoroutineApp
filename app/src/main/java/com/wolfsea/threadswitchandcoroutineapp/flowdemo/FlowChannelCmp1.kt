package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 *@desc  Flow与Channel进行比较1
 *@author liuliheng
 *@time 2021/8/1  23:43
 **/
fun main() = runBlocking {

    val time = measureTimeMillis {
        flow {
            for (i in 20..25) {

                delay(100)
                emit(i)
            }
        }.collect {

            delay(100)
            println("value:${it}")
        }
    }

    println("cost:${time}")

}