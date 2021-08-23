package com.wolfsea.threadswitchandcoroutineapp.dialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wolfsea.threadswitchandcoroutineapp.R

/**
 *@desc  DialogFragment基类
 *@author liuliheng
 *@time 2021/8/19  21:47
 **/
abstract class BaseDialogFragment : DialogFragment() {

    var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
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

    override fun onResume() {
        super.onResume()
        val layoutParams = dialog?.window?.attributes
        layoutParams?.gravity = Gravity.BOTTOM
        layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams?.height = setDialogFragmentHeight()
        dialog?.window?.attributes = layoutParams
    }

    open fun setDialogFragmentHeight() : Int = mContext?.resources?.getDimensionPixelSize(R.dimen.dp_300)!!

    abstract fun initView(view: View)

    abstract fun getLayoutId(): Int
}