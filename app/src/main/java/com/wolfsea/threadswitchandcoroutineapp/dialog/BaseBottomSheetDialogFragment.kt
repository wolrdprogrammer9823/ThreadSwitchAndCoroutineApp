package com.wolfsea.threadswitchandcoroutineapp.dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wolfsea.threadswitchandcoroutineapp.R

/**
 *@desc  BottomSheetDialogFragment基类
 *@author liuliheng
 *@time 2021/8/19  21:47
 **/
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onStart() {
        super.onStart()
        val mView = dialog?.findViewById<View>(R.id.design_bottom_sheet)
        mView?.layoutParams?.height = setDialogFragmentMaxHeight()
        val bottomSheetBehavior = BottomSheetBehavior.from(mView!!)
        bottomSheetBehavior.peekHeight = setDialogFragmentHeight()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutId(), container, false)
        initView(view)
        return view
    }

    open fun setDialogFragmentMaxHeight() : Int = ViewGroup.LayoutParams.MATCH_PARENT

    open fun setDialogFragmentHeight() : Int = mContext?.resources?.getDimensionPixelSize(R.dimen.dp_300)!!

    abstract fun initView(view: View)

    abstract fun getLayoutId(): Int
}