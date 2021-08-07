package com.wolfsea.threadswitchandcoroutineapp.flowdemo

/**
 *@desc
 *@author liuliheng
 *@time 2021/8/6  23:55
 **/
object Util {

    fun cutStr(str: String): String {
        return if (str.indexOf("-") == -1) str else str.substring(0, str.indexOf("-"))
    }

    fun getSubStr(str: String): String {
       return if (str.contains("-")) str.split("-")[1] else "-1"
    }
}