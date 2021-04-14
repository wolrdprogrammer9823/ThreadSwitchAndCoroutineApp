package com.wolfsea.threadswitchandcoroutineapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import java.util.concurrent.Callable

class MainActivity : AppCompatActivity() {

    private lateinit var disposable : Disposable

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RxJava

        //多个subscribeOn只有第一个起作用,其他的无效.
        disposable = Observable.fromCallable(Callable
            {
                return@Callable 10
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())
            .subscribe {
                //Log.d(TAG, "data:$it")
            }

        //observeOn每个都有效
        Observable.fromArray(mutableListOf(1, 2, 3, 4, 5))
                  .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                    value -> value.sum()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                //result -> Log.d(TAG,"result:$result")
            }


        //just需要配合defer执行耗时操作
        Observable.defer { Observable.just(loadDataAsync()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                //result0 -> Log.d(TAG,"result0:$result0")
            }



        //======================================================================
        //coroutine
        //flow切换线程
        CoroutineScope(Job() + Dispatchers.Main).launch {
            flowOf(1,2,3).flowOn(Dispatchers.IO)
                .collect {
                    //value -> Log.d(TAG,"value->$value")
                }
        }

        //flowOn允许出现多次,每一个flowOn都有自己的作用域.
        CoroutineScope(Job() + Dispatchers.Main).launch {
            flowOf(1,2,3).flowOn(Dispatchers.IO)
                .map {
                    result -> result * 2
                }
                .flowOn(Dispatchers.Default)
                .collect {
                    //count -> Log.d(TAG,"count->$count")
                }
        }

        //走完第一个collect,之后才走第二个collect.
        /*CoroutineScope(Job() + Dispatchers.Main).launch {

            foo1().flowOn(Dispatchers.IO)
                .collect {
                    content -> Log.d(TAG,"content->$content")
                }

            flowOf(11).flowOn(Dispatchers.Default)
                .collect {
                    description -> Log.d(TAG,"description->$description")
                }
        }*/

        //开启两个协程,协程之间相互不影响.
        /*CoroutineScope(Job() + Dispatchers.Main).launch {

            launch {
                foo1().flowOn(Dispatchers.IO)
                    .collect {
                            content -> Log.d(TAG,"content->$content")
                    }
            }

            launch {

                flowOf(11).flowOn(Dispatchers.Default)
                    .collect {
                            description -> Log.d(TAG,"description->$description")
                    }
            }
        }*/

        //开启两个协程,协程之间相互不影响.使用launchIn形式
        CoroutineScope(Job() + Dispatchers.Main).launch {
            foo1().flowOn(Dispatchers.IO)
                .onEach {
                        content -> Log.d(TAG,"content->$content")
                }
                .launchIn(this)

            flowOf(11).flowOn(Dispatchers.Main)
                .onEach {
                        description -> Log.d(TAG,"description->$description")
                }
                .launchIn(this)
        }

        GlobalScope.launch {
            foo1().flowOn(Dispatchers.IO)
                .collect {
                        content -> Log.d(TAG,"content->$content")
                }
        }

        //执行并发操作
        GlobalScope.launch {
            val totalValue = concurrentSum()
            Log.d(TAG,"totalValue1->$totalValue")
        }

        CoroutineScope(Job() + Dispatchers.Main).launch {
            flow {
                emit(concurrentSum())
            }.flowOn(Dispatchers.IO)
                    .collect {
                        value ->  Log.d(TAG,"totalValue2->$value")
                    }
        }

        Observable.create<String> {
            "123456789"
        }.flatMap {
            it.run {
                Observable.create<Int> {
                    this.substring(1,5).toInt()
                }
            }
        }
           /* .flatMap {
            it.run {
                Observable.create<Int> {
                    this.times(10)
                }
            }
        }*/
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG,"value:$it")
            }
    }

    private fun loadDataAsync() : Int {
        Thread.sleep(3 * 1000)
        return 10
    }

    //创建flow
    private fun foo1() : Flow<Int> = flow {
        delay(2000)
        emit(10)
    }

    private suspend fun doSomethingOne() : Int {
        delay(3000)
        return 10
    }

    private suspend fun doSomethingTwo() : Int {
        delay(1500)
        return 15
    }

    //两个异步的任务
    private suspend fun concurrentSum() : Int = coroutineScope {

        val one = async {
            doSomethingOne()
        }

        val two = async {
            doSomethingTwo()
        }

        one.await() + two.await()
    }

    override fun onDestroy() {

        super.onDestroy()

        if (!disposable.isDisposed) {

            disposable.dispose()
        }
    }

    companion object {
        const val TAG = "data01"
    }

}