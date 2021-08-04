package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

/**
 *@desc  flow中使用retryWhen
 *@author liuliheng
 *@time 2021/8/5  0:20
 **/
fun main() = runBlocking {

    (1..5).asFlow().onEach {

        if (it == 3) throw RuntimeException("error on $it")
    }.onEach {

        println("emitting $it")
    }.retryWhen { _, attempt ->

        attempt < 2
    }.catch {

        //it.printStackTrace()
        println("exception:$it")
    }.collect()
}