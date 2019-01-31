package com.inlacou.inkdialog.wheel

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import com.inlacou.inkdialog.DecimalPickerAdapter
import com.inlacou.inkdialog.R
import com.inlacou.inkdialog.basic.InkDialog
import com.super_rabbit.wheel_picker.OnValueChangeListener
import com.super_rabbit.wheel_picker.WheelPicker
import timber.log.Timber

class WheelDialog constructor(context: Context, override val model: WheelDialogMdl)
	: InkDialog(context, model) {

	class Builder(context: Context, val firstValuesList: List<String>, val defaultValueFirst: String? = null, val onFirstValueSelected: (String) -> Unit): InkDialog.Builder(context) {

		protected var separator: String? = null
		protected var defaultValueSecond: String? = null
		var onSecondValueSelected: ((String) -> Unit)? = null
			private set

		var secondValuesList: List<String>? = null
			private set

		/**
		 * First element on the list will be the default
		 */
		fun secondWheel(separator: String? = null, secondValuesList: List<String>, defaultValueSecond: String? = null, onSecondValueSelected: ((String) -> Unit)): InkDialog.Builder {
			this.separator = separator
			this.secondValuesList = secondValuesList
			this.defaultValueSecond = defaultValueSecond
			this.onSecondValueSelected = onSecondValueSelected
			return this
		}

		override fun build(): WheelDialog {
			return WheelDialog(
				context = context, model = WheelDialogMdl(
					title = titleText,
					positiveText = positiveButtonText,
					onPositive = onPositiveClick,
					neutralText = neutralButtonText,
					onNeutral = onNeutralClick,
					negativeText = negativeButtonText,
					onNegative = onNegativeClick,
					firstValuesList = firstValuesList,
					defaultValueFirst = defaultValueFirst,
					onFirstValueChange = onFirstValueSelected,
					separator = separator,
					secondValuesList = secondValuesList,
					defaultValueSecond = defaultValueSecond,
					onSecondValueChange = onSecondValueSelected
				)
			)
		}
	}

	protected var np1: WheelPicker? = null
	protected var np2: WheelPicker? = null
	protected var tvSeparator: TextView? = null

	private lateinit var controller: WheelDialogCtrl

	override fun onCreate(savedInstanceState: Bundle?) {
		Timber.d("onCreate")
		super.onCreate(savedInstanceState)
		setContentView(R.layout.view_wheel_dialog)

		initialize()

		setListeners()

		populate()
	}

	override fun initialize() {
		super.initialize()
		Timber.d("initialize")
		controller = WheelDialogCtrl(view = this, model = model)
		np1 = findViewById(R.id.np_1)
		np2 = findViewById(R.id.np_2)
		tvSeparator = findViewById(R.id.tv_separator)
	}

	override fun populate() {
		super.populate()
		Timber.d("populate")
		model.apply {
			np1?.setUnselectedTextColor(R.color.inkdialog_black_text)
			np1?.setSelectedTextColor(R.color.inkdialog_black_text)
			np1?.setAdapter(DecimalPickerAdapter(firstValuesList))
			np1?.setOnValueChangeListener(object : OnValueChangeListener {
				override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
					controller.onFirstValueSelected(newVal)
				}
			})
			defaultValueFirst?.let { np1?.scrollToValue(it) }

			tvSeparator?.text = separator
			tvSeparator?.setVisible(separator!=null)

			secondValuesList.let { valuesList ->
				if(valuesList!=null) {
					np2?.setUnselectedTextColor(R.color.inkdialog_black_text)
					np2?.setSelectedTextColor(R.color.inkdialog_black_text)
					np2?.setAdapter(DecimalPickerAdapter(valuesList))
					np2?.setOnValueChangeListener(object : OnValueChangeListener {
						override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
							controller.onSecondValueSelected(newVal)
						}
					})
					defaultValueSecond?.let { np2?.scrollToValue(it) }
				}
				np2.setVisible(valuesList!=null)
			}
		}
	}

	override fun setListeners() {
		super.setListeners()
		Timber.d("setListeners")
	}
}