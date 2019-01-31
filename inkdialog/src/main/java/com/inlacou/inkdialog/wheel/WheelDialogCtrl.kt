package com.inlacou.inkdialog.wheel

import com.inlacou.inkdialog.basic.InkDialogCtrl

class WheelDialogCtrl(override val view: WheelDialog, override val model: WheelDialogMdl): InkDialogCtrl(view, model){

	fun onFirstValueSelected(newVal: String) {
		model.onFirstValueChange.invoke(newVal)
	}

	fun onSecondValueSelected(newVal: String) {
		model.onSecondValueChange?.invoke(newVal)
	}
}