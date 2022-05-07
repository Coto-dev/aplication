package com.example.aplication

import android.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.aplication.databinding.CustomBlockBinding


class ForCustomView  constructor (
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0) :CoordinatorLayout(context, attrs, defStyleAttr) {
    private val binding = CustomBlockBinding.inflate(LayoutInflater.from(context), this)


    fun GetLastBox(x: Int, y: Int) {
        return
    }

}