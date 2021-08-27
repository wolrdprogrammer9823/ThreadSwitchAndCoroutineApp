package com.wolfsea.threadswitchandcoroutineapp.defineview
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.widget.AppCompatTextView
import com.wolfsea.threadswitchandcoroutineapp.R
import com.wolfsea.threadswitchandcoroutineapp.usecase.BinObj
import com.wolfsea.threadswitchandcoroutineapp.usecase.ColumnData
import java.text.DecimalFormat
import kotlin.properties.Delegates

/**
 *@desc
 *@author liuliheng
 *@time 2021/8/18  0:20
 **/
class LayerTableInfoView(context: Context, attributeSet: AttributeSet) : RelativeLayout(context, attributeSet) {

    private var headBg by Delegates.notNull<Int>()
    private var borderWidth by Delegates.notNull<Float>()
    private var headerHeight by Delegates.notNull<Float>()
    private var contentHeight by Delegates.notNull<Float>()

    private lateinit var mContext: Context
    private lateinit var rectPaint: Paint
    private lateinit var linePaint: Paint

    private var dataSet: MutableList<ColumnData>? = null

    init {
        init(context, attributeSet)
    }

    override fun dispatchDraw(canvas: Canvas?) {

        super.dispatchDraw(canvas)

        if (dataSet == null || dataSet?.size == 0) {

            return
        }

        val col = 3
        val row = dataSet?.size
        val cellWidth = width.div(col)

        canvas?.apply {

            save()

            /*
             * 绘制header的边框
             * */
            drawRect(
                borderWidth.div(2),
                borderWidth.div(2),
                width.toFloat() - borderWidth.div(2),
                headerHeight - borderWidth.div(2),
                rectPaint
            )

            /*
             * 画分割线(竖线)
             * */
            for (i in 1 until col) {
                val startX = cellWidth.times(i) + borderWidth.div(2)
                drawRect(
                    startX,
                    0F,
                    startX,
                    headerHeight,
                    linePaint
                )
            }

            for (i in 0..col) {
                val startX = cellWidth.times(i) + borderWidth.div(2)
                //最后一列的线条绘制需要加减线宽的一半值以及0.5F.主要是因为canvas绘制线条是中间向两边绘制的,渲染时不
                //支持小于0.5像素.
                val left = if (i == col) startX - borderWidth.div(2) - 0.5F else startX
                val right = if (i == col) startX + borderWidth.div(2) + 0.5F else startX
                drawRect(
                    left,
                    headerHeight,
                    right,
                    height.toFloat(),
                    linePaint
                )
            }

            /*
             * 画分割线(横线)
             * */
            for (j in 1..row!!) {
                val startY = contentHeight.times(j) + headerHeight + borderWidth.div(2)
                //最后一行的线条需要加减线宽以及1.0F.主要是因为canvas绘制线条是中间向两边绘制的,渲染时不支持
                //小于0.5像素和更正线条粗细误差.
                val top = if (j == row) startY - borderWidth - 1.0F else startY
                val bottom = if (j == row) startY + borderWidth + 1.0F else startY
                drawRect(
                    0F,
                    top,
                    width.toFloat(),
                    bottom,
                    linePaint)
            }

            restore()
        }
    }


    private fun init(context: Context, attributeSet: AttributeSet) {

        mContext = context

        context.obtainStyledAttributes(attributeSet, R.styleable.LayerInfoTableView).apply {

            headBg = getColor(
                R.styleable.LayerInfoTableView_head_bg,
                resources.getColor(R.color.teal_200)
            )

            borderWidth = getDimension(
                R.styleable.LayerInfoTableView_border,
                1F)

            headerHeight = getDimension(
                R.styleable.LayerInfoTableView_head_height,
                100F)

            contentHeight = getDimension(
                R.styleable.LayerInfoTableView_content_height,
                100F)

            recycle()
        }

        rectPaint = Paint()
        rectPaint.apply {
            strokeWidth = borderWidth
            style = Paint.Style.STROKE
            isDither = true
            isAntiAlias = true
            color = resources.getColor(R.color.purple_500)
        }

        linePaint = Paint()
        linePaint.apply {
            strokeWidth = borderWidth
            style = Paint.Style.FILL_AND_STROKE
            isDither = true
            isAntiAlias = true
            color = resources.getColor(R.color.purple_500)
        }

        /*
        * 初始化表头
        * */
        LayoutInflater.from(context).inflate(R.layout.table_item, this)
        val tableRow = makeTableRows(true, "名称", "UL(kps)", "DL(kps)")
        val tableHeader = this.findViewById<TableLayout>(R.id.table_head)
        tableHeader.setBackgroundColor(headBg)
        tableHeader.isStretchAllColumns = true
        tableHeader.addView(tableRow, TableLayout.LayoutParams(WARP_CONTENT, MATCH_PARENT))
    }

