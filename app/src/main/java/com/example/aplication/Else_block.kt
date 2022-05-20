package com.example.aplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.aplication.databinding.ElseBlockBinding

class Else_block constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {
    private val binding = ElseBlockBinding.inflate(LayoutInflater.from(context), this)

}
