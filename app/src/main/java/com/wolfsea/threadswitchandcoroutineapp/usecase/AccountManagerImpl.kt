package com.wolfsea.threadswitchandcoroutineapp.usecase
import io.reactivex.rxjava3.core.Observable

/**
 *@desc  用户管理实现
 *@author liuliheng
 *@time 2021/4/12  0:42
 **/
class AccountManagerImpl : AccountManager {

    override fun requestWxOpenId(): Observable<String> {
        TODO("Not yet implemented")
    }

    override fun login(wxOpenId: String): Observable<User> {
        TODO("Not yet implemented")
    }
}