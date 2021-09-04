package com.wolfsea.threadswitchandcoroutineapp.advancedfunction

/**
 *@desc  高阶函数D7
 *@author liuliheng
 *@time 2021/9/4  23:18
 **/

//typealias定义高阶函数
typealias TargetMethod = (Int) -> Unit

fun main() {

    function(true) {
        println("value:$it")
    }

    function(false) {
        println("value:$it")
    }

}

fun function(target: Boolean, targetMethod: TargetMethod) {
    targetMethod(if (target) 1 else 2)
}

