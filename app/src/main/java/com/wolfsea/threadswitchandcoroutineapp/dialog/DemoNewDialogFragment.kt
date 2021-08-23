package com.wolfsea.threadswitchandcoroutineapp.dialog
import android.os.Bundle
import android.view.View
import com.wolfsea.threadswitchandcoroutineapp.R

/**
 *@desc  New DialogFragment
 *@author liuliheng
 *@time 2021/8/19  22:11
 **/
class DemoNewDialogFragment : BaseDialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onResume() {
        super.onResume()
        //dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        //移除动画,防止闪屏.
        dialog?.window?.setWindowAnimations(-1)
    }

    override fun initView(view: View) {


    }

    override fun getLayoutId(): Int = R.layout.new_dialog_fragment_layout

    override fun setDialogFragmentHeight(): Int = mContext?.resources!!.getDimension(R.dimen.dp_100).toInt()

    companion object {
        fun newInstance() : DemoNewDialogFragment {
            return DemoNewDialogFragment()
        }
    }
}