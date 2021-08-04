package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException

/**
 *@desc flow中使用catch
 *@author liuliheng
 *@time 2021/8/4  23:55
 **/
fun main() = runBlocking {
    
    flow {
        
        emit(1)
        throw IllegalArgumentException()
    }.onCompletion { cause ->

        if (cause != null) {

            println("Flow completed exceptionally")
        } else {

            println("Done.")
        }
    }.collect {

        println("cause:$it")
    }

}