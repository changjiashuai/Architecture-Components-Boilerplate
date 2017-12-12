package com.changjiashuai.architecture_components_boilerplate.widget.empty

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.changjiashuai.architecture_components_boilerplate.R
import kotlinx.android.synthetic.main.view_empty.view.*

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 15:21.
 */
class EmptyView : RelativeLayout {

    var emptyListener: (() -> Unit)? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_empty, this)
        button_check_again.setOnClickListener { emptyListener?.invoke() }
    }
}