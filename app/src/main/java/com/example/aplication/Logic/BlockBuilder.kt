package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.listOfBlocks
fun pushDataForInitialization(editText:String,index:Int) {
    val a = Initialization()
    a.textBar = editText
    listOfBlocks[index] = a
}
fun pushDataForIfElse(editText:String,indexStart: Int, indexElse:Int, indexFinish: Int) {
    val a = IfElse()
    a.indStart = indexStart
    a.indElse = indexElse
    a.indFinish = indexFinish
    a.textBar = editText
    listOfBlocks[indexStart] = a
}

fun pushDataForIf(editText:String,indexStart:Int,indexFinish:Int) {
    val a = If()
    a.indStart = indexStart
    a.indFinish = indexFinish
    a.textBar = editText
    listOfBlocks[indexStart] = a
}
fun pushDataForWhile(editText:String,indexStart:Int,indexFinish:Int) {
    val a = While()
    a.indStart = indexStart
    a.indFinish = indexFinish
    a.textBar = editText
    listOfBlocks[indexStart] = a
}
fun pushDataForArithmetic(editText:String,variable:String,index:Int){
    val a = Arithmetic()
    a.textBar = editText
    a.variable = variable
    listOfBlocks[index] = a
}
fun pushDataForEnd(index:Int){
    val a = Null()
    listOfBlocks[index] = a
}
fun pushDataForOutput(editText:String,index:Int){
    val a = Output()
    a.textBar = editText
    listOfBlocks[index] = a
}
fun pushDataForInput(editText:String,index:Int){
    val a = Input()
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