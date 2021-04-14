package com.wolfsea.threadswitchandcoroutineapp.usecase
import io.reactivex.rxjava3.core.Observable

/**
 *@desc  账户管理
 *@author liuliheng
 *@time 2021/4/12  0:38
 **/
interface AccountManager {

    fun requestWxOpenId() : Observable<String>

    fun login(wxOpenId: String): Observable<User>
}