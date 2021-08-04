package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 *@desc  flow buffer的使用
 *@author liuliheng
 *@time 2021/8/4  23:27
 **/
fun main() = runBlocking {

    val time = measureTimeMillis {
        flow {
            for (i in 1..5) {

                delay(100)
                emit(i)
            }
        }.buffer().collect {

            delay(300)
            println("value:$it")
        }
    }

    println("cost time:${time} ms")
}