    /**
     *@desc  创建表格行
     *@author:liuliheng
     *@time: 2021/8/24 22:01
    **/
    private fun makeTableRows(mIsHead: Boolean, vararg otherRows: String): TableRow {
        val tableRow = TableRow(mContext)
        val cellWidth = width.div(otherRows.size)
        for (i in 0.until(otherRows.size)) {
            val cellView = makeCellView(mIsHead, cellWidth, otherRows[i])
            tableRow.addView(cellView, i)
        }
        return tableRow
    }

    /**
     *@desc  创建表格的item
     *@author:liuliheng
     *@time: 2021/8/24 21:49
    **/
    private fun makeCellView(mIsHead: Boolean, cellWidth: Int, name: String): AppCompatTextView {
        val cellView = AppCompatTextView(mContext)
        cellView.apply {
             isSingleLine = true
             isSelected = true
             text = name
             width = cellWidth
             height = if (mIsHead) headerHeight.toInt() else contentHeight.toInt()
             setTextColor(Color.BLACK)
             gravity = Gravity.CENTER
             ellipsize = TextUtils.TruncateAt.MARQUEE
             if (mIsHead) {
                setBackgroundColor(headBg)
             }
        }
        return cellView
    }

    /**
     *@desc 初始化表格数据
     *@author:liuliheng
     *@time: 2021/8/24 22:46
    **/
    private fun initTableContent(columnDataSet: MutableList<ColumnData>?) {

        LayoutInflater.from(mContext).inflate(R.layout.table_item, this)

        val tableContent = this.findViewById<TableLayout>(R.id.table_content)
        tableContent.isStretchAllColumns = true
        tableContent.removeAllViews()

        for (columnData in columnDataSet!!) {
            val tableRow = makeTableRows(
                false,
                columnData.name,
                float2Str(columnData.dlRate),
                float2Str(columnData.ulRate)
            )
            tableContent.addView(tableRow, TableLayout.LayoutParams(WARP_CONTENT, MATCH_PARENT))
        }
    }

    /**
     *@desc 设置数据
     *@author:liuliheng
     *@time: 2021/8/24 22:47
    **/
    fun setData(binObj: BinObj?) {

        dataSet = parseTableContent(binObj)
        initTableContent(dataSet)
    }

    /**
     *@desc 解析表格内容
     *@author:liuliheng
     *@time: 2021/8/24 22:46
    **/
    private fun parseTableContent(binObj: BinObj?): MutableList<ColumnData> {

        val columnData = mutableListOf<ColumnData>()

        if (binObj == null) {
            return columnData
        }

        val dlsData = binObj.getDlsThroughput()
        val ulsData = binObj.getUlsThroughput()
        columnData.add(ColumnData("SDAP", dlsData, ulsData))

        val dlData = binObj.getDlThroughput()
        val ulData = binObj.getUlThroughput()
        columnData.add(ColumnData("PDCP", dlData, ulData))

        val macDlData = binObj.getMacThroughput()
        val macUlData = binObj.getMacThroughput()
        columnData.add(ColumnData("MAC", macDlData, macUlData))

        val phyDlData = binObj.getPhyThroughput()
        val phyUlData = binObj.getPhyThroughput()
        columnData.add(ColumnData("PHY", phyDlData, phyUlData))

        val rlcDlData = binObj.getDlThroughput()
        val rlcUlData = binObj.getUlThroughput()
        columnData.add(ColumnData("RLC", rlcDlData, rlcUlData))

        return columnData
    }

    companion object {

        const val WARP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT
        const val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT

        fun float2Str(f: Float): String {
            val decimalFormat = DecimalFormat("#.00")
            return decimalFormat.format(f)
        }
    }

}