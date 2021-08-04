package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.lang.IllegalArgumentException

/**
 *@desc flow中使用try-catch
 *@author liuliheng
 *@time 2021/8/4  23:51
 **/
fun main() = runBlocking {

    flow {

        emit(1)
        try {

            throw IllegalArgumentException()
        } catch (ex: Exception) {

            ex.printStackTrace()
        }
    }.onCompletion {

        println("Done")
    }.collect {

        println("exception:$it")
    }
}