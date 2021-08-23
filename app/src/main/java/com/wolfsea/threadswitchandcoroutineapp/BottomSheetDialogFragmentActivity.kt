package com.wolfsea.threadswitchandcoroutineapp
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.wolfsea.threadswitchandcoroutineapp.databinding.ActivityBottomSheetDialogFragmentBinding
import com.wolfsea.threadswitchandcoroutineapp.dialog.DemoBSDFragment
import com.wolfsea.threadswitchandcoroutineapp.dialog.DemoDialogFragment
import kotlinx.android.synthetic.main.activity_bottom_sheet_dialog_fragment.*

class BottomSheetDialogFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_dialog_fragment)
        init()
    }

    private fun init() {

        bsd_fragment_btn.setOnClickListener {
            DemoBSDFragment.newInstance().show(supportFragmentManager, DemoBSDFragment::class.java.simpleName)
        }

        dia_fragment_btn.setOnClickListener {
            DemoDialogFragment.newInstance().show(supportFragmentManager, DemoBSDFragment::class.java.simpleName)
        }
    }
}