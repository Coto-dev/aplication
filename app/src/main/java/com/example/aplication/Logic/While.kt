package com.example.aplication.Logic

import com.example.aplication.Logic.MainBlock.Companion.variables
import com.example.aplication.Logic.MainBlock.Companion.index
import android.text.TextUtils.replace
import androidx.core.text.isDigitsOnly
import java.lang.Error
import java.util.*


class While : MainBlock {
    override var ErrorString = ""
    override var status = true
    val vars = MainBlock.variables
    val name: String? = null
    var indStart = 0;
    var indFinish = 0;
    val variables = mutableMapOf<String, Int>()
    var i = index
    var textBar: String = ""

    override fun start() = assign()
    fun assign() {
        //textBar = (recognize(textBar));
        //pushDataForArithmetic("aa","aa+1",0);
        if (condition(recognize(textBar)) == 1) {
            index = indStart + 1;
            while (index < indFinish) {
                MainBlock.listOfBlocks[index].start()
                if (!MainBlock.listOfBlocks[index].status) {
                    MainBlock.consoleOutput += MainBlock.listOfBlocks[index].ErrorString
                    break
                }
                index++
            }
            index = indStart - 1
        } else index = indFinish
    }

    private fun assignmentVar(textBar: String): String {
        var variable: String = ""
        if (!textBar.contains(Regex("""([^\d|\s|^\+\-\/\*\!\(\)\%\>\<\=\&\||^a-zA-Z])"""))) {
            val matches = Regex("""(([a-zA-Z]+[0-9]*)|([0-9]+[a-zA-Z]+))""").find(textBar)
            if (vars.containsKey(matches?.value))
                variable = matches?.value.toString()
            else {
                ErrorString = "variable is not exist : ${matches?.value}"
                status = false
            }

        } else {
            status = false
            val matches = Regex("""([^\d|\s|^\+\-\/\*\(\)\%\>\<\=\&\||^a-zA-Z])""").find(textBar)
            ErrorString = "the value of the variable was entered incorrectly : ${matches?.value}"
        }

        return variable
    }

    private fun calculate(textBar: String): String {
        println(textBar)
        if (textBar.contains(Regex("""((\s*\/\s*0))"""))) {
            val matches = Regex("""((\s*\/\s*0))""").find(textBar)
            ErrorString = "incorrect expression : ${matches?.value}"
            status = false
        }
        if (!status)
            return "0"
        val text = textBar.replace("""\s""".toRegex(), "")
        if (!text.contains(Regex("""([\+\-\/\*\%])""")))
            return text.toString()
        val stack: Stack<Char> = Stack<Char>()
        var RPN: String = ""
        for (i in text) {
            if (i.isDigit()) {
                RPN += i
            } else {

                RPN += ' '

                if (i == '-' || i == '+') {
                    if (!stack.isEmpty()) {
                        if (stack.peek() == '-' || stack.peek() == '+' || stack.peek() == '/' || stack.peek() == '*' || stack.peek() == '%') {
                            if (!(RPN[RPN.length - 1] == '-' || RPN[RPN.length - 1] == '+' || RPN[RPN.length - 1] == '/' || RPN[RPN.length - 1] == '*' || RPN[RPN.length - 1] == '%')) {
                                RPN += stack.pop()
                                RPN += ' '
                            }

                        }
                        stack.push(i)
                    } else stack.push(i)
                }
                if (i == '*' || i == '/' || i == '%') {
                    if (!stack.isEmpty()) {
                        if (stack.peek() == '/' || stack.peek() == '*' || stack.peek() == '%') {
                            RPN += stack.pop()
                            RPN += ' '
                        }
                        stack.push(i)
                    } else stack.push(i)
                }

                if (i == '(' || i == ')') {
                    if (i == '(') {
                        stack.push(i)
                    } else {
                        while (stack.peek() != '(') {
                            RPN += stack.pop()
                            RPN += ' '
                        }
                        stack.pop()
                    }
                }
            }

        }

        while (!stack.isEmpty()) {
            RPN += ' '
            RPN += stack.pop()
        }

        RPN = RPN.replace("""\s+""".toRegex(), " ")
        RPN = RPN.replace("""^\s+""".toRegex(), "")
        val stackString: Stack<String> = Stack<String>()
        var value = ""
        var temp: String = ""
        for (i in RPN.indices) {
            if (RPN[i] == ' ') {
                if (value != "")
                    stackString.push(value)
                value = ""
            } else if (RPN[i].isDigit()) value += RPN[i]
            if (temp != "" && stackString.size > 1) {
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
                        } else {
                            val count = -x.toInt()
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


                            } else
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
            if (RPN[i] == '-' || RPN[i] == '+' || RPN[i] == '/' || RPN[i] == '*' || RPN[i] == '%') {

                if (RPN[i] == '+') {
                    var x = stackString.pop()
                    if (!stackString.isEmpty()) {
                        val y = stackString.pop()
                        val count = y.toInt() + x.toInt()
                        stackString.push(count.toString())
                    } else {
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
                        } else {
                            val count = -x.toInt()
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
                            } else {
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
                                } else {
                                    stackString.push(x)
                                    temp = RPN[i].toString()
                                }

                            } else
                                if (RPN[i] == '%') {
                                    var x = stackString.pop()
                                    if (!stackString.isEmpty()) {
                                        val y = stackString.pop()
                                        val count = y.toInt() % x.toInt()
                                        stackString.push(count.toString())
                                    } else {
                                        stackString.push(x)
                                        temp = RPN[i].toString()
                                    }

                                }

            }


        }

        return (stackString.pop().toString())
    }

