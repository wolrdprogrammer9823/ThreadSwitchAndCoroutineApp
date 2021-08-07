package com.wolfsea.threadswitchandcoroutineapp.extendmethod
import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 *@desc
 *@author liuliheng
 *@time 2021/8/7  16:14
 **/
inline fun <reified T : Activity> Context?.startActivity() =
    this?.startActivity(Intent(this, T::class.java))