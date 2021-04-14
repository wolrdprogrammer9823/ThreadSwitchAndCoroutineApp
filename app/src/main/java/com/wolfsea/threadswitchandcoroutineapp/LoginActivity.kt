package com.wolfsea.threadswitchandcoroutineapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wolfsea.threadswitchandcoroutineapp.usecase.AccountManagerImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Observable.create<String> {
            it.onNext("123456")
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("value","value:$it")
                }

        createObservable("123456").flatMap {
            createObservable(it.substring(1, 4).toInt())
        }.flatMap {
            createObservable(it.toString().plus("5678"))
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("value","flatMapValue:$it")
                }

        createObservable(123).map {
            Thread.sleep(2000)
            it * 10
        }.map {
            Thread.sleep(1000)
            it.toString().plus("123")
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("value","mapValue:$it")
                }
    }

    private fun clickLogin() {
        val accountManagerImpl = AccountManagerImpl()
        accountManagerImpl.requestWxOpenId()
                .flatMap {
                    accountManagerImpl.login(it)
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {


                })
    }

    private fun createObservableString(value: String): Observable<String> {
        return Observable.create {
            it.onNext(value)
        }
    }

    private fun createObservableInt(data: Int): Observable<Int> {
        return Observable.create {
            it.onNext(data.times(10))
        }
    }

    private fun <T> createObservable(value : T) : Observable<T> {
        return Observable.create {
            it.onNext(value)
        }
    }

}