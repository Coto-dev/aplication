package Logic
import Logic.MainBlock.Companion.listOfBlocks
import Logic.MainBlock.Companion.variables
import java.util.*

fun main(){
    createInitialization()
    createArithmetic()
    createInitialization()
    createArithmetic()
    createInitialization()
    createArithmetic()

    pushDataForInitialization("aaa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd",0)
    pushDataForInitialization("abvgd",2)
    pushDataForInitialization("clone",4)
    pushDataForArithmetic("a+2","a23",1)
    pushDataForArithmetic("a23+3","abvgd",3)
    pushDataForArithmetic("abvgd+1","clone",5)

    fun start() {
        for (name in listOfBlocks)
            name.start()
       //println(variables)
    }
    start()
    //        val a = ContainerVariables()
//        a.textBar = "aaa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd"
//        a.indetify()
//        val b = Arithmetic()
//    b.textBar = "3+4"
//    b.variable = "b"
//    b.assign()
    }
