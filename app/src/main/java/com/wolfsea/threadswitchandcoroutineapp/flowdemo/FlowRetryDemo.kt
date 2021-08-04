package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

/**
 *@desc flow中使用retry
 *@author liuliheng
 *@time 2021/8/5  0:14
 **/
fun main() = runBlocking {

    (1..5).asFlow().onEach {

        if (it == 3) throw RuntimeException("Error on $it")
    }.retry(2) {
        if (it is RuntimeException) {

            return@retry true
        }
        false
    }.onEach {

        println("Emitting $it")
    }.catch {

        it.printStackTrace()
    }.collect()
}