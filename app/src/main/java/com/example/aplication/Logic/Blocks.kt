package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.MapArray
import com.example.aplication.Logic.MainBlock.Companion.consoleOutput
import com.example.aplication.Logic.MainBlock.Companion.listOfBlocks
import com.example.aplication.Logic.MainBlock.Companion.index

fun main() {
    createMassive()//0
    createInitialization()//1
    createArithmetic()//2
    createWhile()//3
    createArithmetic()//4
    createWhile()//5
    createIf()//6
    createArithmetic()//7
    createArithmetic()//8
    createArithmetic()//9
    createNull()//10
    createArithmetic()//11
    createNull()//12
    createArithmetic()//13
    createNull()//14
    pushDataForMassive("mas[8]={-9,-1,1,2,3,4,5,6}",0)
    pushDataForInitialization("temp,j,i",1)
    pushDataForArithmetic("7","j",2)
    pushDataForWhile("j>=0",3,14)
    pushDataForArithmetic("0","i",4)
    pushDataForWhile("i<j",5,12)
    pushDataForIf("mas[i]>mas[i+1]",6,10)
    pushDataForArithmetic("mas[i]","temp",7)
    pushDataForArithmetic("mas[i+1]","mas[i]",8)
    pushDataForArithmetic("temp","mas[i+1]",9)
    pushDataForArithmetic("i+1","i",11)
    pushDataForArithmetic("j-1","j",13)


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
//    createInitialization()
//    createIfElse()
//    createArithmetic()
//    createNull()
//    createArithmetic()
//    createNull()
//    createInitialization()
//
//    createOutput()
//    createOutput()

//    pushDataForMassive("mas[4]={1,2, 10},a[10]={1},aawd[10]={1, 3 }",0)
//    pushDataForInitialization("yu",1)
//    pushDataForArithmetic("1","yu",2)
//    pushDataForArithmetic("aawd[0]+1-4+mas[2]","mas[1+yu-1]",3)
//    pushDataForInitialization("aa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd",0)
//    pushDataForIfElse("aa<<<<<<<<<<<=3",1,3,5)
//    pushDataForArithmetic("a23+1","a23",2)
//    pushDataForInitialization("abvgd",2)
//    pushDataForWhile("a23>1",3, 9)
//    pushDataForArithmetic("2","a23",4)
//    pushDataForIf("aa=0",5, 7)
//    pushDataForArithmetic("-(a23+5)","abvgd",6)
//
//    pushDataForInitialization("w",6)
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
                print("break$consoleOutput")
                break
            }

            index++
        }
        //      println(variables)
    }

    fun getTextForConsole(text: String) {

    }
    start()
    println("norm$consoleOutput")
    println("map ${MapArray["mas"]?.get(0)}")
    println("map ${MapArray["mas"]?.get(1)}")
    println("map ${MapArray["mas"]?.get(2)}")
    println("map ${MapArray["mas"]?.get(3)}")
    println("map ${MapArray["mas"]?.get(4)}")
    println("map ${MapArray["mas"]?.get(5)}")
    println("map ${MapArray["mas"]?.get(6)}")
    println("map ${MapArray["mas"]?.get(7)}")



    //val a = ContainerVariables()
    //a.textBar = "aaa,b,a2,aaw233,  baw23, a ,a23,   adwadwadawdadwa,wdadsawd"
    //a.indetify()
//        val b = IfElse()


//    b.textBar = "((aa+b<3)&&&&&&&&&&&&&&&&&&&1)||||||||||||||||0&1"
    //b.variable = "b"
//    b.assign()


}
