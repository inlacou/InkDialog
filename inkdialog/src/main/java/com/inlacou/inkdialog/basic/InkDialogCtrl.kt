package com.inlacou.inkdialog.basic

open class InkDialogCtrl(open val view: InkDialog, open val model: InkDialogMdl) {

	fun onPositive(inkDialog: InkDialog) {
		model.onPositive?.invoke(inkDialog)
	}

	fun onNeutral(inkDialog: InkDialog) {
		model.onNeutral?.invoke(inkDialog)
	}

	fun onNegative(inkDialog: InkDialog) {
		model.onNegative?.invoke(inkDialog)
	}
}