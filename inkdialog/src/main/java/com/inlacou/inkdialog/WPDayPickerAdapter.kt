package com.inlacou.inkdialog

import com.super_rabbit.wheel_picker.WheelAdapter

class DecimalPickerAdapter(val values: List<String>): WheelAdapter {

	//get item value based on item position in wheel
	override fun getValue(position: Int): String {
		return values[intoRange(position, values.size)]
	}

	private fun intoRange(position: Int, size: Int): Int {
		return when {
			position in 0 until size -> position
			position < 0 -> Math.abs((position%size)+size)%size
			position > 0 -> position%size
			else -> position
		}
	}

	//get item position based on item string value
	override fun getPosition(vale: String): Int {
		return values.indexOf(vale).let {
			if(it>0) it
			else 0
		}
	}

	//return a string with the approximate longest text width, for supporting WRAP_CONTENT //TODO check how this is done in code
	override fun getTextWithMaximumLength(): String {
		return values.longest()
	}

	//return the maximum index
	override fun getMaxIndex(): Int {
		return Int.MAX_VALUE
	}

	//return the minimum index
	override fun getMinIndex(): Int {
		return Int.MIN_VALUE
	}

	fun List<String>.longest(): String = sortedByDescending { it.length }.first()
}