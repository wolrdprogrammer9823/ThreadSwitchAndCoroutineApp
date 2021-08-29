package com.wolfsea.threadswitchandcoroutineapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wolfsea.threadswitchandcoroutineapp.extendmethod.startActivity
import com.wolfsea.threadswitchandcoroutineapp.flowdemo.Util
import kotlinx.android.synthetic.main.activity_frame.*
import kotlinx.coroutines.*

class FrameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        initEvent()

        method2()
    }

    /**
     *@desc 初始化事件
     *@author:liuliheng
     *@time: 2021/8/7 16:09
    **/
    private fun initEvent() {

        to_login.setOnClickListener {

           startActivity<LoginActivity>()
        }

        to_main.setOnClickListener {

            startActivity<MainActivity>()
        }

        to_table_layout.setOnClickListener {

            startActivity<TableLayoutActivity>()
        }

        to_bsd_fragment.setOnClickListener {

            startActivity<BottomSheetDialogFragmentActivity>()
        }

        to_grid_layout.setOnClickListener {

            startActivity<GridLayoutActivity>()
        }

        to_define_grid_layout.setOnClickListener {

            startActivity<DefineGridLayoutActivity>()
        }
    }

    private fun method1() {
        CoroutineScope(Dispatchers.Main).launch {

            val dataSource = createDataSet()

            val result = async(Dispatchers.Default) {

                val dataMap = mutableMapOf<String, MutableList<String>>()

                for (value in dataSource) {

                    val v1 = Util.cutStr(value)
                    val v2 = Util.getSubStr(value)

                    val keyExits = dataMap[v1] != null
                    if (keyExits) {
                        if (v2 != "-1") {

                            val dataList = dataMap[v1]
                            dataList?.add(v2)
                        }
                    } else {
                        if (v2 != "-1") {

                            val dataList = mutableListOf<String>()
                            dataList.add(v2)
                            dataMap[v1] = dataList
                        }
                    }
                }

                dataMap
            }

            val dataMap = result.await()

            for ((key, value) in dataMap) {

                Log.d("value", "key:$key,value:${value.size}")
            }
        }
    }

    private fun method2() {
        CoroutineScope(Dispatchers.Main).launch {

            val dataSource = createDataSet()
            val dataMap = getDataMap(dataSource)
            for ((key, value) in dataMap) {

                Log.d("value", "key:$key,value:${value.size}")
            }
        }
    }


    private suspend fun createDataSet(): MutableList<String> = withContext(Dispatchers.Default) {
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
        dataSource
    }

    private suspend fun getDataMap(dataSource: MutableList<String>): MutableMap<String, MutableList<String>> =
        withContext(Dispatchers.Default) {

            val dataMap = mutableMapOf<String, MutableList<String>>()

            for (value in dataSource) {

                val v1 = Util.cutStr(value)
                val v2 = Util.getSubStr(value)

                val keyExits = dataMap[v1] != null
                if (keyExits) {
                    if (v2 != "-1") {

                        val dataList = dataMap[v1]
                        dataList?.add(v2)
                    }
                } else {
                    if (v2 != "-1") {

                        val dataList = mutableListOf<String>()
                        dataList.add(v2)
                        dataMap[v1] = dataList
                    }
                }
            }

            return@withContext dataMap
        }
}