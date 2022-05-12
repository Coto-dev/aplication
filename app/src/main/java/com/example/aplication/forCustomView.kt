package com.example.aplication

import android.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.aplication.databinding.CustomBlockBinding
import com.example.aplication.databinding.ForInitializationBinding
import com.example.aplication.Logic.pushDataForArithmetic
import com.example.aplication.Logic.pushDataForInitialization

class ForCustomView  constructor (
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0) :CoordinatorLayout(context, attrs, defStyleAttr) {
    private val binding = CustomBlockBinding.inflate(LayoutInflater.from(context), this)

    fun SetText(text:String) {
       val textview= binding.nupizda
       if (textview.text!=null){
           var a:String=textview.text.toString()
           textview.text=text+" "+a
       }
       else{
           textview.text=text
       }

    }

    fun GetText1():String {
        val textview= binding.textInput
        var a:String=textview.text.toString()
        return a
    }

    fun GetText2():String {
        val textview2= binding.textInput2
        var b:String=textview2.text.toString()
        return b
    }

}