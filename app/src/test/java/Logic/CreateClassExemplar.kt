package Logic

import Logic.MainBlock.Companion.listOfBlocks

fun createBlockContainer(){
    listOfBlocks.add(ContainerVariables())
}
fun createArithmetic(){

    listOfBlocks.add(Arithmetic())
}