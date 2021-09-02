package com.wolfsea.threadswitchandcoroutineapp.advancedfunction

/**
 *@desc  高阶函数Demo1
 *@author liuliheng
 *@time 2021/9/2  23:48
 **/

fun main() {

    val age1 = 18
    val age2 = 25

    val result1 = age1AndAge2(age1, age2, ::plusAge)
    val result2 = age1AndAge2(age1, age2, ::minAge)

    println("result1:$result1")
    println("result2:$result2")

    val age3 = 30
    val age4 = 40

    val result3 = age1AndAge2(age3,age4) {
        a1,a2 ->
        a1 + a2
    }

    val result4 = age1AndAge2(age3,age4) {
        b1,b2 ->
        b1 - b2
    }

    println("result3:$result3")

    println("result4:$result4")

}

inline fun age1AndAge2(age1: Int, age2: Int, count: (Int, Int) -> Int): Int = count(age1, age2)

fun plusAge(age1: Int, age2: Int): Int = age1 + age2

fun minAge(age1: Int,age2: Int) : Int = if (age1 < age2) age1 else age2