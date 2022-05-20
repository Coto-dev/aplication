package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.consoleOutput
import com.example.aplication.Logic.MainBlock.Companion.variables
import java.util.*

class Input : MainBlock{
    override var ErrorString = ""
    override var status = true
    val vars = variables
    var textBar: String=""
    var stack: String=""

    override fun start() = input()

    fun input() {
        if (!textBar?.contains(Regex("""([^\w|,|\s]|((^|,)\s*([0-9]+[a-zA-Z]|\d+))|(\w+\s+\w+)|,{2,})"""))!!)
        {
            val matches = Regex("""[a-zA-Z]+[0-9]*""").findAll(textBar)
            var s = readLine()
            while (!stack.contains(Regex("""[0-9]+"""))) {

            }
            while (stack.contains(Regex("""[0-9]+"""))&&textBar.contains(Regex("""[a-zA-Z]+[0-9]*""")))
                {
                    //variables.replace(, 2)
                //vars.replace(textBar.find(Regex("""[a-zA-Z]+[0-9]*""")),stack.find(Regex("""[0-9]+""")).toInt())
                //vars.replace(textBar.find(Regex("""[a-zA-Z]+[0-9]*""")), stack.find(Regex("""[0-9]+""")))
                }
            //s = readLine()

            //println(s + " World!")
        }
    }
}