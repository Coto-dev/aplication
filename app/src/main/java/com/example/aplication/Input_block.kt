package com.example.aplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.aplication.databinding.InputBlockBinding

class Input_block  constructor (
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0) :CoordinatorLayout(context, attrs, defStyleAttr) {
    private val binding = InputBlockBinding.inflate(LayoutInflater.from(context), this)

    fun GetText2():String {
        val textview= binding.forInput
        var a:String=textview.text.toString()
        return a
    }

}