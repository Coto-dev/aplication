package Logic

import Logic.MainBlock.Companion.variables
import android.text.TextUtils.replace
import androidx.core.text.isDigitsOnly
import java.lang.Error
import java.util.*

class If_else : MainBlock {
    override var ErrorString = ""
    override var status = true
    val vars = MainBlock.variables
    val name: String? = null

    val variables = mutableMapOf<String,Int>()
    val listOfBlocks = mutableListOf<MainBlock>()
    val MapArray = mutableMapOf<String,Array<Int>>()

    var textBar: String = ""
    var variable: String = ""
    override fun start() = assign()
    fun assign() {
        vars.replace(assignmentVar(variable), calculate(recognize(textBar)))
    }

    private fun assignmentVar(textBar:String): String {
        var variable:String =""
        if (!textBar.contains(Regex("""([^\d|\s|^\+\-\/\*\(\)\%|^a-zA-Z])"""))) {
            val matches = Regex("""(([a-zA-Z]+[0-9]*)|([0-9]+[a-zA-Z]+))""").find(textBar)
            if (vars.containsKey(matches?.value))
                variable = matches?.value.toString()
            else {
                ErrorString = "variable is not exist : ${matches?.value}"
                status = false
            }

        }
        else{
            status = false
            val matches = Regex("""([^\d|\s|^\+\-\/\*\(\)\%|^a-zA-Z])""").find(textBar)
            ErrorString = "the value of the variable was entered incorrectly : ${matches?.value}"
        }

        return variable
    }
    private fun recognize(textBar:String):String{
        var text = textBar
        if (!textBar.contains(Regex("""([^\d|\s|^\+\-\/\*\(\)\%|^a-zA-Z])"""))) {
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
            val matches = Regex("""([^\d|\s|^\+\-\/\*\(\)\%|^a-zA-Z])""").find(textBar)
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
        if (textBar.contains(Regex("""((\d\s*\/\s*0))"""))) {
            val matches = Regex("""((\d\s*\/\s*0))""").find(textBar)
            ErrorString = "incorrect expression : ${matches?.value}"
            status = false
        }
        if (!status)
            return  0
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

    private fun equality(textBar: String): Boolean{
        println(textBar)
        val x1:  MatchResult? = """[^>=|<=|==|!=|<>|>|<]""".toRegex().find(textBar)
        val x2:  MatchResult? = x1?.next();
        if(x1==null || x2==null) return false
        if(textBar.contains(Regex(""">=""")))


        if(calculate(x1.value)>=calculate(x2.value))
            {return  true;}
            else return  false;
        else if(textBar.contains(Regex("""<=""")))
                if(calculate(x1.value)<=calculate(x2.value))
                    return  true;
                else return  false
            else if(textBar.contains(Regex("""==""")))
                if(calculate(x1.value)==calculate(x2.value))
                    return  true;
                else return  false;
                else if(textBar.contains(Regex("""!=""")))
                    if(calculate(x1.value)!=calculate(x2.value))
                        return  true;
                    else return  false;
                    else if(textBar.contains(Regex("""<""")))
                        if(calculate(x1.value)<calculate(x2.value))
                            return  true;
                        else return  false;
                        else if(textBar.contains(Regex(""">""")))
                            if(calculate(x1.value)>calculate(x2.value))
                                return  true;
                            else return  false;

        return  false;
    }

    private fun condition(textBar: String){//выполняется ли условие
        println(textBar)
        if (textBar.contains(Regex("""((\d\s*\/\s*0))"""))) {
            val matches = Regex("""((\d\s*\/\s*0))""").find(textBar)
            ErrorString = "incorrect expression : ${matches?.value}"
            status = false
        }
        if (!status)
            for (name in listOfBlocks) {
                    break
            }
        val text = textBar.replace("""\s""".toRegex(), "")
        if (!text.contains(Regex("""([\+\-\/\*\%])""")))
            for (name in listOfBlocks) {
                name.start()
                if (!name.status)
                {
                    println(name.ErrorString)
                    break
                }
            }
        val stack: Stack<Char> = Stack<Char>();

        var RPN: String = ""
        for (i in text) {
            if (i.isDigit()) {
                RPN += i
            } else {
                println(RPN)
                RPN += ' '
                if (i == '|') {
                    if (!stack.isEmpty()) {
                        if (stack.peek() == '|' || stack.peek() == '&') {
                            RPN += stack.pop()
                            RPN += ' '
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
        RPN = RPN.replace("""^\s+""".toRegex(), "")
        val stackInt: Stack<String> = Stack<String>()
        var value = ""
        for (i in RPN.indices) {
            if (RPN[i] == ' ') {
                if (value != "")
                    stackInt.push(value)
                value = ""
            } else if (RPN[i].isDigit()) value += RPN[i]
            if (RPN[i] == '|' ||  RPN[i] == '&') {
                if (RPN[i] == '|') {
                        if(equality(stackInt.pop())||equality(stackInt.pop())){
                                stackInt.push("1")
                            }
                                else stackInt.push("0")

                }  else
                        if (RPN[i] == '&') {
                            if(equality(stackInt.pop())&&equality(stackInt.pop())){
                                stackInt.push("1")
                            }
                            else stackInt.push("0")
                        }

            }
        }

        println(stackInt)
    }



}


