package com.jroviraa.android.materialdesignprogressbutton


import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton


class MaterialDesignProgressButton : MaterialButton {
    // config
    private val paddingProgress = 30
    private val strokeWidthProgress = 10

    // internal variables
    private var isLoading = false
    private var progressDrawable: ProgressDrawable? = null
    private var canvas: Canvas? = null

    // save button state
    private var buttonText = ""
    private var buttonIcon: Drawable? = null

    constructor(context: Context?) : super(context!!) {
        saveButtonState()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        saveButtonState()
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        saveButtonState()
    }

    private fun saveButtonState() {
        buttonText = text.toString()
        buttonIcon = icon
    }

    // override MaterialButton.onDraw
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.canvas = canvas
        updateButton()
    }

    // override MaterialButton.setText
    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)

        if (!isLoading) {
            this.buttonText = text.toString()
        }
    }

    // public functions
    fun showLoading() {
        setLoading(true)
    }

    fun hideLoading() {
        setLoading(false)
    }

    // internal functions
    private fun drawIndeterminateProgress(canvas: Canvas?) {
        if (progressDrawable == null) {
            val offset = (width - height) / 2

            progressDrawable = ProgressDrawable(currentTextColor, strokeWidthProgress.toFloat())
            val left = offset + paddingProgress
            val right = width - offset - paddingProgress
            val bottom = height - paddingProgress
            val top = paddingProgress
            progressDrawable!!.setBounds(left, top, right, bottom)
            progressDrawable!!.callback = this
            progressDrawable!!.start()
        } else {
            progressDrawable!!.draw(canvas!!)
        }
    }

    private fun setLoading(loading: Boolean) {
        isLoading = loading
        updateButton()
    }

    private fun updateButton() {
        if (isLoading) {
            drawIndeterminateProgress(canvas)
            text = ""
            icon = null
        } else {
            if (buttonText.isNotEmpty()) text = buttonText
            if (buttonIcon != null) icon = buttonIcon
        }
    }
}