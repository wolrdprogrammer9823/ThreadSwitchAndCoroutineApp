package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 *@desc  flow flatMapMerge
 *@author liuliheng
 *@time 2021/8/4  23:38
 **/
@FlowPreview
fun main() = runBlocking {

    val result = arrayListOf<Int>()
    for (i in 1..3) {

        result.add(i)
    }

    result.asFlow().flatMapMerge {
        flow {

            emit(it)
        }.flowOn(Dispatchers.IO)
    }.collect {

        println("value:$it")
    }
}