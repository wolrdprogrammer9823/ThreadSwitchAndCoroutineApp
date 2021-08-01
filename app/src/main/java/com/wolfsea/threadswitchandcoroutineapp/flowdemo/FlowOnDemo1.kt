package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 *@desc  FlowOn的Demo1
 *@author liuliheng
 *@time 2021/8/1  23:57
 **/
fun main() = runBlocking {
    flow {
        for (x in 12..17) {

            delay(100)
            emit(x)
        }
    }.map {
        it * it
    }.flowOn(Dispatchers.IO)
     .collect {

         println("${Thread.currentThread().name}:${it}")
     }
}