package com.wolfsea.threadswitchandcoroutineapp.dialog
import android.os.Bundle
import android.view.View
import com.wolfsea.threadswitchandcoroutineapp.R
import kotlinx.android.synthetic.main.dialog_fragment_layout.view.*

/**
 *@desc  Demo DialogFragment
 *@author liuliheng
 *@time 2021/8/19  22:11
 **/
class DemoDialogFragment : BaseDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun initView(view: View) {

        view.dia_btn_one.setOnClickListener {
            DemoNewDialogFragment.newInstance()
                .show(childFragmentManager, DemoNewDialogFragment::class.java.simpleName)
        }
    }

    override fun getLayoutId(): Int = R.layout.dialog_fragment_layout

    companion object {
        fun newInstance() : DemoDialogFragment {
            return DemoDialogFragment()
        }
    }
}