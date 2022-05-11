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