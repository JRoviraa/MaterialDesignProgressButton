package com.jroviraa.android.materialdesignprogressbutton


import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class MaterialDesignProgressButton : MaterialButton {
    private var isLoading = false
    private var mAnimatedDrawable: ProgressDrawable? = null
    private var mPaddingProgress = 30
    private var mStrokeWidth = 10
    private var buttonText = ""
    private var mcanvas: Canvas? = null

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        buttonText = text.toString()
    }


    // override MaterialButton.onDraw
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mcanvas = canvas
        if (isLoading) {
            drawIndeterminateProgress(canvas)
            text = ""
        } else {
            if (buttonText.isNotEmpty()) text = buttonText
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
        if (mAnimatedDrawable == null) {
            val offset = (width - height) / 2
            mAnimatedDrawable = ProgressDrawable(R.attr.colorAccent, mStrokeWidth.toFloat())
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

    private fun setLoading(loading: Boolean) {
        isLoading = loading
        if (isLoading) {
            drawIndeterminateProgress(mcanvas)
            text = ""
        } else {
            if (buttonText.isNotEmpty()) text = buttonText
        }
    }
}