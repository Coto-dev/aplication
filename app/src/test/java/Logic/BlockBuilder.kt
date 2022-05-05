package Logic

import Logic.MainBlock.Companion.listOfBlocks

fun GetDataForContainerVariables(textBar:String): ContainerVariables {
    val a = ContainerVariables()
    a.textBar = textBar
    return a
}
fun GetDataForArithmetic(textBar:String,variable:String): Arithmetic {
    val a = Arithmetic()
    a.textBar = textBar
    a.variable = variable
    return a
}