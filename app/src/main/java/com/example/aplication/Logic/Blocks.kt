package com.example.aplication.Logic
import com.example.aplication.Logic.MainBlock.Companion.listOfBlocks

fun main(){
//    createInitialization()
//    createArithmetic()
//    createInitialization()
//    createArithmetic()
//    createInitialization()
//    createArithmetic()
//    createArithmetic()
//    createInitialization()
//   createOutput()
//    createMassive()
//
//    pushDataForInitialization("aa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd",0)
//    pushDataForArithmetic("a+2","a23",1)
//    pushDataForInitialization("abvgd",2)
//    pushDataForArithmetic("-(a23+3)","abvgd",3)
//    pushDataForInitialization("clone",4)
//    pushDataForArithmetic("  abvgd","clone",5)
//    pushDataForArithmetic(" ((2+2)*3 +(3+3)*2)+2 ","a",6)
//    pushDataForInitialization("ex",7)
//    pushDataForOutput("clone,ex",8)
//    pushDataForMassive("mas[100],ad[21],a1[12]",9)


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