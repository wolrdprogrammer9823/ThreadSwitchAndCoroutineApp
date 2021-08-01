package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

/**
 *@desc  FlowDemo3
 *@author liuliheng
 *@time 2021/8/1  23:32
 **/
fun main() = runBlocking {

    listOf("a","b","c","d").asFlow().onEach {
        delay(100)
    }.collect {
        println("ch:${it}")
    }
}