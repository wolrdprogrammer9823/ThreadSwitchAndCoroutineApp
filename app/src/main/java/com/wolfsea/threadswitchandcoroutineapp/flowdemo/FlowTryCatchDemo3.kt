package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

/**
 *@desc flow中使用try-catch
 *@author liuliheng
 *@time 2021/8/5  0:00
 **/
fun main() = runBlocking {

    //catch可在onCompletion之前也可在之后
    flow {

        emit(1)
        throw RuntimeException()
    }.onCompletion { cause ->

        if (cause != null) {

            println("flow completed exceptionally")
        } else {

            println("Done.")
        }
    }.catch {

        println("catch exception")
    }.collect {

        println("exception:$it")
    }

}