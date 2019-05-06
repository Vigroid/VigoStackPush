package com.liulishuo.vigostackpush.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.liulishuo.vigostackpush.R
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity : Activity() {
    lateinit var dialog1: Dialog
    lateinit var dialog2: Dialog
    lateinit var dialog3: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        createDialog1()
        createDialog2()
        createDialog3()
        btn_show_dialogs.setOnClickListener {
            dialog1.show()
            dialog2.show()
            dialog3.show()
        }
    }

    private fun createDialog1() {
        dialog1 = AlertDialog.Builder(this)
                .setMessage("wtf1").setPositiveButton("pos") { dialog, _ -> dialog.dismiss() }
                .setNegativeButton("neg") { dialog, _ -> dialog.dismiss() }
                .create()

    }

    private fun createDialog2() {
        dialog2 = AlertDialog.Builder(this)
                .setTitle("wtf")
                .setMessage("wtf2").setPositiveButton("pos") { dialog, _ -> dialog.dismiss() }
                .setNegativeButton("neg") { dialog, _ -> dialog.dismiss() }
                .create()
    }

    private fun createDialog3() {
        dialog3 = AlertDialog.Builder(this)
                .setMessage("wtf3").setPositiveButton("pos") { dialog, _ -> dialog.dismiss() }
                .setNegativeButton("neg") { dialog, _ -> dialog.dismiss() }
                .create()
    }
}