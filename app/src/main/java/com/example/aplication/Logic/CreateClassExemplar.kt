package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.listOfBlocks

fun createInitialization(){
    listOfBlocks.add(Initialization())
}
fun createArithmetic(){

    listOfBlocks.add(Arithmetic())
}
fun createOutput(){

    listOfBlocks.add(Output())
}
fun createMassive(){

    listOfBlocks.add(Massive())
}

fun createNull(){

    listOfBlocks.add(Null())
}
fun createIfElse(){

    listOfBlocks.add(IfElse())
}

fun createWhile(){

    listOfBlocks.add(While())
}
fun createIf(){

    listOfBlocks.add(If())
}