package com.example.aplication.Logic
import com.example.aplication.Logic.MainBlock.Companion.listOfBlocks
import com.example.aplication.Logic.createMassive as createMassive1

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
    createMassive1()
    createIfElse()

    pushDataForInitialization("aa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd",0)
    pushDataForArithmetic("a+2","a23",1)
    //pushDataForInitialization("abvgd",2)
    //pushDataForArithmetic("-(a23+3)","abvgd",3)
    //pushDataForInitialization("clone",4)
    //pushDataForInitialization("clone",4)
    //pushDataForIf_Else("(aa+a=b)||(a=b)",1)
    //pushDataForIf_Else("(2=b)&(a=b)",2)
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
 //      println(variables)
    }
    start()
        //val a = ContainerVariables()
        //a.textBar = "aaa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd"
        //a.indetify()
        val b = IfElse()


    b.textBar = "((aa+b<3)&&&&&&&&&&&&&&&&&&&1)||||||||||||||||0&1"
    //b.variable = "b"
    b.assign()


    }