    private fun recognize(textBar: String): String {
        var text = textBar
        if (!textBar.contains(Regex("""([^\d|\s|^\+\-\/\*\(\)\%\>\<\=\&\||^a-zA-Z])"""))) {
            var matches = Regex("""(([a-zA-Z]+[0-9]*)|([0-9]+[a-zA-Z]+))""").find(text)
            while (matches != null) {
                if (vars.containsKey(matches.value)) {
                    text = text.replaceRange(matches.range, vars.getValue(matches.value).toString())
                } else {
                    // исключение : тут пользователь ввел переменную которую не задавал(к примеру 1+2+a+c)(словарь: a=0,b=0)
                    //return matches.value // та самая переменная c
                    status = false
                    ErrorString = "undefined variable : ${matches.value}"
                    break
                }
                matches = Regex("""(([a-zA-Z]+[0-9]*)|([0-9]+[a-zA-Z]+))""").find(text)
            }
            //заменили переменные на числа, теперь заменим выражения на числа
            matches =
                Regex("""\((([a-zA-Z]+[0-9]*)|([0-9]+))([\+\-\/\*\%](([a-zA-Z]+[0-9]*)|[0-9]+))+\)|(([a-zA-Z]+[0-9]*)|([0-9]+))([\+\-\/\*\%](([a-zA-Z]+[0-9]*)|[0-9]+))+""")
                    .find(text)
            while (matches != null) {
                text = text.replace(matches.value, calculate(matches.value))

                matches =
                    Regex("""\((([a-zA-Z]+[0-9]*)|([0-9]+))([\+\-\/\*\%](([a-zA-Z]+[0-9]*)|[0-9]+))+\)|(([a-zA-Z]+[0-9]*)|([0-9]+))([\+\-\/\*\%](([a-zA-Z]+[0-9]*)|[0-9]+))+""").find(
                        text
                    )
            }

            //ну, чтож теперь заменяем равенства
            matches =
                Regex("""\(\-{0,1}[0-9]+[\=><\!]+[0-9]+\)|\-{0,1}[0-9]+[\=><\!]+\-{0,1}[0-9]+""")
                    .find(text)
            while (matches != null) {
                text = text.replace(matches.value, equality(matches.value))
                matches =
                    Regex("""\(\-{0,1}[0-9]+[\=><\!]+\-{0,1}[0-9]+\)|[0-9]+[\=><\!]+\-{0,1}[0-9]+""").find(
                        text
                    )
            }
        } else {
            //исключение(тут надо в UX выдать пользователю ошибку типо ввел невозможную переменную e.g "12awd","@#!aue" и тд)
            val matches = Regex("""([^\d|\s|^\+\-\/\*\(\)\%\>\<\=\&\||^a-zA-Z])""").find(textBar)
            ErrorString = "incorrect expression : ${matches?.value}"
            status = false
        }
        if (textBar.contains(Regex("""([a-zA-Z]+[0-9]*)\s+([a-zA-Z]+[0-9]*)"""))) {
            val matches = Regex("""([a-zA-Z]+[0-9]*)\s+([a-zA-Z]+[0-9]*)""").find(textBar)
            ErrorString = "incorrect expression : ${matches?.value}"
            status = false
        }
        //print(text);
        return text
    }

