package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

/**
 *@desc  ChannelFlow
 *@author liuliheng
 *@time 2021/8/1  23:35
 **/
fun main() = runBlocking {

    channelFlow {
        for (i in 11..15) {
            kotlinx.coroutines.delay(100)
            send(i)
        }
    }.collect {
        println("s-:${it}")
    }
}