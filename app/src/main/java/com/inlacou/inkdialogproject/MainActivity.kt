package com.inlacou.inkdialogproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.inlacou.inkdialog.basic.InkDialog
import com.inlacou.inkdialog.wheel.WheelDialog
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		btn_normal_dialog?.setOnClickListener {
            InkDialog.Builder(this)
                .title("Title")
                .content("Lorem ipsum dolor sit amet")
                .positiveButton("Accept") { Toast.makeText(this, "hello!", Toast.LENGTH_LONG).show(); it.cancel() }
                .neutralButton("Cancel") { it.cancel() }
                .build().show()
		}
		btn_wheel_dialog?.setOnClickListener {
            WheelDialog.Builder(this, (0..30).map { it.toString() }) { Timber.d("value 1 selected: $it") }
                .secondWheel(".", listOf("00", "25", "50", "75", "-75", "-50", "-25")) { Timber.d("value 2 selected: $it") }
                .title("Right Eye")
                .positiveButton("Apply") { Toast.makeText(this, "hello!", Toast.LENGTH_LONG).show(); it.cancel() }
                .neutralButton("Cancel") { it.cancel() }
                .build().show()
		}
	}
}
