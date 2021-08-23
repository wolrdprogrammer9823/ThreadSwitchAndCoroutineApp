package com.wolfsea.threadswitchandcoroutineapp.dialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.wolfsea.threadswitchandcoroutineapp.R

/**
 *@desc  Demo BottomSheetDialogFragment
 *@author liuliheng
 *@time 2021/8/19  22:11
 **/
class DemoBSDFragment : BaseBottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }


    override fun initView(view: View) {

    }

    override fun getLayoutId(): Int = R.layout.demo_layout

    override fun setDialogFragmentMaxHeight(): Int = mContext?.resources?.getDimensionPixelSize(R.dimen.dp_600)!!

    companion object {
        fun newInstance() : DemoBSDFragment {
            return DemoBSDFragment()
        }
    }
}