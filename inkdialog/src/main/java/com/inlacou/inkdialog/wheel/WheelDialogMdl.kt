package com.inlacou.inkdialog.wheel

import com.inlacou.inkdialog.basic.InkDialog
import com.inlacou.inkdialog.basic.InkDialogMdl

/* Medidas de ruleta
	-20 a 20
	-75 -50 -25 00 25 50 75
	-8 a 8
	0 a 4
	0 a 30
	0 a 10
	Nasal / Temporal
	Superior / Inferior
	Derecho / Izquierdo
	0 a 25
	Regular, Symmetric, Asymmetric
	1,49 , 1,53, 1,56, 1,59, 1,60, 1,67, 1,70,  1,74, 1,80, 1,90
	ambas, sólo derecha, sólo izquierda
	BCO, end, AR, Clean, AR blue cut, Otro
	No, fotocromático gris, fotocromático marrón, fotocromático verde
	No, si
*/

open class WheelDialogMdl(
	title: String? = null,
	positiveText: String? = null,
	neutralText: String? = null,
	negativeText: String? = null,
	onPositive: ((InkDialog) -> Unit)? = null,
	onNeutral: ((InkDialog) -> Unit)? = null,
	onNegative: ((InkDialog) -> Unit)? = null,
	val firstValuesList: List<String>,
	val defaultValueFirst: String? = null,
	val onFirstValueChange: ((String) -> Unit),
	val separator: String? = null,
	val secondValuesList: List<String>? = null,
	val defaultValueSecond: String? = null,
	val onSecondValueChange: ((String) -> Unit)? = null
): InkDialogMdl(title, null, positiveText, neutralText, negativeText, onPositive, onNeutral, onNegative)