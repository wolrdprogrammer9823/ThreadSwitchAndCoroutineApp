package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 *@desc Flow取消demo1
 *@author liuliheng
 *@time 2021/8/2  0:11
 **/
fun main() = runBlocking {

    withTimeoutOrNull(2500) {
        flow {
            for (i in 1..5) {

                delay(1000)
                emit(i)
            }
        }.collect {

            println("value:${it}")
        }
    }

    println("done.")
}