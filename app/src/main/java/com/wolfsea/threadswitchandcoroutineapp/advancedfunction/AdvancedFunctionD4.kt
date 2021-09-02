package com.wolfsea.threadswitchandcoroutineapp.advancedfunction

/**
 *@desc  高阶函数D4
 *@author liuliheng
 *@time 2021/9/3  0:35
 **/

fun main() {


}

//crossinline关键字保证内联函数中的lambda表达式一定不使用return关键字
//(内联函数中的lambda表达式允许使用return关键字,内联函数中的匿名内部类不允许使用return关键字)
inline fun runRunnable(crossinline block: () -> Unit) {

    val runnable = Runnable {

        block()
    }

    runnable.run()
}