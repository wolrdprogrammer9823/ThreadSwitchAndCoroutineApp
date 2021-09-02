package com.wolfsea.threadswitchandcoroutineapp.advancedfunction
import java.lang.StringBuilder

/**
 *@desc  高阶函数D2
 *@author liuliheng
 *@time 2021/9/3  0:10
 **/

fun main() {

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")

    val result = StringBuilder().build {
        list.forEach {
            append(it).append("\n")
        }
    }

    println(result)
}

fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}