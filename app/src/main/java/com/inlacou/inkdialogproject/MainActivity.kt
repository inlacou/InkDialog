package com.inlacou.inkdialogproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import com.inlacou.inkdialog.DecimalPickerAdapter
import com.inlacou.inkdialog.basic.InkDialog
import com.inlacou.inkdialog.wheel.WheelDialog
import com.super_rabbit.wheel_picker.OnValueChangeListener
import com.super_rabbit.wheel_picker.WheelPicker
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {

    var np2: WheelPicker? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
        np2 = np
		btn_normal_dialog?.setOnClickListener {
            InkDialog.Builder(this)
                .title("Title")
                .content("Lorem ipsum dolor sit amet")
                .positiveButton("Accept") { Toast.makeText(this, "hello!", Toast.LENGTH_LONG).show(); it.cancel() }
                .neutralButton("Cancel") { it.cancel() }
                .build().show()
		}
		btn_wheel_dialog?.setOnClickListener {
            WheelDialog.Builder(this, (0..30).map { it.toString() }, "9") { Timber.d("value 1 selected: $it") }
                .secondWheel(".", listOf("00", "25", "50", "75", "-75", "-50", "-25"), "50") { Timber.d("value 2 selected: $it") }
                .title("Right Eye")
                .positiveButton("Apply") { Toast.makeText(this, "hello!", Toast.LENGTH_LONG).show(); it.cancel() }
                .neutralButton("Cancel") { it.cancel(); configure() }
                .build().show()
		}
	}

    fun configure(){
        np2!!.setUnselectedTextColor(R.color.inkdialog_black_text)
        np2!!.setSelectedTextColor(R.color.inkdialog_black_text)
        np2!!.setAdapter(DecimalPickerAdapter((0..100).map { it.toString() }))
        np2!!.setOnValueChangeListener(object : OnValueChangeListener {
            override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
                Timber.d("selected: $newVal")
            }
        })
        np2!!.setSelectorRoundedWrapPreferred(false)
        np2!!.reset()
    }
}
