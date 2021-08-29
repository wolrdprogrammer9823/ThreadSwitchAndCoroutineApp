package com.wolfsea.threadswitchandcoroutineapp.defineview
import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.DragEvent
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import androidx.appcompat.widget.AppCompatTextView
import kotlin.properties.Delegates

/**
 *@desc  自定义可拖动GridLayout
 *@author liuliheng
 *@time 2021/8/29  13:11
 **/
class DragGridLayout(context: Context, attributeSet: AttributeSet) : GridLayout(context, attributeSet) {

    private var resId by Delegates.notNull<Int>()
    private var magins by Delegates.notNull<Int>()
    private var padding by Delegates.notNull<Int>()
    private var scale by Delegates.notNull<Float>()

    private var ableDrag = false
    private var dragView: View? = null
    private lateinit var rects: Array<Rect?>

    var mOnItemClickListener: OnItemClickListener? = null

    init {
        init(context, attributeSet)
    }

    /*
    * 添加item
    * */
    fun addItem(item: String) {
        val textView = getNextTextView()
        textView.text = item
        this.addView(textView)
    }

    /*
    * 设置数据集
    * */
    fun setItems(items: MutableList<String>) {
        for (item in items) {
            addItem(item)
        }
    }

    /*
    * 创建AppCompatTextView
    * */
    private fun getNextTextView(): AppCompatTextView {

        val mLayoutParams = LayoutParams()
        mLayoutParams.width = resources.displayMetrics.widthPixels.div(4) - magins.times(2)
        mLayoutParams.height = LayoutParams.WRAP_CONTENT
        mLayoutParams.setMargins(magins, magins, magins, magins)

        return AppCompatTextView(context).apply {
            layoutParams = mLayoutParams
            setBackgroundResource(resId)
            gravity = Gravity.CENTER
            setPadding(padding, padding, padding, padding)
            setOnLongClickListener(if (ableDrag) onLongClickListener else null)
            setOnClickListener {
                mOnItemClickListener?.onItemClick(it as AppCompatTextView)
            }
        }
    }

    /*
    * 初始化方法
    * */
    private fun init(context: Context, attributeSet: AttributeSet) {
        columnCount = 4
        layoutTransition = LayoutTransition()
        scale = context.resources.displayMetrics.density
    }

    /*
    * 初始化view矩形
    * */
    private fun initRect() {
        rects = arrayOfNulls(childCount)
        for (i in 0 until childCount) {
            val children = getChildAt(i)
            rects[i] = Rect(children.left, children.top, children.right, children.bottom)
        }
    }

    /*
    * 获取手指所在区域对应的索引
    * */
    private fun getTouchIndex(event: DragEvent): Int {
        for (i in rects.indices) {
            val rect = rects[i]
            if (rect?.contains(event.x.toInt(), event.y.toInt())!!) {
                return i
            }
        }
        return -1
    }

    /*
    * 设置是否可以拖拽
    * */
    fun setDragEnabled(ableDrag: Boolean) {
        this.ableDrag = ableDrag
        setOnDragListener(if (ableDrag) onDragListener else null)
    }

    /*
    * 设置资源值
    * */
    fun setResource(resourceId: Int, margin: Int, padding: Int) {
        this.resId = resourceId
        this.magins = margin.times(scale).plus(0.5F).toInt()
        this.padding = padding.times(scale).plus(0.5f).toInt()
    }

    /*
    * 长按监听
    * */
    private val onLongClickListener = OnLongClickListener { view ->
        dragView = view
        view?.startDragAndDrop(null, DragShadowBuilder(view), null, 0)
        view?.isEnabled = false
        false
    }

    /*
    * 拖拽监听
    * */
    private val onDragListener = OnDragListener { _,event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                //当拖拽事件发生时,给每个子控件创建出对应的矩形
                initRect()
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                //当手指移动时,判断进入了哪个手指的区域,并返回所在区域的索引
                val touchIndex = getTouchIndex(event)
                val condition =
                    touchIndex > -1 && dragView != null && dragView != getChildAt(touchIndex)
                if (condition) {
                    //先把拖拽的view从当前位置移除,再添加到touchIndex上
                    this.removeView(dragView)
                    this.addView(dragView, touchIndex)
                }
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                dragView?.isEnabled = true
            }

            else -> {}
        }
        true
    }

    /*
    * item监听回调
    * */
    interface OnItemClickListener {
        fun onItemClick(textView: AppCompatTextView)
    }

}