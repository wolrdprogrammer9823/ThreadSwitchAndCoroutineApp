package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

/**
 *@desc  FlowDemo2
 *@author liuliheng
 *@time 2021/8/1  23:30
 **/

fun main() = runBlocking {
    flowOf(6,7,8,9,10).onEach {

        delay(100)
    }.collect {

        println("value:${it}")
    }
}