package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.MapArray
import com.example.aplication.Logic.MainBlock.Companion.consoleOutput
import com.example.aplication.Logic.MainBlock.Companion.variables
import java.util.*

class Output : MainBlock {
    override var ErrorString = ""
    override var status = true
    val vars = variables
    var map = MapArray
    var textBar: String=""

    override fun start() = output()

    fun output(){
    consoleOutput.add(calculate(recognize(textBar)).toString())
    }
    private fun recognize(textBar:String):String{
        var text = textBar
        var indText = ""
        // println("text1 $text")
        if (!textBar.contains(Regex("""([^\d\s^\+\-\/\*\(\)\%^a-zA-Z\[\]])"""))) {

            if (text.contains(Regex("""\w+\d*(\[\s*((\d+|[a-zA-Z]+\d*)\s*([\+\-\/\*\(\)\%]\s*(\d+|[a-zA-Z]+\d*)\s*)*)+\])"""))) {
                var matchesForMassive = Regex("""\w+\d*(\[\s*((\d+|[a-zA-Z]+\d*)\s*([\+\-\/\*\(\)\%]\s*(\d+|[a-zA-Z]+\d*)\s*)*)+\])""").find(text)
                //  println("matchesForMassive ${matchesForMassive?.value}")
                while (matchesForMassive != null) {
                    // println(map["a"]?.get(0))
                    val ind = Regex("""(\[\s*((\d+|[a-zA-Z]+\d*)\s*([\+\-\/\*\(\)\%]\s*(\d+|[a-zA-Z]+\d*)\s*)*)+\])""").find(matchesForMassive.value)
                    indText = ind?.value?.replace("""\[""".toRegex(), "").toString()
                    indText = indText?.replace("""\]""".toRegex(), "")
                    // println("indText ${ind?.value}")
                    var indexInt = calculate(recognize(indText))
                    val nameMas = Regex("""\w+\d*\[""").find(matchesForMassive?.value.toString())
                    var NameText = nameMas?.value?.replace("""\[""".toRegex(), "")

                    if (map.containsKey(NameText)) {
                        //
                        // println("matchesForMassive.range ${matchesForMassive.range}")
                        // println("map[nameMas?.value.toString()]?.get(ind?.value!!.toInt()).toString() ${map[NameText]?.get(indexInt).toString()}")
                        text = text.replaceRange(matchesForMassive.range, map[NameText]?.get(indexInt).toString())
                        // println("text $text")
                    } else {
                        // исключение : тут пользователь ввел переменную которую не задавал(к примеру 1+2+a+c)(словарь: a=0,b=0)
                        //return matches.value // та самая переменная c
                        status = false
                        ErrorString = "undefined massive : ${NameText}"
                        break
                    }
                    matchesForMassive = Regex("""\w+\d*(\[\s*((\d+|[a-zA-Z]+\d*)\s*([\+\-\/\*\(\)\%]\s*(\d+|[a-zA-Z]+\d*)\s*)*)+\])""").find(text)
                }
            }


            var matches = Regex("""(([a-zA-Z]+[0-9]*)|([0-9]+[a-zA-Z]+))""").find(text)
            while (matches != null)
            {
                if (vars.containsKey(matches.value)) {
                    // println(matches.value)
                    text = text.replaceRange(matches.range, vars.getValue(matches.value).toString())
                }
                else{
                    // исключение : тут пользователь ввел переменную которую не задавал(к примеру 1+2+a+c)(словарь: a=0,b=0)
                    //return matches.value // та самая переменная c
                    status = false
                    ErrorString = "undefined variable : ${matches.value}"
                    break
                }
                matches = Regex("""(([a-zA-Z]+[0-9]*)|([0-9]+[a-zA-Z]+))""").find(text)
            }

        }
        else{
            //исключение(тут надо в UX выдать пользователю ошибку типо ввел невозможную переменную e.g "12awd","@#!aue" и тд)
            val matches = Regex("""([^\d\s^\+\-\/\*\(\)\%^a-zA-Z\[\]])""").find(textBar)
            ErrorString = "incorrect expression : ${matches?.value}"
            status = false
        }
        if (textBar.contains(Regex("""([a-zA-Z]+[0-9]*)\s+([a-zA-Z]+[0-9]*)"""))) {
            val matches = Regex("""([a-zA-Z]+[0-9]*)\s+([a-zA-Z]+[0-9]*)""").find(textBar)
            ErrorString = "incorrect expression : ${matches?.value}"
            status = false
        }
        return text
    }
    private fun calculate(textBar:String):Int{
        println(textBar)
        if (textBar.contains(Regex("""((\s*\/\s*0))"""))) {
            val matches = Regex("""((\s*\/\s*0))""").find(textBar)
            ErrorString = "incorrect expression : ${matches?.value}"
            status = false
        }
        if (!status)
            return 0
        val text = textBar.replace("""\s""".toRegex(), "")
        if (!text.contains(Regex("""([\+\-\/\*\%])""")))
            return text.toInt()
        val stack: Stack<Char> = Stack<Char>()
        var RPN:String = ""
        for (i in text) {
            if(i.isDigit()){
                RPN+=i
            }
            else{

                RPN+=' '

                if (i == '-' || i == '+'){
                    if(!stack.isEmpty()) {
                        if (stack.peek() == '-' || stack.peek() == '+' || stack.peek() == '/' || stack.peek() == '*'|| stack.peek() == '%') {
                            if (!(RPN[RPN.length-1] == '-' || RPN[RPN.length-1] == '+' || RPN[RPN.length-1] == '/' || RPN[RPN.length-1] == '*' || RPN[RPN.length-1] ==  '%')) {
                                RPN += stack.pop()
                                RPN += ' '
                            }

                        }
                        stack.push(i)
                    }
                    else stack.push(i)
                }
                if (i == '*' || i == '/'|| i == '%') {
                    if(!stack.isEmpty()) {
                        if (stack.peek() == '/' || stack.peek() == '*'|| stack.peek() == '%') {
                            RPN += stack.pop()
                            RPN+=' '
                        }
                        stack.push(i)
                    }
                    else stack.push(i)
                }

                if (i == '(' || i ==')') {
                    if (i == '('){
                        stack.push(i)
                    }
                    else {
                        while(stack.peek() != '('){
                            RPN+= stack.pop()
                            RPN+=' '
                        }
                        stack.pop()
                    }
                }
            }

        }

        while(!stack.isEmpty()){
            RPN+=' '
            RPN+= stack.pop()
        }

        RPN = RPN.replace("""\s+""".toRegex(), " ")
        RPN = RPN.replace("""^\s+""".toRegex(), "")
        val stackString: Stack<String> = Stack<String>()
        var value = ""
        var temp:String = ""
        for (i in RPN.indices){
            if (RPN[i]==' ') {
                if(value!="")
                    stackString.push(value)
                value = ""
            }
            else if(RPN[i].isDigit()) value += RPN[i]
            if (temp!= "" && stackString.size>1) {
                if (temp == "+") {
                    var x = stackString.pop()
                    if (!stackString.isEmpty()) {
                        val y = stackString.pop()
                        val count = y.toInt() + x.toInt()
                        stackString.push(count.toString())
                    }

                } else
                    if (temp == "-") {

                        var x = stackString.pop()
                        if (!stackString.isEmpty()) {
                            val y = stackString.pop()
                            val count = y.toInt() - x.toInt()
                            stackString.push(count.toString())
                        }
                        else{
                            val count =-x.toInt()
                            stackString.push(count.toString())
                        }

                    } else
                        if (temp == "*") {
                            var x = stackString.pop()

                            if (!stackString.isEmpty()) {
                                val y = stackString.pop()
                                val count = y.toInt() * x.toInt()
                                stackString.push(count.toString())
                            }


                        } else
                            if (temp == "/") {
                                var x = stackString.pop()
                                if (!stackString.isEmpty()) {
                                    val y = stackString.pop()
                                    val count = y.toInt() / x.toInt()
                                    stackString.push(count.toString())
                                }


                            }
                            else
                                if (temp == "%") {
                                    var x = stackString.pop()
                                    if (!stackString.isEmpty()) {
                                        val y = stackString.pop()
                                        val count = y.toInt() % x.toInt()
                                        stackString.push(count.toString())
                                    }


                                }
                temp = ""
            }
            if (RPN[i] == '-' || RPN[i] == '+' || RPN[i] == '/' || RPN[i] == '*'|| RPN[i] == '%') {

                if (RPN[i] == '+') {
                    var x = stackString.pop()
                    if (!stackString.isEmpty()) {
                        val y = stackString.pop()
                        val count = y.toInt() + x.toInt()
                        stackString.push(count.toString())
                    }
                    else{
                        stackString.push(x)
                        temp = RPN[i].toString()
                    }
                } else
                    if (RPN[i] == '-') {

                        var x = stackString.pop()
                        if (!stackString.isEmpty()) {
                            val y = stackString.pop()
                            val count = y.toInt() - x.toInt()
                            stackString.push(count.toString())
                        }
                        else{
                            val count =-x.toInt()
                            stackString.push(count.toString())
                        }
// (-5+2)*-5
//5 - 2 +  * 5 -
                    } else
                        if (RPN[i] == '*') {
                            var x = stackString.pop()
                            if (!stackString.isEmpty()) {
                                val y = stackString.pop()
                                val count = y.toInt() * x.toInt()
                                stackString.push(count.toString())
                            }
                            else{
                                stackString.push(x)
                                temp = RPN[i].toString()
                            }

                        } else
                            if (RPN[i] == '/') {
                                var x = stackString.pop()
                                if (!stackString.isEmpty()) {
                                    val y = stackString.pop()
                                    val count = y.toInt() / x.toInt()
                                    stackString.push(count.toString())
                                }
                                else{
                                    stackString.push(x)
                                    temp = RPN[i].toString()
                                }

                            }
                            else
                                if (RPN[i] == '%') {
                                    var x = stackString.pop()
                                    if (!stackString.isEmpty()) {
                                        val y = stackString.pop()
                                        val count = y.toInt() % x.toInt()
                                        stackString.push(count.toString())
                                    }
                                    else{
                                        stackString.push(x)
                                        temp = RPN[i].toString()
                                    }

                                }

            }


        }

        return(stackString.pop().toInt())
    }


}