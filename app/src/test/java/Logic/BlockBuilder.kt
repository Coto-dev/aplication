package Logic

import Logic.MainBlock.Companion.listOfBlocks

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
fun swapBlocks(blockIndexA:Int,blockIndexB:Int){
    listOfBlocks[blockIndexA] = listOfBlocks[blockIndexB].also {listOfBlocks[blockIndexB] = listOfBlocks[blockIndexA]  }
}