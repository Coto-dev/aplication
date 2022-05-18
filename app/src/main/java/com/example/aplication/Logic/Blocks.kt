package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.consoleOutput
import com.example.aplication.Logic.MainBlock.Companion.listOfBlocks
import com.example.aplication.Logic.MainBlock.Companion.index
import com.example.aplication.Logic.createMassive as createMassive1

fun main() {
//    createInitialization()
//    createArithmetic()
//    createInitialization()
//    createWhile()
//    createArithmetic()
//    createIf()
//    createArithmetic()
//    createNull()
//    createInitialization()
//    createNull()
//   createArithmetic()
//    createArithmetic()
//    createInitialization()
//   createOutput()
//    createMassive1()
    createInitialization()
    createIfElse()
    createArithmetic()
    createNull()
    createArithmetic()
    createNull()
    createInitialization()
//
//    createOutput()
//    createOutput()


    pushDataForInitialization("aa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd",0)
    pushDataForIfElse("aa<<<<<<<<<<<=3",1,3,5)
    pushDataForArithmetic("a23+1","a23",2)
//    pushDataForInitialization("abvgd",2)
//    pushDataForWhile("a23>1",3, 9)
    pushDataForArithmetic("2","a23",4)
//    pushDataForIf("aa=0",5, 7)
//    pushDataForArithmetic("-(a23+5)","abvgd",6)
//
    pushDataForInitialization("w",6)
//
//    pushDataForOutput("a23+10", 10)
//    pushDataForOutput("abvgd", 11)
//    //pushDataForInitialization("clone",4)
    //pushDataForIf_Else("(aa+a=b)||(a=b)",1)
    //pushDataForIf_Else("(2=b)&(a=b)",2)
//    pushDataForArithmetic("  abvgd","clone",5)
//    pushDataForArithmetic(" ((2+2)*3 +(3+3)*2)+2 ","a",6)
//    pushDataForInitialization("ex",7)
//    pushDataForOutput("clone,ex",8)
//    pushDataForMassive("mas[100],ad[21],a1[12]",9)

    fun start() {
        /*for (name in listOfBlocks) {
            name.start()
            if (!name.status)
            {
                consoleOutput+=name.ErrorString
                break
            }
        }*/
        //var i = index
        while (index < listOfBlocks.size) {
            listOfBlocks[index].start()
            if (!listOfBlocks[index].status) {
                consoleOutput += listOfBlocks[index].ErrorString
                break
            }
            //print( index)
            index++
        }
        //      println(variables)
    }

    fun getTextForConsole(text: String) {

    }
    start()
    //val a = ContainerVariables()
    //a.textBar = "aaa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd"
    //a.indetify()
//        val b = IfElse()


//    b.textBar = "((aa+b<3)&&&&&&&&&&&&&&&&&&&1)||||||||||||||||0&1"
    //b.variable = "b"
//    b.assign()


}
