package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.listOfBlocks

fun pushDataForInitialization(editText:String,index:Int) {
    val a = Initialization()
    a.textBar = editText
    listOfBlocks[index] = a
}
fun pushDataForArithmetic(editText:String,variable:String,index:Int){
    val a = Arithmetic()
    a.textBar = editText
    a.variable = variable
    listOfBlocks[index] = a
}
fun pushDataForOutput(editText:String,index:Int){
    val a = Output()
    a.textBar = editText
    listOfBlocks[index] = a
}
fun pushDataForMassive(editText:String,index:Int){
    val a = Massive()
    a.textBar = editText
    listOfBlocks[index] = a
}
fun swapBlocks(blockIndexA:Int,blockIndexB:Int){
    listOfBlocks[blockIndexA] = listOfBlocks[blockIndexB].also {listOfBlocks[blockIndexB] = listOfBlocks[blockIndexA]  }
}