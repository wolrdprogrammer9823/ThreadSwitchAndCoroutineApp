package com.wolfsea.threadswitchandcoroutineapp.flowdemo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.util.*

/**
 *@desc flow使用demo
 *@author liuliheng
 *@time 2021/8/6  23:47
 **/
fun main() = runBlocking {

    val dataSource = mutableListOf<String>()
    dataSource.add("a11122")
    dataSource.add("a11122-001")
    dataSource.add("b11120-001")
    dataSource.add("a11122-002")
    dataSource.add("c21122")
    dataSource.add("c21122-001")
    dataSource.add("c21122-002")
    dataSource.add("d31122-001")
    dataSource.add("d31122-002")

//    val dataMap = mutableMapOf<String, MutableList<String>>()
//    for (value in dataSource) {
//
//        val v1 = Util.cutStr(value)
//        val v2 = Util.getSubStr(value)
//
//        val keyExits = dataMap[v1] != null
//        if (keyExits) {
//            if (v2 != "-1") {
//
//                val dataList = dataMap[v1]
//                dataList?.add(v2)
//            }
//        } else {
//            if (v2 != "-1") {
//
//                val dataList = mutableListOf<String>()
//                dataList.add(v2)
//                dataMap[v1] = dataList
//            }
//        }
//    }
//
//    for ((key, value) in dataMap) {
//
//        println("key:$key,value:${value.size}")
//    }


//    dataSource.asFlow().flatMapMerge {
//        flow {
//            emit(it)
//        }.flowOn(Dispatchers.IO)
//    }.collect()

    val newDataMap = mutableMapOf<String, MutableList<String>>()
    dataSource.asFlow().map {

        val v1 = Util.cutStr(it)
        val v2 = Util.getSubStr(it)

        val keyExits = newDataMap[v1] != null
        if (keyExits) {
            if (v2 != "-1") {

                val dataList = newDataMap[v1]
                dataList?.add(v2)
            }
        } else {
            if (v2 != "-1") {

                val dataList = mutableListOf<String>()
                dataList.add(v2)
                newDataMap[v1] = dataList
            }
        }
    }.collect()

    for ((key, value) in newDataMap) {

        println("key:$key,value:${value.size}")
    }

}
