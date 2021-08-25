package com.wolfsea.threadswitchandcoroutineapp.usecase

/**
 *@desc  实体对象
 *@author liuliheng
 *@time 2021/8/24  21:17
 **/
class BinObj {

    fun getMacThroughput(): Float = Math.random().times(10).toFloat()

    fun getPhyThroughput(): Float = Math.random().times(10).toFloat()

    fun getDlsThroughput(): Float = Math.random().times(10).toFloat()

    fun getUlsThroughput(): Float = Math.random().times(10).toFloat()

    fun getDlThroughput(): Float = Math.random().times(10).toFloat()

    fun getUlThroughput(): Float = Math.random().times(10).toFloat()
}