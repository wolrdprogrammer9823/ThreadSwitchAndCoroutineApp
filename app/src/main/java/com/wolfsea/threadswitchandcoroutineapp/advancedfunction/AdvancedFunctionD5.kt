package com.wolfsea.threadswitchandcoroutineapp.advancedfunction
import android.content.Context
import android.content.SharedPreferences

/**
 *@desc  高阶函数D5
 *@author liuliheng
 *@time 2021/9/3  0:43
 **/
fun main(context: Context) {

    context.getSharedPreferences("data",Context.MODE_PRIVATE).open {
        putInt("int", 1)
        putLong("long", 10L)
        putBoolean("boolean", false)
    }
}

fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    val edit = edit()
    edit.block()
    edit.apply()
}