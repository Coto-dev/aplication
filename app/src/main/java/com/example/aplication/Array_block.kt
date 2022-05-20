package com.example.aplication

import android.R
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.aplication.databinding.CustomBlockBinding
import com.example.aplication.databinding.ForInitializationBinding
import com.example.aplication.databinding.ArrayBlockBinding

class Array_block constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {
    private val binding2 = ArrayBlockBinding.inflate(LayoutInflater.from(context), this)

    fun GetText2(): String {
        val textview2 = binding2.forArray
        var b: String = textview2.text.toString()

        return b
    }

}