    private fun equality(textBar: String): String {
        val x1: MatchResult? = """([^>=<=!<\?\)\(])+""".toRegex().find(textBar)
        val x2: MatchResult? = x1?.next();
        if (x1 == null || x2 == null) {
            ErrorString = "incorrect expression : ${textBar}"
            return "";
        }
        if (textBar.contains(Regex(""">="""))) {
            if (x1.value.toInt() >= x2.value.toInt())
                return "1";
            else return "0"
        }
        if (textBar.contains(Regex("""<="""))) {
            if (x1.value.toInt() <= x2.value.toInt())
                return "1";
            else return "0"
        }
        if (textBar.contains(Regex("""!=|<>|><"""))) {
            if (x1.value.toInt() != x2.value.toInt())
                return "1";
            else return "0"
        }
        if (textBar.contains(Regex("""="""))) {
            if (x1.value.toInt() == x2.value.toInt())
                return "1";
            else return "0"
        }
        if (textBar.contains(Regex(""">"""))) {
            if (x1.value.toInt() > x2.value.toInt())
                return "1";
            else return "0"
        }
        if (textBar.contains(Regex("""<"""))) {
            if (x1.value.toInt() < x2.value.toInt())
                return "1";
            else return "0"
        }
        if (textBar == "0")
            return "0";
        else return "1";
    }


    private fun condition(textBar: String): Int {
        println(textBar)
        if (textBar.contains(Regex("""((\d\s*\/\s*0))"""))) {
            val matches = Regex("""((\d\s*\/\s*0))""").find(textBar)
            ErrorString = "incorrect expression : ${matches?.value}"
            status = false
        }
        if (!status)
            return 0
        val text = textBar.replace("""\s""".toRegex(), "")
        if (!text.contains(Regex("""([\|\&])""")))
            return text.toInt()
        val stack: Stack<Char> = Stack<Char>()
        var RPN: String = ""
        for (i in text) {
            if (i.isDigit()) {
                RPN += i
            } else {

                RPN += ' '

                if (i == '|') {
                    if (!stack.isEmpty()) {
                        if (stack.peek() == '|') {
                            if (!(RPN[RPN.length - 1] == '|')) {
                                RPN += stack.pop()
                                RPN += ' '
                            }

                        }
                        stack.push(i)
                    } else stack.push(i)
                }
                if (i == '&') {
                    if (!stack.isEmpty()) {
                        if (stack.peek() == '&') {
                            RPN += stack.pop()
                            RPN += ' '
                        }
                        stack.push(i)
                    } else stack.push(i)
                }

                if (i == '(' || i == ')') {
                    if (i == '(') {
                        stack.push(i)
                    } else {
                        while (stack.peek() != '(') {
                            RPN += stack.pop()
                            RPN += ' '
                        }
                        stack.pop()
                    }
                }
            }

        }

        while (!stack.isEmpty()) {
            RPN += ' '
            RPN += stack.pop()
        }

        RPN = RPN.replace("""\s+""".toRegex(), " ")
        RPN = RPN.replace("""\|{2}""".toRegex(), "|")
        RPN = RPN.replace("""&{2}""".toRegex(), "&")
        RPN = RPN.replace("""^\s+""".toRegex(), "")
        val stackString: Stack<String> = Stack<String>()
        var value = ""
        var temp: String = ""
        for (i in RPN.indices) {
            if (RPN[i] == ' ') {
                if (value != "")
                    stackString.push(value)
                value = ""
            } else if (RPN[i].isDigit()) value += RPN[i]
            if (temp != "" && stackString.size > 1) {
                if (temp == "|") {
                    var x = stackString.pop()
                    if (!stackString.isEmpty()) {
                        val y = stackString.pop()
                        val count = y.toInt() + x.toInt()
                        if (count == 0)
                            stackString.push("0")
                        else stackString.push("1")
                    }
                } else
                    if (temp == "&") {
                        var x = stackString.pop()

                        if (!stackString.isEmpty()) {
                            val y = stackString.pop()
                            val count = y.toInt() * x.toInt()
                            stackString.push(count.toString())
                        }
                    }
                temp = ""
            }

            if (RPN[i] == '|') {
                var x = stackString.pop()
                if (!stackString.isEmpty()) {
                    val y = stackString.pop()
                    val count = y.toInt() + x.toInt()
                    stackString.push(count.toString())
                } else {
                    stackString.push(x)
                    temp = RPN[i].toString()
                }
            } else
                if (RPN[i] == '&') {
                    var x = stackString.pop()
                    if (!stackString.isEmpty()) {
                        val y = stackString.pop()
                        val count = y.toInt() * x.toInt()
                        stackString.push(count.toString())
                    } else {
                        stackString.push(x)
                        temp = RPN[i].toString()
                    }
                }
        }
        println(stackString.pop().toInt())
        return (stackString.pop().toInt())
    }
}


