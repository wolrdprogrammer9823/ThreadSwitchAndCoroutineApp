package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 *@desc flowOn Dmeo2
 *@author liuliheng
 *@time 2021/8/4  23:20
 **/
fun main() = runBlocking {
    flow {
        for (i in 1..5) {
            delay(100)
            emit(i)
        }
    }.map {
        it * it
    }.flowOn(Executors.newCachedThreadPool().asCoroutineDispatcher()).collect {

        println("value:$it")
    }
}