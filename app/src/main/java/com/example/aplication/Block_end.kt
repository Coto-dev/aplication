package com.example.aplication

import android.R
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.aplication.databinding.BlockEndBinding
import com.example.aplication.databinding.CustomBlockBinding
import com.example.aplication.databinding.ForInitializationBinding

class Block_end  constructor (
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0) :CoordinatorLayout(context, attrs, defStyleAttr) {
    private val binding2 = BlockEndBinding.inflate(LayoutInflater.from(context), this)

}