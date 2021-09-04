package com.wolfsea.threadswitchandcoroutineapp.advancedfunction

/**
 *@desc  高阶函数D6
 *@author liuliheng
 *@time 2021/9/4  23:08
 **/
fun main() {

    /*val testTarget: (String) -> Unit = {
        println("target:$it")
    }*/

    method(true) {
        println("target:$it")
    }

    method(false) {
        println("target:$it")
    }

}

fun method(target: Boolean, requestFlag: (String) -> Unit) {
    requestFlag(if(target) "成功" else "失败")
}