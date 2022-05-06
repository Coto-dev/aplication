package Logic

import Logic.MainBlock.Companion.listOfBlocks

fun createInitialization(){
    listOfBlocks.add(Initialization())
}
fun createArithmetic(){

    listOfBlocks.add(Arithmetic())
}