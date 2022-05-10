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
    createArithmetic()
    createInitialization()
   createOutput()

    pushDataForInitialization("aa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd",0)
    pushDataForArithmetic("a+2","a23",1)
    pushDataForInitialization("abvgd",2)
    pushDataForArithmetic("a23+3","abvgd",3)
    pushDataForInitialization("1233clone",4)
    pushDataForArithmetic("  abvgd","clone",5)
    pushDataForArithmetic("(abvgd+a23)*clone","a",6)
    pushDataForInitialization("ex",7)
    pushDataForOutput("clone,ex",8)


    fun start() {
        for (name in listOfBlocks) {
            name.start()
            if (!name.status)
            {
                println(name.ErrorString)
                break
            }
        }
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
