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
import com.example.aplication.Logic.pushDataForArithmetic
import com.example.aplication.Logic.pushDataForInitialization
import com.example.aplication.databinding.PrintBlockBinding

class Print_block  constructor (
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0) :CoordinatorLayout(context, attrs, defStyleAttr) {
    private val binding = PrintBlockBinding.inflate(LayoutInflater.from(context), this)

//    fun setName(text:String) {
//        val textview= binding.nupizda
//        if (textview.text!=null){
//            var a:String=textview.text.toString()
//            textview.text=text+" "+a
//        }
//        else{
//            textview.text=text
//        }
//
//    }

    fun GetText():String {
        val textview= binding.forPrint
        var a:String=textview.text.toString()
        return a
    }

}