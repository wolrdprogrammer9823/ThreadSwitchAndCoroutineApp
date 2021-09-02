package com.wolfsea.threadswitchandcoroutineapp.advancedfunction

/**
 *@desc  高阶函数D3
 *@author liuliheng
 *@time 2021/9/3  0:23
 **/

fun main() {
    println("main start")
    val str = ""
    printStr(str) {
        value ->
        println("lambda start")
        if (value.isEmpty()) return
        println(value)
        println("lambda end")
    }
    println("main end")
}

inline fun printStr(str: String, block: (String) -> Unit) {
    println("printStr start")
    block(str)
    println("printStr end")
}