package com.nidhinek.ottminiframe.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleObserver

abstract class BaseWidget<T> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), LifecycleObserver {
    lateinit var widgetView: View

    init {
        widgetView = LayoutInflater.from(context).inflate(getLayoutId(), this)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun bindData(data: T, viewPosition: Int = 0)
}

