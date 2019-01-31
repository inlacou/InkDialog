package com.inlacou.inkdialog.basic

open class InkDialogMdl(
	val title: String? = null,
	val content: String? = null,
	val positiveText: String? = null,
	val neutralText: String? = null,
	val negativeText: String? = null,
	val onPositive: ((InkDialog) -> Unit)? = null,
	val onNeutral: ((InkDialog) -> Unit)? = null,
	val onNegative: ((InkDialog) -> Unit)? = null
)