package com.wolfsea.threadswitchandcoroutineapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import com.wolfsea.threadswitchandcoroutineapp.defineview.DragGridLayout
import kotlinx.android.synthetic.main.activity_define_grid_layout.*

class DefineGridLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_define_grid_layout)
        initCustom()
        initEvent()
    }

    private fun initCustom() {

        main_drag_layout.setResource(R.drawable.shape_text_bg_selector, 10, 5)
        main_drag_layout.setDragEnabled(true)
        val mainItems = mutableListOf<String>()
        mainItems.add("推荐")
        mainItems.add("本地事")
        mainItems.add("本地人")
        mainItems.add("社区")
        mainItems.add("图集")
        mainItems.add("要闻")
        mainItems.add("热点")
        mainItems.add("旅游")
        mainItems.add("健康")
        main_drag_layout.setItems(mainItems)

        bottom_drag_layout.setResource(R.drawable.shape_text_bg_selector, 10, 5)
        bottom_drag_layout.setDragEnabled(false)
        val bottomItems = mutableListOf<String>()
        bottomItems.add("家居");
        bottomItems.add("奇石");
        bottomItems.add("螺蛳粉");
        bottomItems.add("情感");
        bottomItems.add("文化");
        bottomItems.add("体育");
        bottomItems.add("汽车");
        bottomItems.add("本地号");
        bottomItems.add("爆料");
        bottomItems.add("时政");
        bottomItems.add("美女");
        bottomItems.add("公益");
        bottomItems.add("公民榜样");
        bottomItems.add("亲子");
        bottomItems.add("社会");
        bottomItems.add("舌尖柳州");
        bottomItems.add("开心一刻");
        bottomItems.add("居柳州");
        bottom_drag_layout.setItems(bottomItems)
    }

    private fun initEvent() {
        main_drag_layout.mOnItemClickListener = object : DragGridLayout.OnItemClickListener {
            override fun onItemClick(textView: AppCompatTextView) {
                main_drag_layout.removeView(textView)
                bottom_drag_layout.addItem(textView.text.toString())
            }
        }

        bottom_drag_layout.mOnItemClickListener = object : DragGridLayout.OnItemClickListener {
            override fun onItemClick(textView: AppCompatTextView) {
                bottom_drag_layout.removeView(textView)
                main_drag_layout.addItem(textView.text.toString())
            }
        }
    }
}