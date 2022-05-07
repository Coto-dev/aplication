package Logic

import Logic.MainBlock.Companion.variables
import java.util.*

class Arithmetic : MainBlock {
    val vars = variables
    val name :String? = null
    val previousBlock : MainBlock? = null
    val nextBlock : MainBlock? = null
    var textBar: String?=null
    var variable: String? =null
    override fun start() = assign()
    fun assign(){
        variable?.let { assignmentVar(it) }?.let {
            textBar?.let { it1 ->
                recognize(
                    it1
                )
            }?.let { it2 -> calculate(it2) }?.let { it3 -> vars.replace(it, it3) }
        }

    }
    private fun assignmentVar(textBar:String): String {
        var variable:String =""
        if (!textBar.contains(Regex("""([^\d|\s|^\+\-\/\*\(\)|^a-zA-Z])"""))) {
            val matches = Regex("""(([a-zA-Z]+[0-9]*)|([0-9]+[a-zA-Z]+))""").find(textBar)
            if (vars.containsKey(matches?.value))
                variable = matches?.value.toString()
            else {
                println("variable is not exist")
                return "variable is not exist"
            }

        }
        else{
            println("the value of the variable was entered incorrectly")
        }
        return variable
    }
    private fun recognize(textBar:String):String{
        var text = textBar
        if (!textBar.contains(Regex("""([^\d|\s|^\+\-\/\*\(\)|^a-zA-Z])"""))) {
            var matches = Regex("""(([a-zA-Z]+[0-9]*)|([0-9]+[a-zA-Z]+))""").find(text)
            while (matches != null)
            {
                if (vars.containsKey(matches.value)) {
                    text = text.replaceRange(matches.range, vars.getValue(matches.value).toString())
                }
                else{
                    // исключение : тут пользователь ввел переменную которую не задавал(к примеру 1+2+a+c)(словарь: a=0,b=0)
                    return matches.value // та самая переменная c
                    break
                }
                 matches = Regex("""(([a-zA-Z]+[0-9]*)|([0-9]+[a-zA-Z]+))""").find(text)
            }

        }
        else{
            //исключение(тут надо в UX выдать пользователю ошибку типо ввел невозможную переменную e.g "12awd","@#!aue" и тд)
            println("false")
        }
       // println(text)
        return text
    }
    private fun calculate(textBar:String):Int{
       // println(textBar)
        val stack: Stack<Char> = Stack<Char>()
        val text = textBar.replace("""\s""".toRegex(), "")
        var RPN:String = ""
        for (i in text) {
            if(i.isDigit()){
                RPN+=i
            }
            else{
                RPN+=' '
                if (i == '-' || i == '+'){
                    if(!stack.isEmpty()) {
                        if (stack.peek() == '-' || stack.peek() == '+' || stack.peek() == '/' || stack.peek() == '*') {
                            RPN += stack.pop()
                            RPN+=' '
                        }
                        stack.push(i)
                    }
                    else stack.push(i)
                }
                if (i == '*' || i == '/') {
                    if(!stack.isEmpty()) {
                        if (stack.peek() == '/' || stack.peek() == '*') {
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
        val stackInt: Stack<String> = Stack<String>()
        var value = ""
        for (i in RPN.indices){
            if (RPN[i]==' ') {
                if(value!="")
                    stackInt.push(value)
                value = ""
            }
            else if(RPN[i].isDigit()) value += RPN[i]
            if (RPN[i] == '-' || RPN[i] == '+' || RPN[i] == '/' || RPN[i] == '*') {
                if (RPN[i] == '+') {
                    val x = stackInt.pop()
                    val y = stackInt.pop()
                    val count = x.toInt() + y.toInt()
                    stackInt.push(count.toString())
                } else
                    if (RPN[i] == '-') {
                        val x = stackInt.pop()
                        val y = stackInt.pop()
                        val count = y.toInt() - x.toInt()
                        stackInt.push(count.toString())
                    } else
                        if (RPN[i] == '*') {
                            val x = stackInt.pop()
                            val y = stackInt.pop()
                            val count = x.toInt() * y.toInt()
                            stackInt.push(count.toString())
                        } else
                            if (RPN[i] == '/') {
                                val x = stackInt.pop()
                                val y = stackInt.pop()
                                try {
                                    val count = y.toInt() / x.toInt()
                                    stackInt.push(count.toString())
                                }
                                catch (e: NumberFormatException) {
                                    return 0
                                }

                            }

            }


        }
        return(stackInt.pop().toInt())
    }
}