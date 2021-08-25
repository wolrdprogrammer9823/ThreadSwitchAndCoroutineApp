package com.wolfsea.threadswitchandcoroutineapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wolfsea.threadswitchandcoroutineapp.usecase.BinObj
import kotlinx.android.synthetic.main.activity_table_layout.*

class TableLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_layout)

        table_view.setData(BinObj())
    }
}