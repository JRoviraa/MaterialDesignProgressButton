package com.jroviraa.android.materialdesignprogressbutton


import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import com.google.android.material.button.MaterialButton


class MaterialDesignProgressButton : MaterialButton {
    // config
    private val mPaddingProgress = 30
    private val mStrokeWidth = 10

    // internal variables
    private var isLoading = false
    private var mAnimatedDrawable: ProgressDrawable? = null
    private var mCanvas: Canvas? = null

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
        mCanvas = canvas
        updateButton()
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
        if (mAnimatedDrawable == null) {
            val offset = (width - height) / 2

            mAnimatedDrawable = ProgressDrawable(getThemeAccentColor(), mStrokeWidth.toFloat())
            val left = offset + mPaddingProgress
            val right = width - offset - mPaddingProgress
            val bottom = height - mPaddingProgress
            val top = mPaddingProgress
            mAnimatedDrawable!!.setBounds(left, top, right, bottom)
            mAnimatedDrawable!!.callback = this
            mAnimatedDrawable!!.start()
        } else {
            mAnimatedDrawable!!.draw(canvas!!)
        }
    }

    private fun getThemeAccentColor(): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, value, true)
        return value.data
    }

    private fun setLoading(loading: Boolean) {
        isLoading = loading
        updateButton()
    }

    private fun updateButton() {
        if (isLoading) {
            drawIndeterminateProgress(mCanvas)
            text = ""
            icon = null
        } else {
            if (buttonText.isNotEmpty()) text = buttonText
            if (buttonIcon != null) icon = buttonIcon
        }
    }
}