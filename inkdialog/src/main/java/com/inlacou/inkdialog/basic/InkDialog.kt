package com.inlacou.inkdialog.basic

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.inlacou.inkdialog.R
import timber.log.Timber

open class InkDialog internal constructor(context: Context, open val model: InkDialogMdl)
	: AlertDialog(context, false, null) {

	open class Builder(val context: Context){

		var titleText: String = ""
			private set
		var contentText: String = ""
			private set
		var positiveButtonText: String = ""
			private set
		var neutralButtonText: String = ""
			private set
		var negativeButtonText: String = ""
			private set
		var onPositiveClick: ((InkDialog) -> Unit)? = null
			private set
		var onNeutralClick: ((InkDialog) -> Unit)? = null
			private set
		var onNegativeClick: ((InkDialog) -> Unit)? = null
			private set

		fun title(textResId: Int): Builder {
			return title(context.getString(textResId))
		}

		fun title(text: String): Builder {
			titleText = text
			return this
		}

		fun content(textResId: Int): Builder {
			return content(context.getString(textResId))
		}

		fun content(text: String): Builder {
			contentText = text
			return this
		}

		fun positiveButton(textResId: Int, onClick: (InkDialog) -> Unit): Builder {
			return positiveButton(context.getString(textResId), onClick)
		}

		fun positiveButton(text: String, onClick: (InkDialog) -> Unit): Builder {
			positiveButtonText = text
			onPositiveClick = onClick
			return this
		}

		fun neutralButton(textResId: Int, onClick: (InkDialog) -> Unit): Builder {
			return neutralButton(context.getString(textResId), onClick)
		}

		fun neutralButton(text: String, onClick: (InkDialog) -> Unit): Builder {
			neutralButtonText = text
			onNeutralClick = onClick
			return this
		}

		fun negativeButton(textResId: Int, onClick: (InkDialog) -> Unit): Builder {
			return negativeButton(context.getString(textResId), onClick)
		}

		fun negativeButton(text: String, onClick: (InkDialog) -> Unit): Builder {
			negativeButtonText = text
			onNegativeClick = onClick
			return this
		}

		open fun build(): InkDialog {
			return InkDialog(
				context = context, model = InkDialogMdl(
					title = titleText,
					content = contentText,
					positiveText = positiveButtonText,
					onPositive = onPositiveClick,
					neutralText = neutralButtonText,
					onNeutral = onNeutralClick,
					negativeText = negativeButtonText,
					onNegative = onNegativeClick
				)
			)
		}

		open fun show(){
			build().show()
		}
	}

	protected var viewBaseLayoutSurface: View? = null
	protected var tvTitle: TextView? = null
	protected var tvContent: TextView? = null
	protected var btnNegative: TextView? = null
	protected var btnNeutral: TextView? = null
	protected var btnPositive: TextView? = null

	private lateinit var controller: InkDialogCtrl

	override fun onCreate(savedInstanceState: Bundle?) {
		Timber.d("onCreate")
		super.onCreate(savedInstanceState)
		setContentView(R.layout.view_dialog)

		initialize()

		setListeners()

		populate()
	}

	protected open fun initialize() {
		Timber.d("initialize")
		controller = InkDialogCtrl(view = this, model = model)
		viewBaseLayoutSurface = findViewById(R.id.view_base_layout_surface)
		tvTitle = findViewById(R.id.title)
		tvContent = findViewById(R.id.content)
		btnNegative = findViewById(R.id.btn_negative)
		btnNeutral = findViewById(R.id.btn_neutral)
		btnPositive = findViewById(R.id.btn_positive)
	}

	protected open fun populate() {
		Timber.d("populate")
		model.apply {
			btnNegative?.setVisible(onNegative!=null)
			btnNeutral?.setVisible(onNeutral!=null)
			btnPositive?.setVisible(onPositive!=null)
			btnPositive?.text = positiveText
			btnNeutral?.text = neutralText
			btnNegative?.text = negativeText
			tvTitle?.setVisible(title!=null)
			tvTitle?.text = title
			tvContent?.setVisible(content!=null)
			tvContent?.text = content
		}
	}

	protected open fun setListeners() {
		Timber.d("setListeners")
		btnPositive?.setOnClickListener { controller.onPositive(this) }
		btnNeutral?.setOnClickListener { controller.onNeutral(this) }
		btnNegative?.setOnClickListener { controller.onNegative(this) }
	}

	fun View?.setVisible(visible: Boolean, holdSpaceOnDissapear: Boolean = false) {
		if (this == null) return
		if(visible){
			this.visibility = View.VISIBLE
		}else{
			if(holdSpaceOnDissapear){
				this.visibility = View.INVISIBLE
			}else{
				this.visibility = View.GONE
			}
		}
	}
}